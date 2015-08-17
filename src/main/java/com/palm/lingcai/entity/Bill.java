package com.palm.lingcai.entity;


import java.math.BigDecimal;

public class Bill {

    //alias
    public static final String TABLE_ALIAS = "Bill";
    public static final String ALIAS_ID = "id";
    public static final String ALIAS_ORDERID = "orderid";
    public static final String ALIAS_SERIALNO = "serialno";
    public static final String ALIAS_TRADENO = "tradeno";
    public static final String ALIAS_AMOUNT = "amount";
    public static final String ALIAS_USERID = "userid";
    public static final String ALIAS_BILLTIME = "billtime";
    public static final String ALIAS_INDECR = "indecr";
    public static final String ALIAS_BILLCHANNEL = "billchannel";
    public static final String ALIAS_BILLRET = "billret";
    public static final String ALIAS_PREBALANCE = "prebalance";
    public static final String ALIAS_IPADDR = "ipaddr";
    public static final String ALIAS_SALT = "salt";
    public static final String ALIAS_PACKETS = "packets";
    public static final String ALIAS_CREATETIME = "createtime";
    public static final String ALIAS_MODIFYTIME = "modifytime";
    public static final String ALIAS_EXPIRETIME = "expiretime";
    public static final String ALIAS_INFORMATION = "information";
    public static final String ALIAS_LOCKED = "locked";
    public static final String ALIAS_CONFIRMED = "confirmed";
    public static final String ALIAS_AFTERBALANCE = "afterbalance";
    /**
     *
     */
    private Long id;
    /**
     * 定单号
     */
    private Long orderid;
    /**
     * 交易流水号
     */
    private Long serialno;
    /**
     * 第三方交易流水号
     */
    private String tradeno;
    /**
     * 操作的金额，分正负
     */
    private BigDecimal amount;
    /**
     * 用户ID
     */
    private Long userid;
    /**
     * 交易时间
     */
    private Long billtime;
    /**
     * 余额变动方式，加或减
     */
    private Integer indecr;
    /**
     * 交易来源，如充值，支付，回滚，结算还是其他
     */
    private Integer billchannel;
    /**
     * 交易返回码,未处理、成功、失败
     */
    private Integer billret;
    /**
     * 发生前的余额
     */
    private BigDecimal prebalance;
    /**
     * 操作IP
     */
    private String ipaddr = "";
    /**
     * 交易校验标识位
     */
    private String salt = "";
    /**
     *
     */
    private String packets = "";
    /**
     * 创建时间
     */
    private Long createtime;
    /**
     * 更新时间
     */
    private Long modifytime;
    /**
     * 过期时间
     */
    private Long expiretime = 0L;
    /**
     * 交易明细信息
     */
    private String information;
    /**
     * 锁定状态 0正常 1锁定
     */
    private Integer locked = 0;
    /**
     * 是否对过账 0无 1有
     */
    private Integer confirmed = 0;

    /**
     * 发生后余额
     */
    private BigDecimal afterbalance;
    /**
     * 名称
     */
    private String subject;
    /**
     * 交易具体方式
     */
    private Integer method;
    
    
    private Long balanceid=0L;
    
    public Long getId() {
        return this.id;
    }

    public void setId(Long value) {
        this.id = value;
    }

    public Long getOrderid() {
        return this.orderid;
    }

    public void setOrderid(Long value) {
        this.orderid = value;
    }

    public Long getSerialno() {
        return this.serialno;
    }

    public void setSerialno(Long value) {
        this.serialno = value;
    }

    public String getTradeno() {
        return this.tradeno;
    }

    public void setTradeno(String value) {
        this.tradeno = value;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal value) {
        this.amount = value;
    }

    public Long getUserid() {
        return this.userid;
    }

    public void setUserid(Long value) {
        this.userid = value;
    }

    public Long getBilltime() {
        return this.billtime;
    }

    public void setBilltime(Long value) {
        this.billtime = value;
    }

    public Integer getIndecr() {
        return this.indecr;
    }

    public void setIndecr(Integer value) {
        this.indecr = value;
    }

    public Integer getBillchannel() {
        return this.billchannel;
    }

    public void setBillchannel(Integer value) {
        this.billchannel = value;
    }

    public Integer getBillret() {
        return this.billret;
    }

    public void setBillret(Integer value) {
        this.billret = value;
    }

    public BigDecimal getPrebalance() {
        return this.prebalance;
    }

    public void setPrebalance(BigDecimal value) {
        this.prebalance = value;
    }

    public String getIpaddr() {
        return this.ipaddr;
    }

    public void setIpaddr(String value) {
        this.ipaddr = value;
    }

    public String getSalt() {
        return this.salt;
    }

    public void setSalt(String value) {
        this.salt = value;
    }

    public String getPackets() {
        return this.packets;
    }

    public void setPackets(String value) {
        this.packets = value;
    }

    public Long getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(Long value) {
        this.createtime = value;
    }

    public Long getModifytime() {
        return this.modifytime;
    }

    public void setModifytime(Long value) {
        this.modifytime = value;
    }

    public Long getExpiretime() {
        return this.expiretime;
    }

    public void setExpiretime(Long value) {
        this.expiretime = value;
    }

    public String getInformation() {
        return this.information;
    }

    public void setInformation(String value) {
        this.information = value;
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    public Integer getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Integer confirmed) {
        this.confirmed = confirmed;
    }

    public BigDecimal getAfterbalance() {
        return afterbalance;
    }

    public void setAfterbalance(BigDecimal afterbalance) {
        this.afterbalance = afterbalance;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    
	public Integer getMethod() {
		return method;
	}

	public void setMethod(Integer method) {
		this.method = method;
	}

	public Long getBalanceid() {
		return balanceid;
	}

	public void setBalanceid(Long balanceid) {
		this.balanceid = balanceid;
	}
    
    
}