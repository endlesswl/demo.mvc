package com.palm.lingcai.entity;

import java.math.BigDecimal;


public class Balance {

	//alias
	public static final String TABLE_ALIAS = "Balance";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_BALANCE = "balance";
	public static final String ALIAS_USERID = "userid";
	public static final String ALIAS_TYPE = "type";
	public static final String ALIAS_WXSERVERID = "wxserverid";
	public static final String ALIAS_REMARK = "remark";
	public static final String ALIAS_CREATETIME = "createtime";
	public static final String ALIAS_MODIFYTIME = "modifytime";

	/**
	 * 
	 */
	private Long id;
	/**
	 * 
	 */
	private BigDecimal balance;
	/**
	 * 
	 */
	private Long userid;
	/**
	 * 
	 */
	private Long type;
	/**
	 * 
	 */
	private Long wxserverid;
	/**
	 * 备注说明
	 */
	private String remark;
	/**
	 * 创建时间
	 */
	private java.util.Date createtime;
	/**
	 * 修改时间
	 */
	private java.util.Date modifytime;

	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	public BigDecimal getBalance() {
		return this.balance;
	}
	
	public void setBalance(BigDecimal value) {
		this.balance = value;
	}
	public java.lang.Long getUserid() {
		return this.userid;
	}
	
	public void setUserid(java.lang.Long value) {
		this.userid = value;
	}
	public java.lang.Long getType() {
		return this.type;
	}
	
	public void setType(java.lang.Long value) {
		this.type = value;
	}
	public java.lang.Long getWxserverid() {
		return this.wxserverid;
	}
	
	public void setWxserverid(java.lang.Long value) {
		this.wxserverid = value;
	}
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	public java.util.Date getModifytime() {
		return this.modifytime;
	}
	
	public void setModifytime(java.util.Date value) {
		this.modifytime = value;
	}
}