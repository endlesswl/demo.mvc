package com.palm.lingcai.entity;


public class MarketRotateRule {

	//alias
	public static final String TABLE_ALIAS = "MarketRotateRule";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_CUSTOMER_ID = "customerId";
	public static final String ALIAS_ROTATE_NAME = "rotateName";
	public static final String ALIAS_ROTATE_RULE = "rotateRule";
	public static final String ALIAS_BEGIN_TIME = "beginTime";
	public static final String ALIAS_END_TIME = "endTime";
	public static final String ALIAS_CREATE_TIME = "createTime";
	public static final String ALIAS_MODIFY_TIME = "modifyTime";
	public static final String ALIAS_FLAG = "flag";

	/**
	 * 主键
	 */
	private String id;
	/**
	 * 客户ID
	 */
	private String customerId;
	/**
	 * 转盘名称
	 */
	private String rotateName;
	/**
	 * 转盘规则
	 */
	private String rotateRule;
	/**
	 * 生效时间
	 */
	private java.util.Date beginTime;
	/**
	 * 失效时间
	 */
	private java.util.Date endTime;
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;
	/**
	 * 更新时间
	 */
	private java.util.Date modifyTime;
	/**
	 * 生效失效标志位
	 */
	private Integer flag;

	public java.lang.String getId() {
		return this.id;
	}
	
	public void setId(java.lang.String value) {
		this.id = value;
	}
	public java.lang.String getCustomerId() {
		return this.customerId;
	}
	
	public void setCustomerId(java.lang.String value) {
		this.customerId = value;
	}
	public java.lang.String getRotateName() {
		return this.rotateName;
	}
	
	public void setRotateName(java.lang.String value) {
		this.rotateName = value;
	}
	public java.lang.String getRotateRule() {
		return this.rotateRule;
	}
	
	public void setRotateRule(java.lang.String value) {
		this.rotateRule = value;
	}
	public java.util.Date getBeginTime() {
		return this.beginTime;
	}
	
	public void setBeginTime(java.util.Date value) {
		this.beginTime = value;
	}
	public java.util.Date getEndTime() {
		return this.endTime;
	}
	
	public void setEndTime(java.util.Date value) {
		this.endTime = value;
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
	public Integer getFlag() {
		return this.flag;
	}
	
	public void setFlag(Integer value) {
		this.flag = value;
	}
}