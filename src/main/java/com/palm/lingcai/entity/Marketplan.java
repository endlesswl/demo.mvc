package com.palm.lingcai.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.palm.lingcai.util.UUIDUtils;
public class Marketplan implements Serializable
{
	/**
	 * @Fields serialVersionUID : 
	 */
	private static final long	serialVersionUID	= 1L;
	//alias
//	public static final String	TABLE_ALIAS			= "营销计划";
//	public static final String	ALIAS_ID			= "id";
//	public static final String	ALIAS_MARKETNAME	= "计划名称";
//	public static final String	ALIAS_MARKETDESC	= "计划描述";
//	public static final String	ALIAS_USERID		= "用户ID";
//	public static final String	ALIAS_STARTTIME		= "开始时间";
//	public static final String	ALIAS_ENDTIME		= "结束时间";
//	public static final String	ALIAS_UNITPRICE		= "零彩面额";
//	public static final String	ALIAS_FREQUENCY		= "单个用户赠送频次";
//	public static final String	ALIAS_TOTALPRICE	= "零彩费用预算控制";
//	public static final String	ALIAS_OTHERREWARD	= "其他奖励计划";
//	public static final String	ALIAS_MARKETTYPE	= "营销计划类型";
//	public static final String	ALIAS_STATUS		= "状态";
//	public static final String	ALIAS_PAYFLAG		= "支付标识";
//	public static final String	ALIAS_AUDITFLAG		= "审核标识";
//	public static final String	ALIAS_CREATETIME	= "创建时间";
//	public static final String	ALIAS_MODIFYTIME	= "修改时间";
//	public static final String	ALIAS_LOTTERYTYPE	= "彩种类型";
//	public static final String	ALIAS_LOTTERYNAME	= "彩种名称";
//	public static final String	ALIAS_POSTERPATH	= "活动海报";
	/**
	 *
	 */
	private Long				id;
	/**
	* 拆分规则ID
	*/
	private Long				ruleid;
	/**
	 * 营销计划名称
	 */
	private String				marketName;
	/**
	 * 营销计划描述
	 */
	private String				marketDesc;
	/**
	 * 用户ID
	 */
	private Long				userId;
	/**
	 * 开始时间
	 */
	private java.util.Date		startTime;
	/**
	 * 结束时间
	 */
	private java.util.Date		endTime;
	/**
	 * 彩票单价
	 */
	private BigDecimal			unitPrice;
	/**
	 * 单个用户赠送频次
	 */
	private String				frequency;
	/**
	 * 总价
	 */
	private BigDecimal			totalPrice;
	/**
	 * 其他奖励计划:1 实物奖励;2 礼券奖励;3 现金奖励
	 */
	private Integer				otherReward;
	/**
	 * 营销计划类型:1 信息推送;2 广告问答;3 调查问卷;4 任务式营销;5 积分换零彩
	 * 计划类型，多种类型一","分隔
	 */
	private String				marketType;
	/**
	 * 状态   0 计划未付款 ; 1计划未提交审核; 2计划审核中; 3计划审核失败; 4计划审核通过; 5计划进行中; 6计划已结束; 7逻辑删除
	 */
	private Integer				status;
	/**
	 * 支付标识 0待支付；1支付成功；2支付失败；3已过期
	 */
	private Integer				payFlag;
	/**
	 * 审核标识
	 */
	private Integer				auditFlag;
	/**
	 * 创建时间
	 */
	private java.util.Date		createTime;
	/**
	 * 修改时间
	 */
	private java.util.Date		modifyTime;
	/**
	 * 彩种类型：1体育彩票；2福利彩票
	 */
	private Integer				lotteryType;
	/**
	 * 彩种名称
	 */
	private String				lotteryName;
	/**
	 * 是否清算，0已清算 1未清算
	 */
	private Integer				balanceFixed;
	//////////////////////////////////////关联///////////////////////////////////////////////
	/**
	 * 计划的appId
	 */
	private String				marketAppKey;
	/**
	 * 计划的appSecret
	 */
	private String				marketAppSecret;
	/**
	 * 祝福内容
	 */
	private String				content;
	private String				sender;
	private String				shortUrl;
	/**
	 * 已消费总金额
	 */
	private BigDecimal			consumerAmount;
	/**
	 * 支付方式，1=支付宝方式支付 0=余额支付
	 */
	private int					channel;
	/**
	 * 备注
	 */
	private String				remark;
	
	/**
	 * 后台发布标识
	 */
	private int					pubFlags;
	/**
	 * 活动海报路径
	 */
	private String				posterPath;

	public String getPosterPath()
	{
		return posterPath;
	}

	public void setPosterPath(String posterPath)
	{
		this.posterPath = posterPath;
	}

	public int getPubFlags()
	{
		return pubFlags;
	}

	public void setPubFlags(int pubFlags)
	{
		this.pubFlags = pubFlags;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public java.lang.Long getId()
	{
		return this.id;
	}

	public void setId(java.lang.Long value)
	{
		this.id = value;
	}

	public Long getRuleid()
	{
		return ruleid;
	}

	public void setRuleid(Long ruleid)
	{
		this.ruleid = ruleid;
	}

	public java.lang.String getMarketName()
	{
		return this.marketName;
	}

	public void setMarketName(java.lang.String value)
	{
		this.marketName = value;
	}

	public java.lang.String getMarketDesc()
	{
		return this.marketDesc;
	}

	public void setMarketDesc(java.lang.String value)
	{
		this.marketDesc = value;
	}

	public java.lang.Long getUserId()
	{
		return this.userId;
	}

	public void setUserId(java.lang.Long value)
	{
		this.userId = value;
	}

	public java.util.Date getStartTime()
	{
		return this.startTime;
	}

	public void setStartTime(java.util.Date value)
	{
		this.startTime = value;
	}

	public java.util.Date getEndTime()
	{
		return this.endTime;
	}

	public void setEndTime(java.util.Date value)
	{
		this.endTime = value;
	}

	public BigDecimal getUnitPrice()
	{
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal value)
	{
		this.unitPrice = value;
	}

	public java.lang.String getFrequency()
	{
		return this.frequency;
	}

	public void setFrequency(java.lang.String value)
	{
		this.frequency = value;
	}

	public BigDecimal getTotalPrice()
 {
		if (totalPrice == null) {
			this.totalPrice = BigDecimal.ZERO;
		}
		return this.totalPrice;
	}

	public void setTotalPrice(BigDecimal value)
	{
		this.totalPrice = value;
	}

	public Integer getOtherReward()
	{
		return this.otherReward;
	}

	public void setOtherReward(Integer value)
	{
		this.otherReward = value;
	}

	public String getMarketType()
	{
		return this.marketType;
	}

	public void setMarketType(String value)
	{
		this.marketType = value;
	}

	public Integer getStatus()
	{
		return this.status;
	}

	public void setStatus(Integer value)
	{
		this.status = value;
	}

	public Integer getPayFlag()
	{
		return this.payFlag;
	}

	public void setPayFlag(Integer value)
	{
		this.payFlag = value;
	}

	public Integer getAuditFlag()
	{
		return this.auditFlag;
	}

	public void setAuditFlag(Integer value)
	{
		this.auditFlag = value;
	}

	public java.util.Date getCreateTime()
	{
		return this.createTime;
	}

	public void setCreateTime(java.util.Date value)
	{
		this.createTime = value;
	}

	public java.util.Date getModifyTime()
	{
		return this.modifyTime;
	}

	public void setModifyTime(java.util.Date value)
	{
		this.modifyTime = value;
	}

	public Integer getLotteryType()
	{
		return this.lotteryType;
	}

	public void setLotteryType(Integer value)
	{
		this.lotteryType = value;
	}

	public java.lang.String getLotteryName()
	{
		return this.lotteryName;
	}

	public void setLotteryName(java.lang.String value)
	{
		this.lotteryName = value;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getSender()
	{
		return sender;
	}

	public void setSender(String sender)
	{
		this.sender = sender;
	}

	public BigDecimal getConsumerAmount()
	{
		return consumerAmount;
	}

	public void setConsumerAmount(BigDecimal consumerAmount)
	{
		this.consumerAmount = consumerAmount;
	}

	public int getChannel()
	{
		return channel;
	}

	public void setChannel(int channel)
	{
		this.channel = channel;
	}

	public Integer getBalanceFixed()
	{
		return balanceFixed;
	}

	public void setBalanceFixed(Integer balanceFixed)
	{
		this.balanceFixed = balanceFixed;
	}

	public String getShortUrl()
	{
		return shortUrl;
	}

	public void setShortUrl(String shortUrl)
	{
		this.shortUrl = shortUrl;
	}
	
	public String getMarketAppKey()
	{
		return marketAppKey;
	}

	public void setMarketAppKey(String marketAppKey)
	{
		this.marketAppKey = marketAppKey;
	}

	public String getMarketAppSecret()
	{
		return marketAppSecret;
	}

	public void setMarketAppSecret(String marketAppSecret)
	{
		this.marketAppSecret = marketAppSecret;
	}
}