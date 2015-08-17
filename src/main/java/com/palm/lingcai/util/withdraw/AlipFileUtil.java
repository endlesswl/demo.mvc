package com.palm.lingcai.util.withdraw;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

/**
 * 
 * @author LDL 2014年12月10日
 */
public class AlipFileUtil {
	// 文件头信息
	private final static String HEADER1 = "日期,总金额,总笔数,支付宝帐号(Email)";
	private final static String HEADER2 = "商户流水号,收款银行户名,收款银行帐号,收款开户银行,收款银行所在省份,收款银行所在市,收款支行名称,金额,对公对私标志,备注";

	public static void main(String[] args) {
		List<String> headers = new ArrayList<String>();
		List<Record> records = new ArrayList<Record>();
		headers.add("20141124");
		headers.add("4.00");
		headers.add("1");
		headers.add("service@lingcaibao.com");

		Record r = new Record();
		r.setId("dssssf");
		r.setMoney(BigDecimal.ZERO);
		Record r2 = new Record();
		r2.setId("dxf");
		r2.setMoney(BigDecimal.ZERO);
		records.add(r2);
		records.add(r);
		writeCSV(records, headers);
	}

	/**
	 * 写入CSV文件
	 * @param records
	 * @param headers
	 */
	public static File writeCSV(List<Record> records, List<String> headers) {
		try {
			StringBuffer txtBuf = new StringBuffer();
			// 写入文件头信息
			txtBuf.append(HEADER1).append("\n");
			for (int i = 0; i < headers.size(); i++) {
				if (i == headers.size() - 1) {
					txtBuf.append(headers.get(i)).append("\n");
					break;
				}
				txtBuf.append(headers.get(i)).append(",");
			}

			// 商户流水号 收款银行户名 收款银行帐号 收款开户银行 收款银行所在省份 收款银行所在市 收款支行名称 金额 对公对私标志 备注
			txtBuf.append(HEADER2).append("\n");
			for (int i = 0; i < records.size(); i++) {
				Record record = records.get(i);
				txtBuf.append(csvSplit(record.getOrderId()));// 商户流水号
				txtBuf.append(csvSplit(record.getUserName()));// 收款姓名
				txtBuf.append(csvSplit(record.getReceiveBankNum()));// 收款银行账号
				txtBuf.append(csvSplit(record.getReceiveBankName()));// 收款开户银行
				txtBuf.append(csvSplit(record.getReceiveBankProvince()));// 收款银行所在省
				txtBuf.append(csvSplit(record.getReceiveBankCity()));// 收款银行所在市
				txtBuf.append(csvSplit(record.getReceiveBankSubbranch()));// 收款支行名称
				txtBuf.append(csvSplit(record.getMoney().toString()));// 金额
				txtBuf.append(csvSplit(record.getMark()));// 标示
				txtBuf.append(csvSplit(record.getRemark()));// 备注
				txtBuf.append("\n");
			}
			File file = new File("D:/" + System.currentTimeMillis() + ".csv");
			createFile(file, txtBuf.toString());
			return file;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}

	public static void createFile(File file, String text) {
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(file);
			os.write(text.getBytes("GB2312"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(os);
		}
	}

	public static String csvSplit(Object obj) {
		String str = "";
		if (obj != null) {
			str = obj.toString();
		}
		return str + ",";
	}
}