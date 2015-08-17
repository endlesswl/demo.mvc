package com.palm.lingcai.entity;

public class PhoneArea {
	private Integer id;
	private String phoneSegment;
	private String city;
	private String phoneArea;
	private String cityNum;
	private Long provinceId;
	
	
	public Long getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPhoneSegment() {
		return phoneSegment;
	}
	public void setPhoneSegment(String phoneSegment) {
		this.phoneSegment = phoneSegment;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPhoneArea() {
		return phoneArea;
	}
	public void setPhoneArea(String phoneArea) {
		this.phoneArea = phoneArea;
	}
	public String getCityNum() {
		return cityNum;
	}
	public void setCityNum(String cityNum) {
		this.cityNum = cityNum;
	}
	
	
}
