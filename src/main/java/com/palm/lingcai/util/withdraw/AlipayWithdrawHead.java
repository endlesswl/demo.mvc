package com.palm.lingcai.util.withdraw;

import java.util.ArrayList;
import java.util.List;

/**
 * 提现概要信息
 * 
 * @author LDL
 * 
 */
public class AlipayWithdrawHead {
	/**
	 * 提现文件名
	 */
	private String fileName;
	/**
	 * 付款日期
	 */
	private String payDate;
	/**
	 * 处理日期
	 */
	private String dealDate;
	/**
	 * 总笔数
	 */
	private String total;
	/**
	 * 处理成功笔数
	 */
	private String successTotal;
	/**
	 * 处理失败笔数
	 */
	private String failTotal;
	/**
	 * 成功付款总金额
	 */
	private String successAmount;
	/**
	 * 失败付款总金额
	 */
	private String failAmount;
	/**
	 * 支付宝帐号(Email)
	 */
	private String alipayAccount;

	/**
	 * 提现明细
	 */
	private List<AlipayWithdraw> withdrawList = new ArrayList<AlipayWithdraw>();

	public List<AlipayWithdraw> getWithdrawList() {
		return withdrawList;
	}

	public void setWithdrawList(List<AlipayWithdraw> withdrawList) {
		this.withdrawList = withdrawList;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public String getDealDate() {
		return dealDate;
	}

	public void setDealDate(String dealDate) {
		this.dealDate = dealDate;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getSuccessTotal() {
		return successTotal;
	}

	public void setSuccessTotal(String successTotal) {
		this.successTotal = successTotal;
	}

	public String getFailTotal() {
		return failTotal;
	}

	public void setFailTotal(String failTotal) {
		this.failTotal = failTotal;
	}

	public String getSuccessAmount() {
		return successAmount;
	}

	public void setSuccessAmount(String successAmount) {
		this.successAmount = successAmount;
	}

	public String getFailAmount() {
		return failAmount;
	}

	public void setFailAmount(String failAmount) {
		this.failAmount = failAmount;
	}

	public String getAlipayAccount() {
		return alipayAccount;
	}

	public void setAlipayAccount(String alipayAccount) {
		this.alipayAccount = alipayAccount;
	}

}