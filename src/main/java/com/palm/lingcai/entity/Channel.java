package com.palm.lingcai.entity;


public class Channel {

	//alias
	public static final String TABLE_ALIAS = "渠道计划";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_USERID = "用户ID";
	public static final String ALIAS_CHANNELNAME = "渠道名称";
	public static final String ALIAS_PWD = "密码";
	public static final String ALIAS_CHANNELKEY = "渠道KEY";
	public static final String ALIAS_CHANNELSIGN = "渠道标识";
	public static final String ALIAS_CALLBACKURL = "回调地址";
	public static final String ALIAS_ACCOUNTTYPE = "账户类型";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_STARTTIME = "开始时间";
	public static final String ALIAS_ENDTIME = "结束时间";
	public static final String ALIAS_REMARK = "备注";

	/**
	 * 渠道id
	 */
	private Long id;
	/**
	 * 用户id
	 */
	private Long userid;
	/**
	 * 渠道名称
	 */
	private String channelName;
	/**
	 * 渠道密码
	 */
	private String pwd;
	/**
	 * 渠道channelKey
	 */
	private String channelKey;
	/**
	 * 渠道标识
	 */
	private String channelSign;
	/**
	 * 公司地址
	 */
	private String callbackUrl;
	/**
	 * 账户类型:0：预付费 1：后付费
	 */
	private Integer accountType;
	/**
	 * 创建时间
	 */
	private java.util.Date createtime;
	/**
	 * 开始时间
	 */
	private java.util.Date starttime;
	/**
	 * 结束时间
	 */
	private java.util.Date endtime;
	/**
	 * 备注
	 */
	private String remark;

	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	public java.lang.Long getUserid() {
		return this.userid;
	}
	
	public void setUserid(java.lang.Long value) {
		this.userid = value;
	}
	public java.lang.String getChannelName() {
		return this.channelName;
	}
	
	public void setChannelName(java.lang.String value) {
		this.channelName = value;
	}
	public java.lang.String getPwd() {
		return this.pwd;
	}
	
	public void setPwd(java.lang.String value) {
		this.pwd = value;
	}
	public java.lang.String getChannelKey() {
		return this.channelKey;
	}
	
	public void setChannelKey(java.lang.String value) {
		this.channelKey = value;
	}
	public java.lang.String getChannelSign() {
		return this.channelSign;
	}
	
	public void setChannelSign(java.lang.String value) {
		this.channelSign = value;
	}
	
	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public java.lang.Integer getAccountType() {
		return this.accountType;
	}
	
	public void setAccountType(java.lang.Integer value) {
		this.accountType = value;
	}
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	public java.util.Date getStarttime() {
		return this.starttime;
	}
	
	public void setStarttime(java.util.Date value) {
		this.starttime = value;
	}
	public java.util.Date getEndtime() {
		return this.endtime;
	}
	
	public void setEndtime(java.util.Date value) {
		this.endtime = value;
	}
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
}