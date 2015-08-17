package com.palm.lingcai.entity;

import java.io.Serializable;
import java.util.Date;

public class ActivitiesCount implements Serializable{

	//alias
	public static final String TABLE_ALIAS = "ActivitiesCount";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_MARKETID = "marketid";
	public static final String ALIAS_USERID = "userid";
	public static final String ALIAS_COUNT = "count";
	public static final String ALIAS_CREATETIME = "createtime";
	public static final String ALIAS_STATUS = "status";

	/**
	 * 
	 */
	private Long id;
	/**
	 * 
	 */
	private Long marketid;
	/**
	 * 
	 */
	private Long userid;
	private String username;
	/**
	 * 
	 */
	private Integer count;
	/**
	 * 
	 */
	private Date createtime;
	/**
	 * 0：当天  1：全部
	 */
	private Integer status;
	
	private WXUser wxuser;

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
	public Integer getCount() {
		return this.count;
	}
	
	public void setCount(Integer value) {
		this.count = value;
	}
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer value) {
		this.status = value;
	}

	public WXUser getWxuser() {
		return wxuser;
	}

	public void setWxuser(WXUser wxuser) {
		this.wxuser = wxuser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}