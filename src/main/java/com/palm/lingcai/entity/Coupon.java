package com.palm.lingcai.entity;


import java.math.BigDecimal;

public class Coupon {

    //alias
    public static final String TABLE_ALIAS = "零彩券";
    public static final String ALIAS_ID = "ID";
    public static final String ALIAS_TITLE = "标题";
    public static final String ALIAS_USERID = "用户";
    public static final String ALIAS_TYPEID = "类型";
    public static final String ALIAS_CODE = "代码";
    public static final String ALIAS_PRICE = "面额";
    public static final String ALIAS_BALANCE = "余额";
    public static final String ALIAS_STATUS = "状态";
    public static final String ALIAS_USEDNUMBERS = "可使用次数";
    public static final String ALIAS_DESCRIPTION = "描述";
    public static final String ALIAS_CREATETIME = "创建时间";
    public static final String ALIAS_EXPIRETIME = "过期时间";
    public static final String ALIAS_USEDTIME = "使用时间";
    public static final String ALIAS_REMARK = "备注";

    /**
     *
     */
    private Long id;
    /**
     * 零彩券标题
     */
    private String title;
    /**
     * 用户
     */
    private Long userid;
    /**
     * 零彩券类型 0(系统赠送),1(商家赠送),2(活动奖励),3()
     */
    private Integer typeid = 0;
    /**
     * 零彩券代码
     */
    private String code;
    /**
     * 零彩券面额
     */
    private BigDecimal price;
    /**
     * 零彩券余额
     */
    private BigDecimal balance = new BigDecimal("0.00");
    /**
     * 状态 0(可用),1(不可用)
     */
    private Integer status = 0;
    /**
     * 可使用次数
     */
    private Integer usednumbers = 1;
    /**
     * 零彩券描述
     */
    private String description;
    /**
     * 创建时间
     */
    private java.util.Date createtime;
    /**
     * 过期时间
     */
    private java.util.Date expiretime;
    /**
     * 使用时间
     */
    private java.util.Date usedtime;
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

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String value) {
        this.title = value;
    }

    public Long getUserid() {
        return this.userid;
    }

    public void setUserid(Long value) {
        this.userid = value;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String value) {
        this.code = value;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer value) {
        this.status = value;
    }

    public Integer getUsednumbers() {
        return this.usednumbers;
    }

    public void setUsednumbers(Integer value) {
        this.usednumbers = value;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public java.util.Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(java.util.Date value) {
        this.createtime = value;
    }

    public java.util.Date getExpiretime() {
        return this.expiretime;
    }

    public void setExpiretime(java.util.Date value) {
        this.expiretime = value;
    }

    public java.util.Date getUsedtime() {
        return this.usedtime;
    }

    public void setUsedtime(java.util.Date value) {
        this.usedtime = value;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String value) {
        this.remark = value;
    }
}