package com.palm.lingcai.entity;


public class Competition {

	//alias
	public static final String TABLE_ALIAS = "Competition";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_MATCHID = "matchid";
	public static final String ALIAS_TEAMNAME = "teamname";
	public static final String ALIAS_CID = "cid";
	public static final String ALIAS_MASTER = "master";
	public static final String ALIAS_STARTTIME = "starttime";
	public static final String ALIAS_ENDTIME = "endtime";
	public static final String ALIAS_CREATETIME = "createtime";
	public static final String ALIAS_MODIFYTIME = "modifytime";
	public static final String ALIAS_REMARK = "remark";

	/**
	 * 
	 */
	private Long id;
	/**
	 * 
	 */
	private String matchid;
	/**
	 * 队名
	 */
	private String teamname;
	/**
	 * 方案
	 */
	private String cid;
	/**
	 * 0 主队，1 客队
	 */
	private Integer master;
	/**
	 * 开始比赛时间
	 */
	private java.util.Date starttime;
	/**
	 * 方案结束时间
	 */
	private java.util.Date endtime;
	
	private java.util.Date prizetime;
	/**
	 * 
	 */
	private java.util.Date createtime;
	/**
	 * 
	 */
	private java.util.Date modifytime;
	
	private  java.util.Date matchtime;
	public java.util.Date getMatchtime() {
		return matchtime;
	}

	public void setMatchtime(java.util.Date matchtime) {
		this.matchtime = matchtime;
	}

	/**
	 * 
	 */
	private String remark="0";
	
	/**
	 * 支持主队人数
	 */
	private Integer homeGameCount;
	
	/**
	 * 支持客队人数
	 */
	private Integer roadGameCount;

	
	public Integer getHomeGameCount() {
		return homeGameCount;
	}

	public void setHomeGameCount(Integer homeGameCount) {
		this.homeGameCount = homeGameCount;
	}

	public Integer getRoadGameCount() {
		return roadGameCount;
	}

	public void setRoadGameCount(Integer roadGameCount) {
		this.roadGameCount = roadGameCount;
	}

	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	public java.lang.String getMatchid() {
		return this.matchid;
	}
	
	public void setMatchid(java.lang.String value) {
		this.matchid = value;
	}
	public java.lang.String getTeamname() {
		return this.teamname;
	}
	
	public void setTeamname(java.lang.String value) {
		this.teamname = value;
	}
	public java.lang.String getCid() {
		return this.cid;
	}
	
	public void setCid(java.lang.String value) {
		this.cid = value;
	}
	public java.lang.Integer getMaster() {
		return this.master;
	}
	
	public void setMaster(java.lang.Integer value) {
		this.master = value;
	}
	public java.util.Date getStarttime() {
		return this.starttime;
	}
	
	public void setStarttime(java.util.Date value) {
		this.starttime = value;
	}
	public java.util.Date getEndtime() {
		return this.endtime;
	}
	
	public void setEndtime(java.util.Date value) {
		this.endtime = value;
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
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}

	public java.util.Date getPrizetime() {
		return prizetime;
	}

	public void setPrizetime(java.util.Date prizetime) {
		this.prizetime = prizetime;
	}
	
	
	
	
}