package com.palm.lingcai.entity;


public class UserWeixin {

	//alias
	public static final String TABLE_ALIAS = "UserWeixin";
	public static final String ALIAS_USERID = "userid";
	public static final String ALIAS_OPENID = "openid";
	public static final String ALIAS_WXSERVERID = "wxserverid";
	public static final String ALIAS_CREATETIME = "createtime";

	/**
	 * 用户id
	 */
	private Long userid;
	/**
	 * 参与活动微信用户openid
	 */
	private String openid;
	/**
	 * 微信推广号id
	 */
	private Long wxserverid;
	/**
	 * 
	 */
	private java.util.Date createtime;
	
	
	private User user;

	public java.lang.Long getUserid() {
		return this.userid;
	}
	
	public void setUserid(java.lang.Long value) {
		this.userid = value;
	}
	public java.lang.String getOpenid() {
		return this.openid;
	}
	
	public void setOpenid(java.lang.String value) {
		this.openid = value;
	}
	public Long getWxserverid() {
		return this.wxserverid;
	}
	
	public void setWxserverid(Long value) {
		this.wxserverid = value;
	}
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
}