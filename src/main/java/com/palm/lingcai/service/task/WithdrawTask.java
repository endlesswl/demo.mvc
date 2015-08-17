package com.palm.lingcai.service.task;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palm.commom.uitl.UUIDUtils;
import com.palm.lingcai.entity.Withdraw;
import com.palm.lingcai.service.WithdrawService;
import com.palm.lingcai.statuscode.WithdrawEnum;
import com.palm.lingcai.util.HttpClientUtil;
import com.palm.lingcai.util.ProPertiesUtils;
import com.palm.lingcai.util.withdraw.BacthExcel;
import com.palm.lingcai.util.withdraw.Record;

/**
 * 定时生成支付文件上传支付平台
 * @author LDL
 * 2014年12月16日
 */
@Service
public class WithdrawTask {
	private static final String ALIPAY_ACCOUNT = "service@lingcaibao.com";
	@Autowired
	private WithdrawService withdrawService;

	/**
	 * 上传提现文件
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param sort
	 * @param wxserverid
	 * @param request
	 * @param response
	 * @param model
	 */
	public void uploadPayFile() {
		File tempfile =null;
		try {
			//查询0-待转账的提现记录；每次限制100条
			int limitNum = 500;
			String status = "0";
			List<Withdraw> resultList = withdrawService.findWithdraws(status,limitNum);
			if (resultList == null || resultList.size() == 0) {
				return;
			}
			
			List<Record> records = new ArrayList<Record>();
			BigDecimal totalMoney = BigDecimal.ZERO;
			StringBuffer ids = new StringBuffer();
			for (Withdraw withdraw : resultList) {
				Record record = new Record();
				record.setId(withdraw.getOrderid()+"");
				record.setUserName(withdraw.getAccountname());
				record.setMark(2);
				BigDecimal amount = withdraw.getAmount();
				BigDecimal base1 = new BigDecimal(20000);
				BigDecimal rate1 = new BigDecimal(1);
				BigDecimal base2 = new BigDecimal(50000);
				BigDecimal rate2 = new BigDecimal(3);
				BigDecimal rate3 = new BigDecimal(5);
				// 0万元-2万元（不包含2万元）/1元每笔；2万元-5万元(不含5万元)/3元每笔；5万元以上（含5万元）/5元每笔
				// 2万
				if (amount.compareTo(base1) < 0) {
					// 扣1元
					record.setMoney(withdraw.getAmount().subtract(rate1));
					totalMoney = totalMoney.add(withdraw.getAmount().subtract(
							rate1));
				} else if (amount.compareTo(base1) >= 0
						&& amount.compareTo(base2) < 0) {
					// 扣3元
					record.setMoney(withdraw.getAmount().subtract(rate2));
					totalMoney = totalMoney.add(withdraw.getAmount().subtract(
							rate2));
				} else {
					// 扣5元
					record.setMoney(withdraw.getAmount().subtract(rate3));
					totalMoney = totalMoney.add(withdraw.getAmount().subtract(
							rate3));
				}
				record.setReceiveBankCity("");
				record.setReceiveBankName(withdraw.getBankname());
				record.setReceiveBankNum(withdraw.getCardno());
				record.setReceiveBankProvince("");
				record.setReceiveBankSubbranch("");
				record.setRemark("用户提现");
				records.add(record);
				ids.append(",'").append(withdraw.getId()).append("'");
			}
			List<String> headers = new ArrayList<String>();
			Date time = Calendar.getInstance().getTime();
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String dStr = format.format(time);
			headers.add(dStr);
			headers.add(totalMoney.toString());
			headers.add(records.size() + "");
			headers.add(ALIPAY_ACCOUNT);
			String idStr = ids.toString().substring(1) ;

			// 生成excel文件
			tempfile = BacthExcel.writeExcel(records, headers);
			String fileName = "lingcai_" + UUIDUtils.getSerialID() + ".xls";
			
			String url = ProPertiesUtils.getPayBankUrl()+"?payFile="+fileName+"&callbackUrl="+ProPertiesUtils.getPayReusltUrl();
			boolean f = HttpClientUtil.upload(url, tempfile);
			if (f) {
				withdrawService.updateWithdrawByIds(idStr, WithdrawEnum.IN_PROGRESS.code, "已处理等待转账");
			} else {
				withdrawService.updateWithdrawByIds(idStr, WithdrawEnum.WAIT.code, "上传支付平台失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (tempfile != null) {
				tempfile.delete();
			}
		}
	}
}
