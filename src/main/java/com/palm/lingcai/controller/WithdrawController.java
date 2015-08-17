package com.palm.lingcai.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.palm.lingcai.service.WithdrawService;
import com.palm.lingcai.util.HttpClientUtil;
import com.palm.lingcai.util.ProPertiesUtils;
import com.palm.lingcai.util.withdraw.AlipayWithdraw;
import com.palm.lingcai.util.withdraw.BacthExcel;


/**
 * 提现/支付转账相关
 * @author LDL
 * 2014年12月23日
 */
@Controller
@RequestMapping(value = "/withdraw")
public class WithdrawController {
	@Autowired
	private WithdrawService withdrawService;
	
	/**
	 * 获取支付结果文件（文件流）
	 * @param filename
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "/downPayResult")
	public void downPayResult(HttpServletRequest request, String fileName)
			throws Exception {
		String saveFile = ProPertiesUtils.getPayResultPath() + File.separator + fileName;

		// 接收文件流
		boolean flag = HttpClientUtil.getStreamToFile(request, saveFile);
		if (!flag) {
			throw new Exception("文件接受失败");
		}
		// 解析EXCEL
		List<AlipayWithdraw> withdrawList = BacthExcel.readAlipayExcel(new File(saveFile));

		// 处理
		withdrawService.auditAlipayWithdraw(withdrawList);
	}
	
}
