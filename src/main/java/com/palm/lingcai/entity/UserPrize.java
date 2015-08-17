package com.palm.lingcai.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class UserPrize implements Serializable {

	// alias
	public static final String TABLE_ALIAS = "中奖记录";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_USERID = "用户";
	public static final String ALIAS_PRIZEID = "中奖记录";
	public static final String ALIAS_GAMEID = "彩种";
	public static final String ALIAS_GAMETYPE = "彩种类型";
	public static final String ALIAS_ISSUENO = "期号";
	public static final String ALIAS_PRIZEMONEY = "中奖金额";
	public static final String ALIAS_PRIZEMONEYAFTERTAX = "税后奖金";
	public static final String ALIAS_TAX = "税金";
	public static final String ALIAS_ISFIXED = "是否兑奖";
	public static final String ALIAS_CREATETIME = "更新时间";
	public static final String ALIAS_REMARK = "备注";

	/**
     *
     */
	private Long id;
	/**
	 * 用户
	 */
	private Long userid;
	/**
	 * 中奖记录
	 */
	private Long prizeid;

	/**
	 * 投注记录
	 */
	private Long lotteryid;
	/**
	 * 彩种
	 */
	private String gameid;
	/**
	 * 彩种类型
	 */
	private Integer gametype;
	/**
	 * 期号
	 */
	private String issueNo;
	/**
	 * 拆分后的奖金
	 */
	private BigDecimal prizeMoney = new BigDecimal("0.00");
	/**
	 * 拆分后的税后奖金
	 */
	private BigDecimal prizeMoneyAfterTax = new BigDecimal("0.00");
	/**
	 * 拆分后的税金
	 */
	private BigDecimal tax = new BigDecimal("0.00");
	/**
	 * 是否兑奖 0否 1是
	 */
	private Integer isfixed = 0;
	/**
	 * 更新时间
	 */
	private java.util.Date createtime;
	/**
	 * 备注
	 */
	private String remark;

	// //////////////////////////关联///////////////////////////////////////
	/** 关联字段 **/
	private String ref_username;
	/** 关联字段 **/
	private String ref_ball;
	/** 关联字段 **/
	private String ref_issueNo;
	/** 关联字段 **/
	private String ref_gameid;
	/** 关联字段 **/
	private String ref_marketName;

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

	public Long getPrizeid() {
		return this.prizeid;
	}

	public void setPrizeid(Long value) {
		this.prizeid = value;
	}

	public Long getLotteryid() {
		return lotteryid;
	}

	public void setLotteryid(Long lotteryid) {
		this.lotteryid = lotteryid;
	}

	public String getGameid() {
		return this.gameid;
	}

	public void setGameid(String value) {
		this.gameid = value;
	}

	public Integer getGametype() {
		return gametype;
	}

	public void setGametype(Integer gametype) {
		this.gametype = gametype;
	}

	public String getIssueNo() {
		return this.issueNo;
	}

	public void setIssueNo(String value) {
		this.issueNo = value;
	}

	public BigDecimal getPrizeMoney() {
		return this.prizeMoney;
	}

	public void setPrizeMoney(BigDecimal value) {
		this.prizeMoney = value;
	}

	public BigDecimal getPrizeMoneyAfterTax() {
		return this.prizeMoneyAfterTax;
	}

	public void setPrizeMoneyAfterTax(BigDecimal value) {
		this.prizeMoneyAfterTax = value;
	}

	public BigDecimal getTax() {
		return this.tax;
	}

	public void setTax(BigDecimal value) {
		this.tax = value;
	}

	public Integer getIsfixed() {
		return this.isfixed;
	}

	public void setIsfixed(Integer value) {
		this.isfixed = value;
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

	public String getRef_username() {
		return ref_username;
	}

	public void setRef_username(String ref_username) {
		this.ref_username = ref_username;
	}

	public String getRef_ball() {
		return ref_ball;
	}

	public void setRef_ball(String ref_ball) {
		this.ref_ball = ref_ball;
	}

	public String getRef_issueNo() {
		return ref_issueNo;
	}

	public void setRef_issueNo(String ref_issueNo) {
		this.ref_issueNo = ref_issueNo;
	}

	public String getRef_gameid() {
		return ref_gameid;
	}

	public void setRef_gameid(String ref_gameid) {
		this.ref_gameid = ref_gameid;
	}

	public String getRef_marketName() {
		return ref_marketName;
	}

	public void setRef_marketName(String ref_marketName) {
		this.ref_marketName = ref_marketName;
	}


}