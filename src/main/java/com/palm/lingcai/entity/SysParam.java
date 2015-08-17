package com.palm.lingcai.entity;

import java.util.Date;

/**
 * 系统参数表
 * 
 * @author LDL
 * 
 */
public class SysParam {
	// alias
	public static final String TABLE_ALIAS = "SysParam";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_CODE = "code1";
	public static final String ALIAS_CODE2 = "code2";
	public static final String ALIAS_CODE3 = "code3";
	public static final String ALIAS_TYPE = "type";
	public static final String ALIAS_DESCRIPTION = "description";
	public static final String ALIAS_CREATE_TIME = "createTime";

	private Long id;
	private String code1;
	private String code2;
	private String code3;
	private String type;
	private String description;
	private String status;
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode1() {
		return code1;
	}

	public void setCode(String code1) {
		this.code1 = code1;
	}

	public String getCode2() {
		return code2;
	}

	public void setCode2(String code2) {
		this.code2 = code2;
	}

	public String getCode3() {
		return code3;
	}

	public void setCode3(String code3) {
		this.code3 = code3;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setCode1(String code1) {
		this.code1 = code1;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}