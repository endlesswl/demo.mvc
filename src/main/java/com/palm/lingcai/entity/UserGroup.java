package com.palm.lingcai.entity;

import java.io.Serializable;

public class UserGroup implements Serializable{

	//alias
	public static final String TABLE_ALIAS = "UserGroup";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_MARKETID = "marketid";
	public static final String ALIAS_USERID = "userid";
	public static final String ALIAS_GROUPNAME = "groupName";
	public static final String ALIAS_LOSERRULE = "loserRule";
	public static final String ALIAS_GROUPTYPE = "groupType";
	public static final String ALIAS_CREATETIME = "createtime";
	public static final String ALIAS_MODIFYTIME = "modifytime";

	/**
	 * 
	 */
	private Long id;
	/**
	 * 营销计划
	 */
	private Long marketid;
	/**
	 * 发起人
	 */
	private Long userid;
	/**
	 * 组名
	 */
	private String groupName;
	/**
	 * 赌注
	 */
	private String loserRule;
	/**
	 * 组类型，0世界杯对战
	 */
	private Integer groupType;
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
	public java.lang.String getGroupName() {
		return this.groupName;
	}
	
	public void setGroupName(java.lang.String value) {
		this.groupName = value;
	}
	public java.lang.String getLoserRule() {
		return this.loserRule;
	}
	
	public void setLoserRule(java.lang.String value) {
		this.loserRule = value;
	}
	public java.lang.Integer getGroupType() {
		return this.groupType;
	}
	
	public void setGroupType(java.lang.Integer value) {
		this.groupType = value;
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