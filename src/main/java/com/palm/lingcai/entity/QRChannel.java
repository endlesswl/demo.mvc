package com.palm.lingcai.entity;

public class QRChannel
{
	//alias
	public static final String	TABLE_ALIAS			= "Qrchannel";
	public static final String	ALIAS_ID			= "id";
	public static final String	ALIAS_MARKETID		= "marketid";
	public static final String	ALIAS_NAME			= "name";
	public static final String	ALIAS_DESCRIPTION	= "description";
	public static final String	ALIAS_QRPATH		= "qrpath";
	public static final String	ALIAS_CREATETIME	= "createtime";
	public static final String	ALIAS_SHORTURL		= "shorturl";
	public static final String	ALIAS_LOGOPATH		= "logopath";
	public static final String	ALIAS_QRCONTENT		= "qrcontent";
	public static final String	ALIAS_FORECOLOR		= "forecolor";
	public static final String	ALIAS_BACKCOLOR		= "backcolor";
	/**
	 *
	 */
	private Long				id;
	/**
	 * 营销计划ID
	 */
	private Long				marketid;
	/**
	 * 分发渠道名称
	 */
	private String				name;
	/**
	 * 描述
	 */
	private String				description;
	/**
	 * 二维码保存路径
	 */
	private String				qrpath;
	/**
	 * 生成时间
	 */
	private java.util.Date		createtime;
	/**
	 * 短网址
	 */
	private String				shorturl;
	/**
	 * LOGO图片路径
	 */
	private String				logopath;
	/**
	 * 二维码内容
	 */
	private String				qrcontent;
	/**
	 * 二维码前景色
	 */
	private String				forecolor;
	/**
	 * 二维码背景色
	 */
	private String				backcolor;
	/**
	 * 营销计划
	 */
	private Marketplan			marketplan;

	public String getForecolor()
	{
		return forecolor;
	}

	public void setForecolor(String forecolor)
	{
		this.forecolor = forecolor;
	}

	public String getBackcolor()
	{
		return backcolor;
	}

	public void setBackcolor(String backcolor)
	{
		this.backcolor = backcolor;
	}

	public java.lang.Long getId()
	{
		return this.id;
	}

	public void setId(java.lang.Long value)
	{
		this.id = value;
	}

	public java.lang.Long getMarketid()
	{
		return this.marketid;
	}

	public void setMarketid(java.lang.Long value)
	{
		this.marketid = value;
	}

	public java.lang.String getName()
	{
		return this.name;
	}

	public void setName(java.lang.String value)
	{
		this.name = value;
	}

	public java.lang.String getDescription()
	{
		return this.description;
	}

	public void setDescription(java.lang.String value)
	{
		this.description = value;
	}

	public java.lang.String getQrpath()
	{
		return this.qrpath;
	}

	public void setQrpath(java.lang.String value)
	{
		this.qrpath = value;
	}

	public java.util.Date getCreatetime()
	{
		return this.createtime;
	}

	public void setCreatetime(java.util.Date value)
	{
		this.createtime = value;
	}

	public java.lang.String getShorturl()
	{
		return this.shorturl;
	}

	public void setShorturl(java.lang.String value)
	{
		this.shorturl = value;
	}

	public String getLogopath()
	{
		return logopath;
	}

	public void setLogopath(String logopath)
	{
		this.logopath = logopath;
	}

	public String getQrcontent()
	{
		return qrcontent;
	}

	public void setQrcontent(String qrcontent)
	{
		this.qrcontent = qrcontent;
	}

	public Marketplan getMarketplan()
	{
		return marketplan;
	}

	public void setMarketplan(Marketplan marketplan)
	{
		this.marketplan = marketplan;
	}
}