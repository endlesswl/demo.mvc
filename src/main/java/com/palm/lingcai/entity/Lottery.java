package com.palm.lingcai.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Lottery implements Serializable {
	// alias
    public static final String TABLE_ALIAS = "彩票";
    public static final String ALIAS_ID = "ID";
    public static final String ALIAS_MARKETID = "营销计划";
    public static final String ALIAS_GAMEID = "彩种";
    public static final String ALIAS_GAMETYPE = "彩种类型";
    public static final String ALIAS_PROVINCE = "省份";
    public static final String ALIAS_ORDERID = "订单";
    public static final String ALIAS_PALMID = "平台订单";
    public static final String ALIAS_MONEY = "金额";
    public static final String ALIAS_ISSUENO = "期号";
    public static final String ALIAS_STARTTIME = "期开始时间";
    public static final String ALIAS_ENDTIME = "期结束时间";
    public static final String ALIAS_PALMTIME = "平台截止时间";
    public static final String ALIAS_BALL = "投注号码";
    public static final String ALIAS_STATUSCODE = "投注状态";
    public static final String ALIAS_SUCESSFLAG = "出票标识";
    public static final String ALIAS_PALMMSG = "平台报文";
    public static final String ALIAS_REMAINDER = "剩余票数";
    public static final String ALIAS_PRIZETIME = "开奖时间";
    public static final String ALIAS_CREATETIME = "投注时间";
    public static final String ALIAS_MODIFYTIME = "出票时间";
    public static final String ALIAS_ISPRIZE = "中奖状态";
    public static final String ALIAS_REMARK = "备注";

    /**
     *
     */
    private Long id;
    /**
     * 营销计划ID
     */
    private Long marketid;
    /**
     * 彩种
     */
    private String gameid;
    /**
     * 彩种类型，1为体彩，2为福彩
     */
    private Integer gametype;
    /**
     * 出票省份
     */
    private String province = "";
    /**
     * 零彩生成的订单ID
     */
    private String orderid = "";
    /**
     * 平台返回的订单ID
     */
    private String palmid = "";
    /**
     * 投注金额
     */
    private Integer money;
    /**
     * 期号
     */
    private String issueNo = "";
    /**
     * 期次开始时间
     */
    private String starttime = "";
    /**
     * 期次结束时间
     */
    private String endtime = "";
    /**
     * 平台接收订单截止时间
     */
    private String palmtime = "";
    /**
     * 投注号码
     */
    private String ball = "";
    /**
     * 平台返回状态码
     */
    private String statusCode = "";
    /**
     * 成功标识位 1投注下单成功 2投注下单失败 3出票成功 4出票失败 5重试失败 6预售
     */
    private Integer sucessFlag = 1;
    /**
     * 平台返回的投注报文结果
     */
    private String palmMsg = "";
    /**
     * 单注彩票剩余张数
     */
    private Integer remainder;

    /**
     * 开奖时间
     */
    private String prizetime = "";
    /**
     * 购彩时间
     */
    private java.util.Date createtime;
    /**
     * 修改时间
     */
    private java.util.Date modifytime;
    /**
     * 是否中奖
     */
    private Integer isprize = -1;

    /**
     * 零彩投注单价
     */
    private String unitprice;

    /**
     * 备注
     */
    private String remark;

    /**
     * 彩票状态，0：处理中，1：待出票，2：出票成功，3：出票失败
     */
    private Integer lotteryStatus = -1;

    /**
     * 拆分规则 0不拆分，1等额拆分，2随机比例拆分
     */
    private Integer lotterySplit = -1;


    // ////////////////////////////////中奖相关/////////////////////////////////////////////
    /**
     * 开奖号码
     */
    private String prizeball;
    /**
     * 中奖金额
     */
    private BigDecimal prizeMoney;
    /**
     * 税后
     */
    private BigDecimal prizeMoneyAfterTax;
    /**
     * 税金
     */
    private BigDecimal tax;
    /**
     * 分得比例 用于数据返回 不计入数据库
     */
    private BigDecimal gainPercent;


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

    public Long getMarketid() {
        return this.marketid;
    }

    public void setMarketid(Long value) {
        this.marketid = value;
    }

    public String getGameid() {
        return this.gameid;
    }

    public void setGameid(String value) {
        this.gameid = value;
    }

    public Integer getGametype() {
        return this.gametype;
    }

    public void setGametype(Integer value) {
        this.gametype = value;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String value) {
        this.province = value;
    }

    public String getOrderid() {
        return this.orderid;
    }

    public void setOrderid(String value) {
        this.orderid = value;
    }

    public String getPalmid() {
        return this.palmid;
    }

    public void setPalmid(String value) {
        this.palmid = value;
    }

    public Integer getMoney() {
        return this.money;
    }

    public void setMoney(Integer value) {
        this.money = value;
    }

    public String getIssueNo() {
        return this.issueNo;
    }

    public void setIssueNo(String value) {
        this.issueNo = value;
    }

    public String getStarttime() {
        return this.starttime;
    }

    public void setStarttime(String value) {
        this.starttime = value;
    }

    public String getEndtime() {
        return this.endtime;
    }

    public void setEndtime(String value) {
        this.endtime = value;
    }

    public String getPalmtime() {
        return this.palmtime;
    }

    public void setPalmtime(String value) {
        this.palmtime = value;
    }

    public String getBall() {
        return this.ball;
    }

    public void setBall(String value) {
        this.ball = value;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(String value) {
        this.statusCode = value;
    }

    public Integer getSucessFlag() {
        return this.sucessFlag;
    }

    public void setSucessFlag(Integer value) {
        this.sucessFlag = value;
    }

    public String getPalmMsg() {
        return this.palmMsg;
    }

    public void setPalmMsg(String value) {
        this.palmMsg = value;
    }

    public Integer getRemainder() {
        return this.remainder;
    }

    public void setRemainder(Integer value) {
        this.remainder = value;
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

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String value) {
        this.remark = value;
    }

    public String getPrizetime() {
        return prizetime;
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

    public Integer getIsprize() {
        return isprize;
    }

    public void setIsprize(Integer isprize) {
        this.isprize = isprize;
    }

    public String getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(String unitprice) {
        this.unitprice = unitprice;
    }

    public Integer getLotteryStatus() {
        return lotteryStatus;
    }

    public void setLotteryStatus(Integer lotteryStatus) {
        this.lotteryStatus = lotteryStatus;
    }

    public Integer getLotterySplit() {
        return lotterySplit;
    }

    public void setLotterySplit(Integer lotterySplit) {
        this.lotterySplit = lotterySplit;
    }
}