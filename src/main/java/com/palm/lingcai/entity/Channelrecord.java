package com.palm.lingcai.entity;

import java.math.BigDecimal;
import java.util.Date;


public class Channelrecord {

	//alias
	public static final String TABLE_ALIAS = "积分兑换";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_CHANNELID = "渠道ID";
	public static final String ALIAS_INTEGRAL = "积分";
	public static final String ALIAS_MOBILE ="手机号";
	public static final String ALIAS_LOTTERYID = "彩票ID";
	public static final String ALIAS_ORDERID = "订单ID";
	public static final String ALIAS_GAMEID = "彩种";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATETIME = "更新时间";
	public static final String ALIAS_REMARK = "备注";
	public static final String ALIAS_CHANNELUSEID = "渠道用户ID";

	/**
	 * 
	 */
	private Long id;
	/**
	 * 渠道id
	 */
	private Long channelid;
	/**
	 * 积分
	 */
	private String integral;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 彩票id
	 */
	private Long lotteryid;
	/**
	 * 订单号
	 */
	private String orderid;
	/**
	 * 彩种
	 */
	private String gameid;
	/**
	 * 创建时间
	 */
	private java.util.Date createtime;
	/**
	 *修改时间
	 */
	private java.util.Date updatetime;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 异步通知地址
	 */
	private String notifyurl;
	/**
	 *异步通知次数
	 */
	private Integer notifytimes=0;
	/**
	 *异步通知时间
	 */
	private Date notitytime;
	/**
	 *异步通知标识
	 */
	private Boolean notifyflag=false;
	/**
	 *交易号
	 */
	private String tradeno;
	/**
	 * 渠道用户ID
	 */
	private String channelUseId;
	/**
	 * 读取标识 0:未读取;1:已读取
	 */
	private Integer readFlags;

	/**
	 * 用户ID
	 */
	private Long userid;

	private Lottery lottery;
	private String issueNo;//期号
	private String prizetime;//开奖时间
	private String prizeball;//开奖号码
	private Integer isprize;//是否中奖
	private BigDecimal prizeMoney;//中奖金额
	private BigDecimal prizeMoneyAfterTax;//中间税后奖金
	private BigDecimal tax;//税金
	
	public String getChannelUseId()
	{
		return channelUseId;
	}

	public void setChannelUseId(String channelUseId)
	{
		this.channelUseId = channelUseId;
	}

	public String getIssueNo() {
		return issueNo;
	}

	public void setIssueNo(String issueNo) {
		this.issueNo = issueNo;
	}

	public String getPrizetime() {
		return prizetime;
	}

	public Integer getIsprize() {
		return isprize;
	}

	public void setIsprize(Integer isprize) {
		this.isprize = isprize;
	}

	public void setPrizetime(String prizetime) {
		this.prizetime = prizetime;
	}

	public String getPrizeball() {
		return prizeball;
	}

	public void setPrizeball(String prizeball) {
		this.prizeball = prizeball;
	}

	public BigDecimal getPrizeMoney() {
		return prizeMoney;
	}

	public void setPrizeMoney(BigDecimal prizeMoney) {
		this.prizeMoney = prizeMoney;
	}

	public BigDecimal getPrizeMoneyAfterTax() {
		return prizeMoneyAfterTax;
	}

	public void setPrizeMoneyAfterTax(BigDecimal prizeMoneyAfterTax) {
		this.prizeMoneyAfterTax = prizeMoneyAfterTax;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public Lottery getLottery() {
		return lottery;
	}

	public void setLottery(Lottery lottery) {
		this.lottery = lottery;
	}

	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	public java.lang.Long getChannelid() {
		return this.channelid;
	}
	
	public void setChannelid(java.lang.Long value) {
		this.channelid = value;
	}
	public java.lang.String getIntegral() {
		return this.integral;
	}
	
	public void setIntegral(java.lang.String value) {
		this.integral = value;
	}
	public java.lang.Long getLotteryid() {
		return this.lotteryid;
	}
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setLotteryid(java.lang.Long value) {
		this.lotteryid = value;
	}
	public java.lang.String getOrderid() {
		return this.orderid;
	}
	
	public void setOrderid(java.lang.String value) {
		this.orderid = value;
	}
	public java.lang.String getGameid() {
		return this.gameid;
	}
	
	public void setGameid(java.lang.String value) {
		this.gameid = value;
	}
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	public java.util.Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(java.util.Date updatetime) {
		this.updatetime = updatetime;
	}

	public java.lang.String getRemark() {
		return this.remark;
	}
	
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}

	public Integer getNotifytimes() {
		return notifytimes;
	}

	public void setNotifytimes(Integer notifytimes) {
		this.notifytimes = notifytimes;
	}

	public Date getNotitytime() {
		return notitytime;
	}

	public void setNotitytime(Date notitytime) {
		this.notitytime = notitytime;
	}

	public Boolean getNotifyflag() {
		return notifyflag;
	}

	public void setNotifyflag(Boolean notifyflag) {
		this.notifyflag = notifyflag;
	}

	public String getNotifyurl() {
		return notifyurl;
	}

	public void setNotifyurl(String notifyurl) {
		this.notifyurl = notifyurl;
	}

	public String getTradeno() {
		return tradeno;
	}

	public void setTradeno(String tradeno) {
		this.tradeno = tradeno;
	}
	
	public Long getUserid()
	{
		return userid;
	}

	public void setUserid(Long userid)
	{
		this.userid = userid;
	}
	
	public Integer getReadFlags()
	{
		return readFlags;
	}

	public void setReadFlags(Integer readFlags)
	{
		this.readFlags = readFlags;
	}
}