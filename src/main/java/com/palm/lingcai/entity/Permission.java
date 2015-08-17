package com.palm.lingcai.entity;

public class Permission {

    // alias
    public static final String TABLE_ALIAS = "权限";
    public static final String ALIAS_ID = "ID";
    public static final String ALIAS_RESOURCE = "资源限定";
    public static final String ALIAS_ACL = "权限类型";
    public static final String ALIAS_NAME = "权限表达式";
    public static final String ALIAS_DESCRIPTION = "中文描述";

    /**
     *
     */
    private Long id;
    /**
     *
     */
    private Integer resource;
    /**
     *
     */
    private Integer acl;
    /**
     *
     */
    private String name;
    /**
     *
     */
    private String description;

    public java.lang.Long getId() {
        return this.id;
    }

    public void setId(java.lang.Long value) {
        this.id = value;
    }

    public java.lang.Integer getResource() {
        return this.resource;
    }

    public void setResource(java.lang.Integer value) {
        this.resource = value;
    }

    public java.lang.Integer getAcl() {
        return this.acl;
    }

    public void setAcl(java.lang.Integer value) {
        this.acl = value;
    }

    public java.lang.String getName() {
        return this.name;
    }

    public void setName(java.lang.String value) {
        this.name = value;
    }

    public java.lang.String getDescription() {
        return this.description;
    }

    public void setDescription(java.lang.String value) {
        this.description = value;
    }
}