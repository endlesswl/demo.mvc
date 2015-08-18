package com.palm.lingcai.entity;


public class TestDemo {

	//alias
	public static final String TABLE_ALIAS = "TestDemo";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NAME = "name";
	public static final String ALIAS_CREATE_TIME = "createTime";
	public static final String ALIAS_AGE = "age";

	/**
	 * 
	 */
	private Integer id;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private java.util.Date createTime;
	/**
	 * 
	 */
	private Integer age;

	public java.lang.Integer getId() {
		return this.id;
	}
	
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String value) {
		this.name = value;
	}
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	public java.lang.Integer getAge() {
		return this.age;
	}
	
	public void setAge(java.lang.Integer value) {
		this.age = value;
	}
}