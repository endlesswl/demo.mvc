package com.palm.lingcai.entity;


import java.math.BigDecimal;

/**
 * 开奖表
 * <p/>
 * 开奖表根据期存储
 * 1.如果当期无中奖订单，则只存在一条记录且orderid为空
 * 2.如果当期有中奖订单，则存在多条记录，且每条中奖订单都存在一条记录
 * <p/>
 * 开奖表的中奖信息由定时任务处理，有中奖订单则更新投注表中的中奖状态并插入一条记录
 */
public class Prize {

    //alias
    public static final String TABLE_ALIAS = "中奖记录";
    public static final String ALIAS_ID = "ID";
    public static final String ALIAS_GAMEID = "彩种";
    public static final String ALIAS_GAMETYPE = "彩种类型";
    public static final String ALIAS_ISSUENO = "期号";
    public static final String ALIAS_ORDERID = "系统订单号";
    public static final String ALIAS_PALMID = "平台订单号";
    public static final String ALIAS_PRIZETIME = "开奖时间";
    public static final String ALIAS_PRIZEBALL = "开奖号码";
    public static final String ALIAS_PRIZESTATUS = "中奖状态";
    public static final String ALIAS_PRIZEMONEY = "奖金";
    public static final String ALIAS_PRIZELEVEL = "奖级";
    public static final String ALIAS_PRIZEMONEYAFTERTAX = "税后奖金";
    public static final String ALIAS_TAX = "税金";
    public static final String ALIAS_CREATETIME = "更新时间";
    public static final String ALIAS_PRIZEFILE = "中奖文件";
    public static final String ALIAS_REMARK = "备注";

    /**
     *
     */
    private Long id;
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
     * 系统订单
     */
    private String orderid = "";
    /**
     * 平台唯一ID
     */
    private String palmid = "";
    /**
     * 彩票中心流水号
     */
    private String serialno = "";
    /**
     * 开奖时间
     */
    private String prizetime = "";
    /**
     * 开奖号码
     */
    private String prizeball = "";
    /**
     * 中奖状态 0:未开奖 1:未中奖 2:中奖 3:已派奖
     */
    private Integer prizeStatus;
    /**
     * 中奖金额
     */
    private BigDecimal prizeMoney = new BigDecimal("0.00");
    /**
     * 中奖等级
     */
    private String prizeLevel = "";
    /**
     * 税后中奖金额
     */
    private BigDecimal prizeMoneyAfterTax = new BigDecimal("0.00");
    /**
     * 税金
     */
    private BigDecimal tax = new BigDecimal("0.00");
    /**
     * 更新时间
     */
    private java.util.Date createtime;
    /**
     * 中奖文件
     */
    private String prizefile = "";
    /**
     * 备注
     */
    private String remark;

    public Long getId() {
        return this.id;
    }

    public void setId(Long value) {
        this.id = value;
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

    public String getPrizetime() {
        return this.prizetime;
    }

    public void setPrizetime(String value) {
        this.prizetime = value;
    }

    public String getPrizeball() {
        return this.prizeball;
    }

    public void setPrizeball(String value) {
        this.prizeball = value;
    }

    public Integer getPrizeStatus() {
        return this.prizeStatus;
    }

    public void setPrizeStatus(Integer value) {
        this.prizeStatus = value;
    }

    public BigDecimal getPrizeMoney() {
        return this.prizeMoney;
    }

    public void setPrizeMoney(BigDecimal value) {
        this.prizeMoney = value;
    }

    public String getPrizeLevel() {
        return this.prizeLevel;
    }

    public void setPrizeLevel(String value) {
        this.prizeLevel = value;
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

    public java.util.Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(java.util.Date value) {
        this.createtime = value;
    }

    public String getPrizefile() {
        return prizefile;
    }

    public void setPrizefile(String prizefile) {
        this.prizefile = prizefile;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String value) {
        this.remark = value;
    }

    public String getSerialno() {
        return serialno;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }
}