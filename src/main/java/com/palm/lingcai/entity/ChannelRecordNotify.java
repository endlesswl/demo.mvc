package com.palm.lingcai.entity;


public class ChannelRecordNotify {

	//alias
	public static final String TABLE_ALIAS = "ChannelRecordNotify";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_CHANNELRECORDID = "channelrecordid";
	public static final String ALIAS_NOTIFYURL = "notifyurl";
	public static final String ALIAS_PARAMS = "params";
	public static final String ALIAS_RESPONSE = "response";
	public static final String ALIAS_NOTIFYTIME = "notifytime";
	public static final String ALIAS_CREATETIME = "createtime";

	/**
	 * 
	 */
	private Long id;
	/**
	 * 
	 */
	private Long channelrecordid;
	/**
	 * 
	 */
	private String notifyurl;
	/**
	 * 
	 */
	private String params;
	/**
	 * 
	 */
	private String response;
	/**
	 * 
	 */
	private java.util.Date notifytime;
	/**
	 * 
	 */
	private java.util.Date createtime;

	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	public java.lang.Long getChannelrecordid() {
		return this.channelrecordid;
	}
	
	public void setChannelrecordid(java.lang.Long value) {
		this.channelrecordid = value;
	}
	public java.lang.String getNotifyurl() {
		return this.notifyurl;
	}
	
	public void setNotifyurl(java.lang.String value) {
		this.notifyurl = value;
	}
	public java.lang.String getParams() {
		return this.params;
	}
	
	public void setParams(java.lang.String value) {
		this.params = value;
	}
	public java.lang.String getResponse() {
		return this.response;
	}
	
	public void setResponse(java.lang.String value) {
		this.response = value;
	}
	public java.util.Date getNotifytime() {
		return this.notifytime;
	}
	
	public void setNotifytime(java.util.Date value) {
		this.notifytime = value;
	}
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
}