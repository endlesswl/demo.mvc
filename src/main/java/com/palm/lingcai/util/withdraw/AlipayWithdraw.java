package com.palm.lingcai.util.withdraw;

public class AlipayWithdraw {
	private String orderId;//商户提现订单号
	private String realName;//提现姓名
	private String bankNo;//提现银行卡
	private String bankName;//收款开户银行
	private String province;//收款银行所在省份
	private String city;//收款银行所在市
	private String bankSubbranch;//收款支行名称
	private String money;//提现金额
	private String type;//对公对私标志
	private String status;//成功失败标志
	private String tradeNo;//提现流水号
	private String isBack;//是否退票
	private String remark;//备注
	private String oldRemark;//原备注
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getBankNo() {
		return bankNo;
	}
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getBankSubbranch() {
		return bankSubbranch;
	}
	public void setBankSubbranch(String bankSubbranch) {
		this.bankSubbranch = bankSubbranch;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public String getIsBack() {
		return isBack;
	}
	public void setIsBack(String isBack) {
		this.isBack = isBack;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOldRemark() {
		return oldRemark;
	}
	public void setOldRemark(String oldRemark) {
		this.oldRemark = oldRemark;
	}

}