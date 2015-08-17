package com.palm.lingcai.util.withdraw;

import java.math.BigDecimal;

/**
 * 收款人信息
 * 
 * @author kelylmall
 * 
 */
public class Record {
	// 序号
	private String orderId;
	// 收款银行户名
	private String userName;
	// 收款银行帐号
	private String receiveBankNum;
	// 收款开户银行
	private String receiveBankName;
	// 收款银行所在省份
	private String receiveBankProvince;
	// 收款银行所在市
	private String receiveBankCity;
	// 收款支行名称
	private String receiveBankSubbranch;
	// 金额
	private BigDecimal money;
	// 对公对私标志
	private int mark;
	// 备注
	private String remark;

	public String getOrderId() {
		return orderId;
	}

	public void setId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getReceiveBankNum() {
		return receiveBankNum;
	}

	public void setReceiveBankNum(String receiveBankNum) {
		this.receiveBankNum = receiveBankNum;
	}

	public String getReceiveBankName() {
		return receiveBankName;
	}

	public void setReceiveBankName(String receiveBankName) {
		this.receiveBankName = receiveBankName;
	}

	public String getReceiveBankProvince() {
		return receiveBankProvince;
	}

	public void setReceiveBankProvince(String receiveBankProvince) {
		this.receiveBankProvince = receiveBankProvince;
	}

	public String getReceiveBankCity() {
		return receiveBankCity;
	}

	public void setReceiveBankCity(String receiveBankCity) {
		this.receiveBankCity = receiveBankCity;
	}

	public String getReceiveBankSubbranch() {
		return receiveBankSubbranch;
	}

	public void setReceiveBankSubbranch(String receiveBankSubbranch) {
		this.receiveBankSubbranch = receiveBankSubbranch;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}