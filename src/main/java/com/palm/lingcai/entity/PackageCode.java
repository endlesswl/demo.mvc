package com.palm.lingcai.entity;


public class PackageCode {

	//alias
	public static final String TABLE_ALIAS = "PackageCode";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_CREATE_TIME = "createTime";
	public static final String ALIAS_SERVICE_ID = "serviceId";
	public static final String ALIAS_MARKET_ID = "marketId";
	public static final String ALIAS_CODE = "code";
	public static final String ALIAS_USER_ID = "userId";

	/**
	 * 
	 */
	private Long id;
	/**
	 * 
	 */
	private java.util.Date createTime;
	/**
	 * 
	 */
	private Long serviceId;
	/**
	 * 
	 */
	private Long marketId;
	/**
	 * 兑换码
	 */
	private String code;
	/**
	 * 
	 */
	private Long userId;

	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	public java.lang.Long getServiceId() {
		return this.serviceId;
	}
	
	public void setServiceId(java.lang.Long value) {
		this.serviceId = value;
	}
	public java.lang.Long getMarketId() {
		return this.marketId;
	}
	
	public void setMarketId(java.lang.Long value) {
		this.marketId = value;
	}
	public java.lang.String getCode() {
		return this.code;
	}
	
	public void setCode(java.lang.String value) {
		this.code = value;
	}
	public java.lang.Long getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.Long value) {
		this.userId = value;
	}
}