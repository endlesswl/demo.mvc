package com.palm.lingcai.entity;

import javax.validation.constraints.NotNull;

import java.math.BigDecimal;

public class Withdraw {

	// alias
	public static final String TABLE_ALIAS = "提现";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_USERID = "用户";
	public static final String ALIAS_BANKID = "银行卡";
	public static final String ALIAS_AMOUNT = "提现金额";
	public static final String ALIAS_CREATETIME = "提交时间";
	public static final String ALIAS_AUDITTIME = "审核时间";
	public static final String ALIAS_OPERATOR = "操作员";
	public static final String ALIAS_STATUS = "状态";
	public static final String ALIAS_PROCESSTIME = "处理时间";
	public static final String ALIAS_REMARK = "备注";
	public static final String ALIAS_SOURCE = "来源";

	/**
     *
     */
	private Long id;
	/**
	 * 用户
	 */
	private Long userid;
	/**
	 * 银行卡
	 */
	private Long bankid;
	/**
	 * 订单号
	 */
	private Long orderid;
	/**
	 * 提现金额
	 */
	private BigDecimal amount;
	/**
	 * 申请时间
	 */
	private java.util.Date createtime;
	/**
	 * 审核时间
	 */
	private java.util.Date audittime;
	/**
	 * 操作员
	 */
	private Long operator;
	/**
	 * 状态 0提交 1审核通过 2审核不通过 3转账成功 4转账不成功
	 */
	private Integer status;
	/**
	 * 处理时间
	 */
	private java.util.Date processtime;
	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 微信推广号id
	 */
	private Long wxserverid;

	/**
	 * 渠道提现流水号
	 */
	private String serialNo;

	/**
	 * 渠道提现流水号
	 */
	private String source;

	/**
	 * 交易流水号
	 */
	private String tradeNo;
	// ////////////////////////////////银行卡相关//////////////////////////////////////////
	/**
	 * 卡号
	 */
	private String cardno;
	/**
	 * 银行名称
	 */

	private String bankname;

	private String accountname;// 户名

	// ////////////////////////////////用户相关//////////////////////////////////////////

	private String realName;// 真实姓名
	private String username;
	private String cardid;// 证件号码

	public Long getId() {
		return this.id;
	}

	public void setId(Long value) {
		this.id = value;
	}

	@NotNull
	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long value) {
		this.userid = value;
	}

	@NotNull
	public Long getBankid() {
		return this.bankid;
	}

	public void setBankid(Long value) {
		this.bankid = value;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal value) {
		this.amount = value;
	}

	public java.util.Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}

	public java.util.Date getAudittime() {
		return this.audittime;
	}

	public void setAudittime(java.util.Date value) {
		this.audittime = value;
	}

	public Long getOperator() {
		return this.operator;
	}

	public void setOperator(Long value) {
		this.operator = value;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer value) {
		this.status = value;
	}

	public java.util.Date getProcesstime() {
		return this.processtime;
	}

	public void setProcesstime(java.util.Date value) {
		this.processtime = value;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String value) {
		this.remark = value;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Long getOrderid() {
		return orderid;
	}

	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}

	public Long getWxserverid() {
		return wxserverid;
	}

	public void setWxserverid(Long wxserverid) {
		this.wxserverid = wxserverid;
	}

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

}