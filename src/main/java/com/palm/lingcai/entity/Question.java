package com.palm.lingcai.entity;

import java.util.List;


public class Question {

    //alias
    public static final String TABLE_ALIAS = "问题";
    public static final String ALIAS_ID = "id";
    public static final String ALIAS_MARKETID = "营销计划ID";
    public static final String ALIAS_SORTID = "序号";
    public static final String ALIAS_TITLE = "问题标题";
    public static final String ALIAS_SUBTITLE = "子标题";
    public static final String ALIAS_QUESTIONDESC = "问题描述";
    public static final String ALIAS_QUESTIONTYPE = "问题类型";
    public static final String ALIAS_RIGHTTIPS = "回答正确提示";
    public static final String ALIAS_WRONGTIPS = "回答错误提示";
    public static final String ALIAS_ANSWER = "正确答案";
    public static final String ALIAS_CREATETIME = "创建时间";
    public static final String ALIAS_MODIFYTIME = "修改时间";

    /**
     *
     */
    private Long id;
    /**
     * 营销计划ID
     */
    private Long marketId;
    /**
     * 序号
     */
    private Integer sortId;
    /**
     * 问题标题
     */
    private String title;
    /**
     * 子标题
     */
    private String subTitle;
    /**
     * 问题描述
     */
    private String questionDesc;
    /**
     * 问题类型:1 单选;2 多选;3 文本;4 随机
     */
    private Integer questionType;
    /**
     * 回答正确提示
     */
    private String rightTips;
    /**
     * 回答错误提示
     */
    private String wrongTips;
    /**
     * 正确答案
     */
    private String answer;
    /**
     * 创建时间
     */
    private java.util.Date createTime;
    /**
     * 修改时间
     */
    private java.util.Date modifyTime;

    /**
     * 选项 1对多
     */
    private List<Qoption> options;

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

    public java.lang.Integer getSortId() {
        return this.sortId;
    }

    public void setSortId(java.lang.Integer value) {
        this.sortId = value;
    }

    public java.lang.String getTitle() {
        return this.title;
    }

    public void setTitle(java.lang.String value) {
        this.title = value;
    }

    public java.lang.String getSubTitle() {
        return this.subTitle;
    }

    public void setSubTitle(java.lang.String value) {
        this.subTitle = value;
    }

    public java.lang.String getQuestionDesc() {
        return this.questionDesc;
    }

    public void setQuestionDesc(java.lang.String value) {
        this.questionDesc = value;
    }

    public Integer getQuestionType() {
        return this.questionType;
    }

    public void setQuestionType(Integer value) {
        this.questionType = value;
    }

    public java.lang.String getRightTips() {
        return this.rightTips;
    }

    public void setRightTips(java.lang.String value) {
        this.rightTips = value;
    }

    public java.lang.String getWrongTips() {
        return this.wrongTips;
    }

    public void setWrongTips(java.lang.String value) {
        this.wrongTips = value;
    }

    public java.lang.String getAnswer() {
        return this.answer;
    }

    public void setAnswer(java.lang.String value) {
        this.answer = value;
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

    public List<Qoption> getOptions() {
        return options;
    }

    public void setOptions(List<Qoption> options) {
        this.options = options;
    }
}