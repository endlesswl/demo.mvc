package com.palm.lingcai.entity;

public class Provinces {
	private Long id;
	private String province;
	private String provinceid;
	private Integer count;
	private String phoneSegment;
	
	public String getPhoneSegment() {
		return phoneSegment;
	}
	public void setPhoneSegment(String phoneSegment) {
		this.phoneSegment = phoneSegment;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getProvinceid() {
		return provinceid;
	}
	public void setProvinceid(String provinceid) {
		this.provinceid = provinceid;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	
}