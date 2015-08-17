package com.palm.lingcai.entity;


public class Qoption {

    //alias
    public static final String TABLE_ALIAS = "问题选项";
    public static final String ALIAS_ID = "id";
    public static final String ALIAS_QUESTIONID = "问题ID";
    public static final String ALIAS_OPTIONNAME = "选项名称";
    public static final String ALIAS_SUBTITLE = "子标题";
    public static final String ALIAS_OPTIONDESC = "选项描述";
    public static final String ALIAS_SORTID = "排序号";
    public static final String ALIAS_CREATETIME = "创建时间";
    public static final String ALIAS_MODIFYTIME = "修改时间";
    /**
     *
     */
    private Long id;
    /**
     * 问题ID
     */
    private Long questionId;
    /**
     * 选项名称
     */
    private String optionName;
    /**
     * 子标题
     */
    private String subTitle;
    /**
     * 选项描述
     */
    private String optionDesc;
    /**
     * 排序号
     */
    private Integer sortId;
    /**
     * 创建时间
     */
    private java.util.Date createTime;

    /**
     * 修改时间
     */
    private java.util.Date modifyTime;

    /**
     * 问题 多对一
     */
    private Question question;


    public java.lang.Long getId() {
        return this.id;
    }

    public void setId(java.lang.Long value) {
        this.id = value;
    }

    public java.lang.Long getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(java.lang.Long value) {
        this.questionId = value;
    }

    public java.lang.String getOptionName() {
        return this.optionName;
    }

    public void setOptionName(java.lang.String value) {
        this.optionName = value;
    }

    public java.lang.String getSubTitle() {
        return this.subTitle;
    }

    public void setSubTitle(java.lang.String value) {
        this.subTitle = value;
    }

    public String getOptionDesc() {
        return this.optionDesc;
    }

    public void setOptionDesc(String value) {
        this.optionDesc = value;
    }

    public Integer getSortId() {
        return this.sortId;
    }

    public void setSortId(Integer value) {
        this.sortId = value;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(java.util.Date value) {
        this.createTime = value;
    }

    public java.util.Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(java.util.Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}