package com.palm.lingcai.entity;


public class Sms {

    //alias
    public static final String TABLE_ALIAS = "短信发送";
    public static final String ALIAS_ID = "ID";
    public static final String ALIAS_MOBILE = "手机号";
    public static final String ALIAS_USERID = "用户ID";
    public static final String ALIAS_CONTENT = "发送内容";
    public static final String ALIAS_OPERATOR = "操作员";
    public static final String ALIAS_CREATETIME = "发送时间";

    /**
     *
     */
    private Long id;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 用户ID
     */
    private Long userid;
    /**
     * 短信内容
     */
    private String content;
    /**
     * 操作员
     */
    private String operator;
    /**
     * 发送时间
     */
    private java.util.Date createtime;

    public Long getId() {
        return this.id;
    }

    public void setId(Long value) {
        this.id = value;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String value) {
        this.mobile = value;
    }

    public Long getUserid() {
        return this.userid;
    }

    public void setUserid(Long value) {
        this.userid = value;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String value) {
        this.content = value;
    }

    public String getOperator() {
        return this.operator;
    }

    public void setOperator(String value) {
        this.operator = value;
    }

    public java.util.Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(java.util.Date value) {
        this.createtime = value;
    }
}