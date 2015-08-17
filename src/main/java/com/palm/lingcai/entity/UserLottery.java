package com.palm.lingcai.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class UserLottery implements Serializable {

	/**
	 * @Fields serialVersionUID :
	 */

	private static final long serialVersionUID = 1L;
	// alias
	public static final String TABLE_ALIAS = "领彩记录";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_USERID = "用户ID";
	public static final String ALIAS_USERNAME = "用户名";
	public static final String ALIAS_LOTTERYID = "彩票ID";
	public static final String ALIAS_MARKETID = "计划ID";
	public static final String ALIAS_MONEY = "金额";
	public static final String ALIAS_REQHEAD = "请求头";
	public static final String ALIAS_REFERER = "来路";
	public static final String ALIAS_SOURCE = "扫码渠道";
	public static final String ALIAS_SCANIP = "扫码IP";
	public static final String ALIAS_CREATETIME = "领彩时间";
	public static final String ALIAS_REMARK = "备注";

	/**
     *
     */
	private Long id;
	/**
	 * 用户ID
	 */
	private Long userid;
	/**
	 * 用户名，使用手机号码做为用户名
	 */
	private String username;
	/**
	 * 购彩ID
	 */
	private Long lotteryid;

	/**
	 * 投注号码
	 */
	private String ball;

	/**
	 * 营销计划ID
	 */
	private Long marketid;
	
	/**
	 * 抽奖ID
	 */
	private Integer drawid;
	
	/**
	 * 彩票金额
	 */
	private BigDecimal money;
	/**
	 * 扫码请求头信息
	 */
	private String reqHead;
	/**
	 * 来路
	 */
	private String referer;
	/**
	 * 访问渠道
	 */
	private String source;

	/**
	 * 送祝福人
	 */
	private String author;
	/**
	 * 访问IP
	 */
	private String scanip;
	/**
	 * 访问时间
	 */
	private java.util.Date createtime;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 用户组
	 */
	private Long groupid = 0L;
	/**
	 * 选择的战队
	 */
	private String cid = "";

	/**
	 * 中奖状态 0:发起人 1:参与人
	 */
	private Integer isSend;

	/**
	 * 订单
	 */
	private String orderid;
	/**
	 * 流水号
	 */
	private String serialno;
	/**
	 * 渠道订单号
	 */
	private String channelOrder;

	private String notifyStatus;
	private Integer notifyTimes;
	private String notifyWinStatus;
	private Integer notifyWinTimes;

	// ////////////////////////////////关联字段//////////////////////////////////////////////////////
	/**
	 * 开奖时间
	 */
	private String prizetime;
	/**
	 * 营销计划名称
	 */
	private String marketName;

	/**
	 * 中奖状态 0:未中奖 1:中奖
	 */
	private Integer isprize;

	/**
	 * 投注标识
	 */
	private String sucessFlag;

	/**
	 * 彩种
	 */
	private String gameid;

	/**
	 * 期号
	 */
	private String issueNo;

	/**
	 * 分得比例
	 */
	private BigDecimal gainPercent;
	/**
	 * 中奖税后金额
	 * 
	 * @return
	 */
	private Double prizeMoneyAfterTax;

	public Double getPrizeMoneyAfterTax() {
		return prizeMoneyAfterTax;
	}

	public void setPrizeMoneyAfterTax(Double prizeMoneyAfterTax) {
		this.prizeMoneyAfterTax = prizeMoneyAfterTax;
	}

	public BigDecimal getGainPercent() {
		return gainPercent;
	}

	public void setGainPercent(BigDecimal gainPercent) {
		this.gainPercent = gainPercent;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long value) {
		this.id = value;
	}

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long value) {
		this.userid = value;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getLotteryid() {
		return this.lotteryid;
	}

	public void setLotteryid(Long value) {
		this.lotteryid = value;
	}

	public Long getMarketid() {
		return marketid;
	}

	public Integer getDrawid() {
		return drawid;
	}

	public void setDrawid(Integer drawid) {
		this.drawid = drawid;
	}

	public void setMarketid(Long marketid) {
		this.marketid = marketid;
	}

	public BigDecimal getMoney() {
		return this.money;
	}

	public void setMoney(BigDecimal value) {
		this.money = value;
	}

	public String getReqHead() {
		return this.reqHead;
	}

	public void setReqHead(String value) {
		this.reqHead = value;
	}

	public String getReferer() {
		return this.referer;
	}

	public void setReferer(String value) {
		this.referer = value;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String value) {
		this.source = value;
	}

	public String getScanip() {
		return scanip;
	}

	public void setScanip(String scanip) {
		this.scanip = scanip;
	}

	public java.util.Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String value) {
		this.remark = value;
	}

	public String getBall() {
		return ball;
	}

	public void setBall(String ball) {
		this.ball = ball;
	}

	public String getMarketName() {
		return marketName;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}

	public Integer getIsprize() {
		return isprize;
	}

	public void setIsprize(Integer isprize) {
		this.isprize = isprize;
	}

	public String getGameid() {
		return gameid;
	}

	public void setGameid(String gameid) {
		this.gameid = gameid;
	}

	public String getIssueNo() {
		return issueNo;
	}

	public void setIssueNo(String issueNo) {
		this.issueNo = issueNo;
	}

	public String getSucessFlag() {
		return sucessFlag;
	}

	public void setSucessFlag(String sucessFlag) {
		this.sucessFlag = sucessFlag;
	}

	public String getPrizetime() {
		return prizetime;
	}

	public void setPrizetime(String prizetime) {
		this.prizetime = prizetime;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getIsSend() {
		return isSend;
	}

	public void setIsSend(Integer isSend) {
		this.isSend = isSend;
	}

	public Long getGroupid() {
		return groupid;
	}

	public void setGroupid(Long groupid) {
		this.groupid = groupid;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String getChannelOrder() {
		return channelOrder;
	}

	public void setChannelOrder(String channelOrder) {
		this.channelOrder = channelOrder;
	}

	public String getNotifyStatus() {
		return notifyStatus;
	}

	public void setNotifyStatus(String notifyStatus) {
		this.notifyStatus = notifyStatus;
	}

	public String getNotifyWinStatus() {
		return notifyWinStatus;
	}

	public void setNotifyWinStatus(String notifyWinStatus) {
		this.notifyWinStatus = notifyWinStatus;
	}

	public Integer getNotifyTimes() {
		return notifyTimes;
	}

	public void setNotifyTimes(Integer notifyTimes) {
		this.notifyTimes = notifyTimes;
	}

	public Integer getNotifyWinTimes() {
		return notifyWinTimes;
	}

	public void setNotifyWinTimes(Integer notifyWinTimes) {
		this.notifyWinTimes = notifyWinTimes;
	}

}