package com.palm.lingcai.entity;

import java.math.BigDecimal;
/**
 * 渠道用户
 */
public class ChannelUser
{
	//alias
	public static final String	TABLE_ALIAS			= "ChannelUser";
	public static final String	ALIAS_ID			= "id";
	public static final String	ALIAS_CHANNELID		= "channelid";
	public static final String	ALIAS_MARKETID		= "marketid";
	public static final String	ALIAS_ORDERID		= "orderid";
	public static final String	ALIAS_CUSERID		= "cuserid";
	public static final String	ALIAS_NAME			= "name";
	public static final String	ALIAS_MOBILE		= "mobile";
	public static final String	ALIAS_CARDTYPE		= "cardtype";
	public static final String	ALIAS_CARDNO		= "cardno";
	public static final String	ALIAS_OPENID		= "openid";
	public static final String	ALIAS_GAMEID		= "gameid";
	public static final String	ALIAS_RULE			= "rule";
	public static final String	ALIAS_MONEY			= "money";
	public static final String	ALIAS_USERID		= "userid";
	public static final String	ALIAS_STATUS		= "status";
	public static final String	ALIAS_STARTTIME		= "starttime";
	public static final String	ALIAS_EXPIRETIME	= "expiretime";
	public static final String	ALIAS_CREATETIME	= "createtime";
	public static final String	ALIAS_MODIFYTIME	= "modifytime";
	/**
	 * 下单ID
	 */
	private Long				id;
	/**
	 * 渠道ID
	 */
	private Long				channelid;
	/**
	 * 营销计划ID
	 */
	private Long				marketid;
	/**
	 * 渠道订单ID
	 */
	private String				orderid;
	/**
	 * 渠道用户ID
	 */
	private String				cuserid;
	/**
	 * 用户姓名
	 */
	private String				name;
	/**
	 * 用户手机号
	 */
	private String				mobile;
	/**
	 * 证件类型
	 */
	private Integer				cardtype;
	/**
	 * 证件号
	 */
	private String				cardno;
	/**
	 * 微信用户唯一标识
	 */
	private String				openid;
	/**
	 * 彩种
	 */
	private String				gameid;
	/**
	 * 规则 1:1次 2:1次/日 3:1次/周 4:1次/月 5:不限
	 */
	private Integer				rule;
	/**
	 * 彩票面额(元)
	 */
	private BigDecimal			money;
	/**
	 * 零彩用户ID
	 */
	private Long				userid;
	/**
	 * 状态 0:正常 1:失效
	 */
	private Integer				status;
	/**
	 * 启动时间
	 */
	private java.util.Date		starttime;
	/**
	 * 失效时间
	 */
	private java.util.Date		expiretime;
	/**
	 * 创建时间
	 */
	private java.util.Date		createtime;
	/**
	 * 更新时间
	 */
	private java.util.Date		modifytime;

	public java.lang.Long getMarketid()
	{
		return this.marketid;
	}

	public void setMarketid(java.lang.Long value)
	{
		this.marketid = value;
	}

	public java.lang.Long getId()
	{
		return this.id;
	}

	public void setId(java.lang.Long value)
	{
		this.id = value;
	}

	public java.lang.Long getChannelid()
	{
		return this.channelid;
	}

	public void setChannelid(java.lang.Long value)
	{
		this.channelid = value;
	}

	public java.lang.String getOrderid()
	{
		return this.orderid;
	}

	public void setOrderid(java.lang.String value)
	{
		this.orderid = value;
	}

	public java.lang.String getCuserid()
	{
		return this.cuserid;
	}

	public void setCuserid(java.lang.String value)
	{
		this.cuserid = value;
	}

	public java.lang.String getName()
	{
		return this.name;
	}

	public void setName(java.lang.String value)
	{
		this.name = value;
	}

	public java.lang.String getMobile()
	{
		return this.mobile;
	}

	public void setMobile(java.lang.String value)
	{
		this.mobile = value;
	}

	public Integer getCardtype()
	{
		return this.cardtype;
	}

	public void setCardtype(Integer value)
	{
		this.cardtype = value;
	}

	public java.lang.String getCardno()
	{
		return this.cardno;
	}

	public void setCardno(java.lang.String value)
	{
		this.cardno = value;
	}

	public java.lang.String getOpenid()
	{
		return this.openid;
	}

	public void setOpenid(java.lang.String value)
	{
		this.openid = value;
	}

	public java.lang.String getGameid()
	{
		return this.gameid;
	}

	public void setGameid(java.lang.String value)
	{
		this.gameid = value;
	}

	public Integer getRule()
	{
		return this.rule;
	}

	public void setRule(Integer value)
	{
		this.rule = value;
	}

	public BigDecimal getMoney()
	{
		return this.money;
	}

	public void setMoney(BigDecimal value)
	{
		this.money = value;
	}

	public java.lang.Long getUserid()
	{
		return this.userid;
	}

	public void setUserid(java.lang.Long value)
	{
		this.userid = value;
	}

	public Integer getStatus()
	{
		return this.status;
	}

	public void setStatus(Integer value)
	{
		this.status = value;
	}

	public java.util.Date getStarttime()
	{
		return this.starttime;
	}

	public void setStarttime(java.util.Date value)
	{
		this.starttime = value;
	}

	public java.util.Date getExpiretime()
	{
		return this.expiretime;
	}

	public void setExpiretime(java.util.Date value)
	{
		this.expiretime = value;
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
}