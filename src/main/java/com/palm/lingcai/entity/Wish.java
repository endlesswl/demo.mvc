package com.palm.lingcai.entity;


public class Wish {

	//alias
	public static final String TABLE_ALIAS = "Wish";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_CONTENT = "content";
	public static final String ALIAS_WISHTYPE = "wishType";
	public static final String ALIAS_REMARK = "remark";
	public static final String ALIAS_CREATETIME = "createTime";
	public static final String ALIAS_MODIFYTIME = "modifyTime";

	/**
	 * 
	 */
	private Long id;
	/**
	 * 祝福语
	 */
	private String content;
	/**
	 * 祝福语类型 0 默认无  1 诙谐 2暖心 3友谊
	 */
	private Integer wishType;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 
	 */
	private java.util.Date createTime;
	/**
	 * 
	 */
	private java.util.Date modifyTime;

	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	public java.lang.Integer getWishType() {
		return this.wishType;
	}
	
	public void setWishType(java.lang.Integer value) {
		this.wishType = value;
	}
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	public java.util.Date getModifyTime() {
		return this.modifyTime;
	}
	
	public void setModifyTime(java.util.Date value) {
		this.modifyTime = value;
	}
}