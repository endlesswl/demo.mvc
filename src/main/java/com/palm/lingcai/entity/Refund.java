package com.palm.lingcai.entity;

import java.math.BigDecimal;


public class Refund {

	//alias
	public static final String TABLE_ALIAS = "Refund";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_MARKETID = "marketid";
	public static final String ALIAS_USERID = "userid";
	public static final String ALIAS_METHOD = "method";
	public static final String ALIAS_REFUNDMONEY = "refundMoney";
	public static final String ALIAS_CREATETIME = "createTime";
	public static final String ALIAS_STATUS = "status";

	/**
	 * 返款id
	 */
	private Long id;
	/**
	 * 计划id
	 */
	private Long marketid;
	/**
	 * 用户id
	 */
	private Long userid;
	/**
	 * 返款方式
	 */
	private Boolean method;
	/**
	 * 返款金额
	 */
	private BigDecimal refundMoney;
	/**
	 * 返款时间
	 */
	private java.util.Date createTime;
	/**
	 *  状态
	 */
	private Boolean status;
	/**
	 * 计划名称
	 */
	private String marketName;

	
	public String getMarketName() {
		return marketName;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}

	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	public java.lang.Long getMarketid() {
		return this.marketid;
	}
	
	public void setMarketid(java.lang.Long value) {
		this.marketid = value;
	}
	public java.lang.Long getUserid() {
		return this.userid;
	}
	
	public void setUserid(java.lang.Long value) {
		this.userid = value;
	}
	public java.lang.Boolean getMethod() {
		return this.method;
	}
	
	public void setMethod(java.lang.Boolean value) {
		this.method = value;
	}
	public BigDecimal getRefundMoney() {
		return this.refundMoney;
	}
	
	public void setRefundMoney(BigDecimal value) {
		this.refundMoney = value;
	}
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	public java.lang.Boolean getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.Boolean value) {
		this.status = value;
	}
}