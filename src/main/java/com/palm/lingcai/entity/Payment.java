package com.palm.lingcai.entity;

import java.math.BigDecimal;
public class Payment
{
	//alias
	public static final String	TABLE_ALIAS			= "支付";
	public static final String	ALIAS_ID			= "id";
	public static final String	ALIAS_SUBJECT		= "商品名称";
	public static final String	ALIAS_AMOUNT		= "商品金额";
	public static final String	ALIAS_FEE			= "清算费用";
	public static final String	ALIAS_RATE			= "商品折扣";
	public static final String	ALIAS_ACTUALAMOUNT	= "支付金额";
	public static final String	ALIAS_ORDERID		= "订单号";
	public static final String	ALIAS_TRADENO		= "第三方交易流水号";
	public static final String	ALIAS_METHOD		= "支付方式";
	public static final String	ALIAS_METHODEXT		= "支付方式扩展";
	public static final String	ALIAS_STATUS		= "交易状态";
	public static final String	ALIAS_PREBALANCE	= "发生前的余额";
	public static final String	ALIAS_IPADDR		= "操作IP";
	public static final String	ALIAS_SALT			= "交易校验标识位";
	public static final String	ALIAS_CREATETIME	= "创建时间";
	public static final String	ALIAS_MODIFYTIME	= "更新时间";
	public static final String	ALIAS_EXPIRETIME	= "过期时间";
	public static final String	ALIAS_FIXEDTIME		= "交易完成时间";
	public static final String	ALIAS_INFORMATION	= "交易明细信息";
	public static final String	ALIAS_CONFIRMED		= "是否确认";
	public static final String	ALIAS_REMARK		= "产品备注";
	public static final String	ALIAS_USERID		= "用户ID";
	public static final String	ALIAS_MARKETID		= "零彩计划id";
	public static final String	ALIAS_DELETEFLAG	= "删除标识";
	/**
	 * 
	 */
	private Long				id;
	/**
	 * 商品名称
	 */
	private String				subject;
	/**
	 * 商品金额
	 */
	private BigDecimal			amount;
	/**
	 * 清算费用
	 */
	private BigDecimal			fee;
	/**
	 * 商品折扣
	 */
	private BigDecimal			rate;
	/**
	 * 支付金额
	 */
	private BigDecimal			actualAmount;
	/**
	 * 定单号
	 */
	private Long				orderid;
	/**
	 * 第三方交易流水号
	 */
	private String				tradeno;
	/**
	 * 支付方式，如支付宝、银联还是其他,  0(账户余额) ，1(支付宝)  ,2(银联) ，3(微信财富通) ,4(零彩币),5(零彩卡),6(零彩券)
	 */
	private Integer				method;
	/**
	 * 支付方式扩展
	 */
	private String				methodExt;
	/**
	 * 交易状态,0等待支付、1支付成功、2支付失败、3订单过期
	 */
	private Integer				status;
	/**
	 * 发生前的余额
	 */
	private BigDecimal			prebalance;
	/**
	 * 操作IP
	 */
	private String				ipaddr;
	/**
	 * 交易校验标识位
	 */
	private String				salt;
	/**
	 * 创建时间
	 */
	private java.util.Date		createtime;
	/**
	 * 更新时间
	 */
	private java.util.Date		modifytime;
	/**
	 * 过期时间
	 */
	private java.util.Date		expiretime;
	/**
	 * 交易完成时间
	 */
	private java.util.Date		fixedtime;
	/**
	 * 交易明细信息
	 */
	private String				information;
	/**
	 * 是否确认订单状态 
	 */
	private Integer				confirmed;
	/**
	 * 产品备注
	 */
	private String				remark;
	/**
	 * 用户ID
	 */
	private Long				userid;
	/**
	 * 零彩计划id 默认为0 
	 */
	private Long				marketid;
	/**
	 * 删除标识 
	 */
	private Integer				deleteFlag;

	public Long getId()
	{
		return this.id;
	}

	public void setId(Long value)
	{
		this.id = value;
	}

	public String getSubject()
	{
		return this.subject;
	}

	public void setSubject(String value)
	{
		this.subject = value;
	}

	public BigDecimal getAmount()
	{
		return this.amount;
	}

	public void setAmount(BigDecimal value)
	{
		this.amount = value;
	}

	public BigDecimal getFee()
	{
		return this.fee;
	}

	public void setFee(BigDecimal value)
	{
		this.fee = value;
	}

	public BigDecimal getRate()
	{
		return this.rate;
	}

	public void setRate(BigDecimal value)
	{
		this.rate = value;
	}

	public BigDecimal getActualAmount()
	{
		return this.actualAmount;
	}

	public void setActualAmount(BigDecimal value)
	{
		this.actualAmount = value;
	}

	public Long getOrderid()
	{
		return this.orderid;
	}

	public void setOrderid(Long value)
	{
		this.orderid = value;
	}

	public String getTradeno()
	{
		return this.tradeno;
	}

	public void setTradeno(String value)
	{
		this.tradeno = value;
	}

	public Integer getMethod()
	{
		return this.method;
	}

	public void setMethod(Integer value)
	{
		this.method = value;
	}

	public String getMethodExt()
	{
		return this.methodExt;
	}

	public void setMethodExt(String value)
	{
		this.methodExt = value;
	}

	public Integer getStatus()
	{
		return this.status;
	}

	public void setStatus(Integer value)
	{
		this.status = value;
	}

	public BigDecimal getPrebalance()
	{
		return this.prebalance;
	}

	public void setPrebalance(BigDecimal value)
	{
		this.prebalance = value;
	}

	public String getIpaddr()
	{
		return this.ipaddr;
	}

	public void setIpaddr(String value)
	{
		this.ipaddr = value;
	}

	public String getSalt()
	{
		return this.salt;
	}

	public void setSalt(String value)
	{
		this.salt = value;
	}

	public java.util.Date getCreatetime()
	{
		return this.createtime;
	}

	public void setCreatetime(java.util.Date value)
	{
		this.createtime = value;
	}

	public java.util.Date getModifytime()
	{
		return this.modifytime;
	}

	public void setModifytime(java.util.Date value)
	{
		this.modifytime = value;
	}

	public java.util.Date getExpiretime()
	{
		return this.expiretime;
	}

	public void setExpiretime(java.util.Date value)
	{
		this.expiretime = value;
	}

	public java.util.Date getFixedtime()
	{
		return this.fixedtime;
	}

	public void setFixedtime(java.util.Date value)
	{
		this.fixedtime = value;
	}

	public String getInformation()
	{
		return this.information;
	}

	public void setInformation(String value)
	{
		this.information = value;
	}

	public Integer getConfirmed()
	{
		return this.confirmed;
	}

	public void setConfirmed(Integer value)
	{
		this.confirmed = value;
	}

	public String getRemark()
	{
		return this.remark;
	}

	public void setRemark(String value)
	{
		this.remark = value;
	}

	public Long getUserid()
	{
		return this.userid;
	}

	public void setUserid(Long value)
	{
		this.userid = value;
	}

	public Long getMarketid()
	{
		return this.marketid;
	}

	public void setMarketid(Long value)
	{
		this.marketid = value;
	}

	public Integer getDeleteFlag()
	{
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer value)
	{
		this.deleteFlag = value;
	}
}