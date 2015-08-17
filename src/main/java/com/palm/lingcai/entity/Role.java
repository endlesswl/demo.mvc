package com.palm.lingcai.entity;

import java.util.List;

public class Role {

    // alias
    public static final String TABLE_ALIAS = "角色";
    public static final String ALIAS_ID = "ID";
    public static final String ALIAS_NAME = "角色名称";
    public static final String ALIAS_DESCRIPTION = "角色描述";
    public static final String ALIAS_RULE = "规则";

    /**
     *
     */
    private Long id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色描述
     */
    private String description;
    /**
     * 规则
     */
    private Integer rule;

    private List<Permission> permissions;

    public java.lang.Long getId() {
        return this.id;
    }

    public void setId(java.lang.Long value) {
        this.id = value;
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

    public java.lang.Integer getRule() {
        return this.rule;
    }

    public void setRule(java.lang.Integer value) {
        this.rule = value;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

}