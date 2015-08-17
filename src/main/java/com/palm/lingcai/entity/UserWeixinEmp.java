package com.palm.lingcai.entity;

/**
 * 员工号信息
 * @Title:
 * @Description:
 * @Author: slfeng
 * @Date: 2014年9月22日 下午2:57:12
 * @Copyright： Copyright © 2014
 * @Company: 北京阳光彩通科技有限公司
 * @Class：com.palm.lingcai.entity.UserWeixinEmp
 * @Version: V1.0
 */
public class UserWeixinEmp {

	//alias
	public static final String TABLE_ALIAS = "UserWeixinEmp";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_OPENID = "openid";
	public static final String ALIAS_EMPNO = "empno";
	public static final String ALIAS_WXSERVERID = "wxserverid";
	public static final String ALIAS_CREATETIME = "createtime";
	public static final String ALIAS_MODIFYTIME = "modifytime";

	/**
	 * 
	 */
	private Long id;
	/**
	 * openid
	 */
	private String openid;
	/**
	 * 员工号
	 */
	private String empno;
	/**
	 * 
	 */
	private Long wxserverid;
	/**
	 * 创建时间
	 */
	private java.util.Date createtime;
	/**
	 * 更新时间
	 */
	private java.util.Date modifytime;

	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	public java.lang.String getOpenid() {
		return this.openid;
	}
	
	public void setOpenid(java.lang.String value) {
		this.openid = value;
	}
	public java.lang.String getEmpno() {
		return this.empno;
	}
	
	public void setEmpno(java.lang.String value) {
		this.empno = value;
	}
	public java.lang.Long getWxserverid() {
		return this.wxserverid;
	}
	
	public void setWxserverid(java.lang.Long value) {
		this.wxserverid = value;
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
}