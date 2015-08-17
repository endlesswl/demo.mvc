package com.palm.lingcai.entity;

public class ReceiveCode {

	public static final String TABLE_ALIAS = "ReceiveCode";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_ORDERID = "orderId";
	public static final String ALIAS_RECEIVECODE = "receiveCode";
	public static final String ALIAS_CREATETIME = "createtime";
	public static final String ALIAS_FLAG = "flag";

	private String id;
	
	private String orderId;
	
	private Long receiveCode;
	
	private java.util.Date createtime;
	
	private Integer flag;
	
	public java.lang.String getOrderId() {
		return this.orderId;
	}
	
	public void setOrderId(java.lang.String value) {
		this.orderId = value;
	}
	public java.lang.Long getReceiveCode() {
		return this.receiveCode;
	}
	
	public void setReceiveCode(java.lang.Long value) {
		this.receiveCode = value;
	}
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	public Integer getFlag() {
		return this.flag;
	}
	
	public void setFlag(Integer value) {
		this.flag = value;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}