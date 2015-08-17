package com.palm.lingcai.entity;


import java.math.BigDecimal;

public class PrizeFixed {

    //alias
    public static final String TABLE_ALIAS = "兑奖记录";
    public static final String ALIAS_ID = "ID";
    public static final String ALIAS_USERID = "用户";
    public static final String ALIAS_LOTTERYID = "彩票";
    public static final String ALIAS_USERPRIZEID = "中奖记录";
    public static final String ALIAS_ORDERID = "订单号";
    public static final String ALIAS_FIXEDTYPE = "兑奖方式";
    public static final String ALIAS_FIXEDMONEY = "兑奖金额";
    public static final String ALIAS_FIXEDTIME = "兑奖时间";
    public static final String ALIAS_REMARK = "备注";

    /**
     *
     */
    private Long id;
    /**
     * 用户信息
     */
    private Long userid;
    /**
     * 投注信息
     */
    private Long lotteryid;
    /**
     * 用户中奖记录
     */
    private Long userprizeid;
    /**
     * 订单号
     */
    private Long orderid;
    /**
     * 兑奖方式 1:转账到余额 2:人工领取
     */
    private Integer fixedType;
    /**
     * 兑奖金额
     */
    private BigDecimal fixedMoney;
    /**
     * 兑奖时间
     */
    private java.util.Date fixedtime;
    /**
     * 备注
     */
    private String remark;


    /////////////////////////////中奖相关////////////////////////////////////////////////

    private String gameid;
    private String issueNo;

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

    public Long getLotteryid() {
        return this.lotteryid;
    }

    public void setLotteryid(Long value) {
        this.lotteryid = value;
    }

    public Long getUserprizeid() {
        return this.userprizeid;
    }

    public void setUserprizeid(Long value) {
        this.userprizeid = value;
    }

    public Long getOrderid() {
        return this.orderid;
    }

    public void setOrderid(Long value) {
        this.orderid = value;
    }

    public Integer getFixedType() {
        return this.fixedType;
    }

    public void setFixedType(Integer value) {
        this.fixedType = value;
    }

    public BigDecimal getFixedMoney() {
        return this.fixedMoney;
    }

    public void setFixedMoney(BigDecimal value) {
        this.fixedMoney = value;
    }

    public java.util.Date getFixedtime() {
        return this.fixedtime;
    }

    public void setFixedtime(java.util.Date value) {
        this.fixedtime = value;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String value) {
        this.remark = value;
    }

    public String getIssueNo() {
        return issueNo;
    }

    public void setIssueNo(String issueNo) {
        this.issueNo = issueNo;
    }

    public String getGameid() {
        return gameid;
    }

    public void setGameid(String gameid) {
        this.gameid = gameid;
    }
}