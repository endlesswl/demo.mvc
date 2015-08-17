package com.palm.lingcai.entity;


public class Ipfilter {

    //alias
    public static final String TABLE_ALIAS = "IP受限";
    public static final String ALIAS_ID = "ID";
    public static final String ALIAS_IPADDRESS = "IP地址";
    public static final String ALIAS_KEY = "缓存KEY";
    public static final String ALIAS_EXPIRETIME = "过期时间";
    public static final String ALIAS_CREATETIME = "创建时间";
    public static final String ALIAS_REMARK = "备注";

    /**
     *
     */
    private Long id;
    /**
     * IP地址
     */
    private String ipaddress;
    /**
     * 缓存KEY
     */
    private String keyvalue;
    /**
     * 过期时间，单位小时
     */
    private int expiretime;
    /**
     * 创建时间
     */
    private java.util.Date createtime;
    /**
     *
     */
    private String remark;

    public Long getId() {
        return this.id;
    }

    public void setId(Long value) {
        this.id = value;
    }

    public String getIpaddress() {
        return this.ipaddress;
    }

    public void setIpaddress(String value) {
        this.ipaddress = value;
    }

    public String getKeyvalue() {
        return keyvalue;
    }

    public void setKeyvalue(String keyvalue) {
        this.keyvalue = keyvalue;
    }

    public int getExpiretime() {
        return this.expiretime;
    }

    public void setExpiretime(int value) {
        this.expiretime = value;
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
}