package com.palm.lingcai.entity;

import java.math.BigDecimal;
import java.util.Date;


public class Message {

    //alias
    public static final String TABLE_ALIAS = "Message";
    public static final String ALIAS_ID = "id";
    public static final String ALIAS_MARKETID = "marketId";
    public static final String ALIAS_CONTENT = "content";
    public static final String ALIAS_TITLE = "title";
    public static final String ALIAS_SORTID = "sortid";
    public static final String ALIAS_IMAGEURL="imageUrl";
    public static final String ALIAS_CREATETIME = "createTime";
    public static final String ALIAS_MODIFYTIME = "modifyTime";
    public static final String ALIAS_SENDER = "发布人";
    /**
     *
     */
    private Long id;
    /**
     * 零彩计划id
     */
    private Long marketId;
    /**
     * 问题ID
     */
    private String content;
    /**
     * 图片地址
     */
    private String imageUrl;
    /**
     * 子标题
     */
    private String title;
    /**
     * 排序号
     */
    private Integer sortid;
    /**
     * 创建时间
     */
    private java.util.Date createTime;
    /**
     * 修改时间
     */
    private java.util.Date modifyTime;
    /**
     * 发布者
     */
    private String sender;

    /**
     * 短地址
     */
    private String shortUrl;

    /**
     * 定阅人，接收人
     */
    private String subscribe;


    //////////////////////////////////////关联////////////////////////////////////////////////
    private int payFlag;
    private BigDecimal totalPrice;
    private BigDecimal unitPrice;
    private Date startTime;
    private Date endTime;
    private int status;
    private BigDecimal userMoney = new BigDecimal(0);

    public java.lang.Long getId() {
        return this.id;
    }

    public void setId(java.lang.Long value) {
        this.id = value;
    }

    public java.lang.Long getMarketId() {
        return this.marketId;
    }

    public void setMarketId(java.lang.Long value) {
        this.marketId = value;
    }

    public java.lang.String getContent() {
        return this.content;
    }

    public void setContent(java.lang.String value) {
        this.content = value;
    }

    public java.lang.String getTitle() {
        return this.title;
    }

    public void setTitle(java.lang.String value) {
        this.title = value;
    }

    public Integer getSortid() {
        return this.sortid;
    }

    public void setSortid(Integer value) {
        this.sortid = value;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(java.util.Date value) {
        this.createTime = value;
    }

    public java.util.Date getModifyTime() {
        return this.modifyTime;
    }

    public void setModifyTime(java.util.Date value) {
        this.modifyTime = value;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public int getPayFlag() {
        return payFlag;
    }

    public void setPayFlag(int payFlag) {
        this.payFlag = payFlag;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(BigDecimal userMoney) {
        this.userMoney = userMoney;
    }

    public String getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe;
    }

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
    
    
    
    
}