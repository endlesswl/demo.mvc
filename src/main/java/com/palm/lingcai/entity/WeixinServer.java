package com.palm.lingcai.entity;


public class WeixinServer {

	//alias
	public static final String TABLE_ALIAS = "WeixinServer";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_APPID = "appid";
	public static final String ALIAS_APPSECRET = "appsecret";
	public static final String ALIAS_USERID = "userid";

	/**
	 * 
	 */
	private Long id;
	/**
	 * 微信服务号appid
	 */
	private String appid;
	/**
	 * 
	 */
	private String appsecret;
	/**
	 * 所属用户
	 */
	private Long userid;

	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	public java.lang.String getAppid() {
		return this.appid;
	}
	
	public void setAppid(java.lang.String value) {
		this.appid = value;
	}
	public java.lang.String getAppsecret() {
		return this.appsecret;
	}
	
	public void setAppsecret(java.lang.String value) {
		this.appsecret = value;
	}
	public java.lang.Long getUserid() {
		return this.userid;
	}
	
	public void setUserid(java.lang.Long value) {
		this.userid = value;
	}
}