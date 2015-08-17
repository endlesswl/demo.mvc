package com.palm.lingcai.entity;

import java.math.BigDecimal;


public class Reward {

	//alias
	public static final String TABLE_ALIAS = "其他奖励计划";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_MARKETID = "营销计划ID";
	public static final String ALIAS_MARKETTYPE = "营销计划类型";
	public static final String ALIAS_NAME = "名称";
	public static final String ALIAS_TOTALNUM = "数量";
	public static final String ALIAS_PROBABILITY = "中奖机率";
	public static final String ALIAS_DESCRIPTION = "活动规则";
	public static final String ALIAS_PRIZEDESC = "竞奖规则";
	public static final String ALIAS_COUPONTYPE = "礼券类型";
	public static final String ALIAS_ATTACHMENT = "礼券素材";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_MODIFYTIME = "修改时间";
	public static final String ALIAS_REMARK = "备注";
	public static final String ALIAS_AWARD = "单次中奖金额";
	/**
	 * 
	 */
	private Long id;
	/**
	 * 营销计划ID
	 */
	private Long marketId;
	/**
	 * 营销计划类型
	 */
	private Integer marketType;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 数量，总额，0为不限制
	 */
	private Integer totalNum;
	/**
	 * 中奖概率
	 */
	private BigDecimal probability;
	/**
	 * 活动描述
	 */
	private String description;
	/**
	 * 竞奖规则
	 */
	private String prizeDesc;
	/**
	 * 礼券类型
	 */
	private Integer couponType;
	/**
	 * 附件
	 */
	private String attachment;
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyTime;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 单次中奖金额
	 */
	private BigDecimal award;
	
	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	public java.lang.Long getMarketId() {
		return this.marketId;
	}
	
	public void setMarketId(java.lang.Long value) {
		this.marketId = value;
	}
	public Integer getMarketType() {
		return this.marketType;
	}
	
	public void setMarketType(Integer value) {
		this.marketType = value;
	}
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String value) {
		this.name = value;
	}
	public java.lang.Integer getTotalNum() {
		return this.totalNum;
	}
	
	public void setTotalNum(java.lang.Integer value) {
		this.totalNum = value;
	}
	public BigDecimal getProbability() {
		return this.probability;
	}
	
	public void setProbability(BigDecimal value) {
		this.probability = value;
	}
	public java.lang.String getDescription() {
		return this.description;
	}
	
	public void setDescription(java.lang.String value) {
		this.description = value;
	}
	public java.lang.String getPrizeDesc() {
		return this.prizeDesc;
	}
	
	public void setPrizeDesc(java.lang.String value) {
		this.prizeDesc = value;
	}
	public Integer getCouponType() {
		return this.couponType;
	}
	
	public void setCouponType(Integer value) {
		this.couponType = value;
	}
	public java.lang.String getAttachment() {
		return this.attachment;
	}
	
	public void setAttachment(java.lang.String value) {
		this.attachment = value;
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
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}

	public BigDecimal getAward() {
		return award;
	}

	public void setAward(BigDecimal award) {
		this.award = award;
	}
	
	
	
}