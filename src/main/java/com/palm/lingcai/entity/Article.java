package com.palm.lingcai.entity;

public class Article {

    // alias
    public static final String TABLE_ALIAS = "文章";
    public static final String ALIAS_ID = "ID";
    public static final String ALIAS_TITLE = "标题";
    public static final String ALIAS_SUBTITLE = "子标题";
    public static final String ALIAS_SOURCE = "来源";
    public static final String ALIAS_AUTHOR = "作者";
    public static final String ALIAS_DIGEST = "简介";
    public static final String ALIAS_CONTENT = "正文";
    public static final String ALIAS_LINK = "外部链接";
    public static final String ALIAS_KEYWORD = "关键词";
    public static final String ALIAS_CHANNEL = "栏目";
    public static final String ALIAS_ARTICLETYPE = "文章类型";
    public static final String ALIAS_PICTURE = "主图";
    public static final String ALIAS_USERID = "用户ID";
    public static final String ALIAS_CREATETIME = "创建时间";
    public static final String ALIAS_MODIFYTIME = "修改时间";

    /**
     *
     */
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 子标题
     */
    private String subtitle;
    /**
     * 来源
     */
    private String source;
    /**
     * 作者
     */
    private String author;
    /**
     * 简介
     */
    private String digest;
    /**
     * 正文
     */
    private String content;
    /**
     * 外部链接
     */
    private String link;
    /**
     * 关键词
     */
    private String keyword;
    /**
     * 栏目
     */
    private String channel;
    /**
     * 文章类型 1:普通文章 2:公告
     */
    private Integer articletype;
    /**
     * 主图
     */
    private String picture;
    /**
     * 用户ID
     */
    private Long userid;
    /**
     * 用户名
     */
    private String username;
    /**
     * 创建时间
     */
    private java.util.Date createtime;
    /**
     * 修改时间
     */
    private java.util.Date modifytime;

    public java.lang.Long getId() {
        return this.id;
    }

    public void setId(java.lang.Long value) {
        this.id = value;
    }

    public java.lang.String getTitle() {
        return this.title;
    }

    public void setTitle(java.lang.String value) {
        this.title = value;
    }

    public java.lang.String getSubtitle() {
        return this.subtitle;
    }

    public void setSubtitle(java.lang.String value) {
        this.subtitle = value;
    }

    public java.lang.String getSource() {
        return this.source;
    }

    public void setSource(java.lang.String value) {
        this.source = value;
    }

    public java.lang.String getAuthor() {
        return this.author;
    }

    public void setAuthor(java.lang.String value) {
        this.author = value;
    }

    public java.lang.String getDigest() {
        return this.digest;
    }

    public void setDigest(java.lang.String value) {
        this.digest = value;
    }

    public java.lang.String getContent() {
        return this.content;
    }

    public void setContent(java.lang.String value) {
        this.content = value;
    }

    public java.lang.String getLink() {
        return this.link;
    }

    public void setLink(java.lang.String value) {
        this.link = value;
    }

    public java.lang.String getKeyword() {
        return this.keyword;
    }

    public void setKeyword(java.lang.String value) {
        this.keyword = value;
    }

    public java.lang.String getChannel() {
        return this.channel;
    }

    public void setChannel(java.lang.String value) {
        this.channel = value;
    }

    public Integer getArticletype() {
        return this.articletype;
    }

    public void setArticletype(Integer value) {
        this.articletype = value;
    }

    public java.lang.String getPicture() {
        return this.picture;
    }

    public void setPicture(java.lang.String value) {
        this.picture = value;
    }

    public java.lang.Long getUserid() {
        return this.userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserid(java.lang.Long value) {
        this.userid = value;
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
}