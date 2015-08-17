package com.palm.lingcai.entity;


public class Answer {

    //alias
    public static final String TABLE_ALIAS = "问题答案";
    public static final String ALIAS_ID = "id";
    public static final String ALIAS_QUESTIONID = "问题ID";
    public static final String ALIAS_USERID = "用户ID";
    public static final String ALIAS_ANSWER = "答案";
    public static final String ALIAS_ISRIGHT = "是否正确";
    public static final String ALIAS_CREATETIME = "答题时间";

    
    
    /**
     *
     */
    private Long id;
    /**
     * 问题ID
     */
    private Long questionId;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 答案
     */
    private String answer;
    /**
     * 是否正确
     */
    private Integer isRight;
    /**
     * 答题时间
     */
    private java.util.Date createTime;

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

    public java.lang.Long getUserId() {
        return this.userId;
    }

    public void setUserId(java.lang.Long value) {
        this.userId = value;
    }

    public java.lang.String getAnswer() {
        return this.answer;
    }

    public void setAnswer(java.lang.String value) {
        this.answer = value;
    }

    public Integer getIsRight() {
        return this.isRight;
    }

    public void setIsRight(Integer value) {
        this.isRight = value;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(java.util.Date value) {
        this.createTime = value;
    }
}