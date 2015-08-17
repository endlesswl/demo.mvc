package com.palm.lingcai.entity;

import java.math.BigDecimal;


public class Recharge {

	//alias
	public static final String TABLE_ALIAS = "充值";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_SUBJECT = "名称";
	public static final String ALIAS_MONEY = "金额(元)";
	public static final String ALIAS_FEE = "清算费用";
	public static final String ALIAS_RATE = "汇率";
	public static final String ALIAS_ACTUALMONEY = "到帐金额";
	public static final String ALIAS_ORDERID = "订单号";
	public static final String ALIAS_TRADENO = "交易号";
	public static final String ALIAS_USERID = "用户";
	public static final String ALIAS_CHANNEL = "渠道";
	public static final String ALIAS_BILLTIME = "充值时间";
	public static final String ALIAS_BILLSTATUS = "交易状态";
	public static final String ALIAS_PREBALANCE = "余额";
	public static final String ALIAS_IPADDR = "ip";
	public static final String ALIAS_SALT = "效验状态";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_MODIFYTIME = "修改时间";
	public static final String ALIAS_EXPIRETIME = "过期时间";
	public static final String ALIAS_INFORMATION = "交易信息";
	public static final String ALIAS_CONFIRMED = "是否确认";
	public static final String ALIAS_BODY = "body";
	public static final String ALIAS_ORDERTYPE="订单类型";

	/**
	 * 
	 */
	private Long id;
	/**
	 * 商品名称
	 */
	private String subject;
	/**
	 * 商品金额
	 */
	private BigDecimal money;
	/**
	 * 清算费用
	 */
	private BigDecimal fee;
	/**
	 * 清算汇率
	 */
	private BigDecimal rate;
	/**
	 * 实际到帐金额
	 */
	private BigDecimal actualMoney;
	/**
	 * 定单号
	 */
	private Long orderId;
	/**
	 * 第三方交易流水号
	 */
	private String tradeno;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 充值渠道，如支付宝、银联还是其他
	 */
	private Integer channel;
	/**
	 * 交易时间
	 */
	private java.util.Date billtime;
	/**
	 * 交易状态,等待支付、支付成功、支付失败、订单过期
	 */
	private Integer billstatus;
	/**
	 * 发生前的余额
	 */
	private BigDecimal prebalance;
	/**
	 * 操作IP
	 */
	private String ipaddr;
	/**
	 * 交易校验标识位
	 */
	private String salt;
	/**
	 * 创建时间
	 */
	private java.util.Date createtime;
	/**
	 * 更新时间
	 */
	private java.util.Date modifytime;
	/**
	 * 过期时间
	 */
	private java.util.Date expiretime;
	/**
	 * 交易明细信息
	 */
	private String information;
	
	/**
	 * 是否确认订单状态 0无 1有
	 */
	private Boolean confirmed;
	/**
	 * 
	 */
	private String body;
	
	/**
	 * 订单类型
	 */
	private Integer orderType;
	
	/**
	 *零彩计划id 
	 */
	private Long marketplanId;
	
	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	public java.lang.String getSubject() {
		return this.subject;
	}
	
	public void setSubject(java.lang.String value) {
		this.subject = value;
	}
	public BigDecimal getMoney() {
		return this.money;
	}
	
	public void setMoney(BigDecimal value) {
		this.money = value;
	}
	public BigDecimal getFee() {
		return this.fee;
	}
	
	public void setFee(BigDecimal value) {
		this.fee = value;
	}
	public BigDecimal getRate() {
		return this.rate;
	}
	
	public void setRate(BigDecimal value) {
		this.rate = value;
	}
	public BigDecimal getActualMoney() {
		return this.actualMoney;
	}
	
	public void setActualMoney(BigDecimal value) {
		this.actualMoney = value;
	}
	public java.lang.Long getOrderId() {
		return this.orderId;
	}
	
	public void setOrderId(java.lang.Long value) {
		this.orderId = value;
	}
	
	public java.lang.String getTradeno() {
		return this.tradeno;
	}
	
	public void setTradeno(java.lang.String value) {
		this.tradeno = value;
	}
	public java.lang.Long getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.Long value) {
		this.userId = value;
	}
	public Integer getChannel() {
		return this.channel;
	}
	
	public void setChannel(java.lang.Integer value) {
		this.channel = value;
	}
	public java.util.Date getBilltime() {
		return this.billtime;
	}
	
	public void setBilltime(java.util.Date value) {
		this.billtime = value;
	}
	public Integer getBillstatus() {
		return this.billstatus;
	}
	
	public void setBillstatus(java.lang.Integer value) {
		this.billstatus = value;
	}
	public BigDecimal getPrebalance() {
		return this.prebalance;
	}
	
	public void setPrebalance(BigDecimal value) {
		this.prebalance = value;
	}
	public java.lang.String getIpaddr() {
		return this.ipaddr;
	}
	
	public void setIpaddr(java.lang.String value) {
		this.ipaddr = value;
	}
	public java.lang.String getSalt() {
		return this.salt;
	}
	
	public void setSalt(java.lang.String value) {
		this.salt = value;
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
	public java.util.Date getExpiretime() {
		return this.expiretime;
	}
	
	public void setExpiretime(java.util.Date value) {
		this.expiretime = value;
	}
	public java.lang.String getInformation() {
		return this.information;
	}
	
	public void setInformation(java.lang.String value) {
		this.information = value;
	}
	public java.lang.Boolean getConfirmed() {
		return this.confirmed;
	}
	
	public void setConfirmed(java.lang.Boolean value) {
		this.confirmed = value;
	}
	public java.lang.String getBody() {
		return this.body;
	}
	
	public void setBody(java.lang.String value) {
		this.body = value;
	}
	public Integer getOrderType() {
		return this.orderType;
	}

	public void setOrderType(java.lang.Integer orderType) {
		this.orderType=orderType;
	}

	public Long getMarketplanId() {
		return marketplanId;
	}

	public void setMarketplanId(java.lang.Long marketplanId) {
		this.marketplanId = marketplanId;
	}
	
}