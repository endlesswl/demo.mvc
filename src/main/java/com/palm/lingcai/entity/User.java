package com.palm.lingcai.entity;

import org.hibernate.validator.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class User implements Serializable {
	/**
	 * @Fields serialVersionUID :
	 */
	private static final long serialVersionUID = 1L;
	// alias
	public static final String TABLE_ALIAS = "用户";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_USERNAME = "登录名";
	public static final String ALIAS_PWD = "密码";
	public static final String ALIAS_PWDSALT = "密码校验";
	public static final String ALIAS_PAYPWD = "支付密码";
	public static final String ALIAS_PAYPWDSALT = "payPwdSalt";
	public static final String ALIAS_REALNAME = "真实姓名";
	public static final String ALIAS_CARDTYPE = "证件类型";
	public static final String ALIAS_CARDNO = "证件号码";
	public static final String ALIAS_EMAIL = "邮箱";
	public static final String ALIAS_MOBILE = "手机";
	public static final String ALIAS_MOBILEAREA = "手机注册地";
	public static final String ALIAS_GENDER = "性别";
	public static final String ALIAS_QQ = "QQ号码";
	public static final String ALIAS_PHONE = "公司电话";
	public static final String ALIAS_ADDRESS = "地址";
	public static final String ALIAS_ZIPCODE = "邮编";
	public static final String ALIAS_AVATAR = "头像";
	public static final String ALIAS_REGIP = "注册IP";
	public static final String ALIAS_COMPANY = "公司";
	public static final String ALIAS_LICENSESCAN = "营业执照扫描件";
	public static final String ALIAS_USERTYPE = "用户类型";
	public static final String ALIAS_BALANCE = "账户余额";
	public static final String ALIAS_SALT = "salt";
	public static final String ALIAS_LASTTIME = "余额最后修改时间";
	public static final String ALIAS_FLAG = "状态";
	public static final String ALIAS_SOURCE = "注册来源";
	public static final String ALIAS_REMARK = "备注";
	public static final String ALIAS_CREATETIME = "注册时间";
	public static final String ALIAS_MODIFYTIME = "修改时间";
	public static final String ALIAS_OPENID = "微信id";
	public static final String ALIAS_CHANNELID = "渠道ID";
	public static final String ALIAS_PRIZE = "中奖金额";
	public static final String ALIAS_LASTPRIZETIME = "中奖金额最后修改时间";

	/**
	 *
	 */
	private Long id;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String pwd;
	/**
	 * 密码校验标识
	 */
	private String pwdSalt;
	/**
	 * 支付密码
	 */
	private String payPwd;
	/**
	 * 支付密码校验标识
	 */
	private String payPwdSalt;
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 证件类型
	 */
	private Integer cardType = 1;
	/**
	 * 证件号码
	 */
	private String cardNo;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 手机
	 */
	private String mobile;
	/**
	 * 手机注册地
	 */
	private String mobileArea;
	/**
	 * 性别1:男 2:女
	 */
	private Integer gender = 0;
	/**
	 * QQ号码
	 */
	private String qq;
	/**
	 * 固话
	 */
	private String phone;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 邮编
	 */
	private String zipcode;
	/**
	 * 头像
	 */
	private String avatar;
	/**
	 * 注册IP
	 */
	private String regip;
	/**
	 * 公司名称
	 */
	private String company;
	/**
	 * 营业执照副本扫描件
	 */
	private String licenseScan;
	/**
	 * 用户类型1:个人用户 2:商户 3:管理员
	 */
	private Integer userType;
	/**
	 * 余额
	 */
	private BigDecimal balance = new BigDecimal("0.00");
	/**
	 * 余额校验标识
	 */
	private String salt;
	/**
	 * 余额最后修改时间
	 */
	private java.util.Date lastTime;
	/**
	 * 中奖金额
	 */
	private BigDecimal prize;
	/**
	 * 余额预警值
	 */
	private BigDecimal warnBalance;
	/**
	 * 余额预警状态
	 */
	private String warnFlag;
	/**
	 * 中奖金额最后修改时间
	 */
	private java.util.Date lastPrizeTime;

	/**
	 * 用户状态 0:正常 1:锁定 2:删除
	 */
	private Integer flag;
	/**
	 * 注册来源 1：手机 2：网站,3：微信
	 */
	private Integer source;
	/**
	 * 用户登录次数
	 */
	private Integer logintimes = 0;
	/**
	 * 注册时间
	 */
	private java.util.Date createTime;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyTime;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 微信用户唯一标识
	 */
	private String openid;

	/**
	 * 角色列表
	 */
	private List<Role> roles;
	/**
	 * 资金属性
	 */
	private List<Balance> balanceList;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public User() {
	}

	public User(Long id) {
		this.id = id;
	}

	public java.lang.Long getId() {
		return this.id;
	}

	public void setId(java.lang.Long value) {
		this.id = value;
	}

	@NotBlank(message = "用户名不能为空")
	public java.lang.String getUsername() {
		return this.username;
	}

	public void setUsername(java.lang.String value) {
		this.username = value;
	}

	@NotBlank(message = "密码不能为空")
	public java.lang.String getPwd() {
		return this.pwd;
	}

	public void setPwd(java.lang.String value) {
		this.pwd = value;
	}

	public java.lang.String getPwdSalt() {
		return this.pwdSalt;
	}

	public void setPwdSalt(java.lang.String value) {
		this.pwdSalt = value;
	}

	public java.lang.String getPayPwd() {
		return this.payPwd;
	}

	public void setPayPwd(java.lang.String value) {
		this.payPwd = value;
	}

	public java.lang.String getPayPwdSalt() {
		return this.payPwdSalt;
	}

	public void setPayPwdSalt(java.lang.String value) {
		this.payPwdSalt = value;
	}

	public java.lang.String getRealName() {
		return this.realName;
	}

	public void setRealName(java.lang.String value) {
		this.realName = value;
	}

	public Integer getCardType() {
		return this.cardType;
	}

	public void setCardType(Integer value) {
		this.cardType = value;
	}

	public java.lang.String getCardNo() {
		return this.cardNo;
	}

	public void setCardNo(java.lang.String value) {
		this.cardNo = value;
	}

	public java.lang.String getEmail() {
		return this.email;
	}

	public void setEmail(java.lang.String value) {
		this.email = value;
	}

	public java.lang.String getMobile() {
		return this.mobile;
	}

	public void setMobile(java.lang.String value) {
		this.mobile = value;
	}

	public java.lang.String getMobileArea() {
		return this.mobileArea;
	}

	public void setMobileArea(java.lang.String value) {
		this.mobileArea = value;
	}

	public Integer getGender() {
		return this.gender;
	}

	public void setGender(Integer value) {
		this.gender = value;
	}

	public java.lang.String getQq() {
		return this.qq;
	}

	public void setQq(java.lang.String value) {
		this.qq = value;
	}

	public java.lang.String getPhone() {
		return this.phone;
	}

	public void setPhone(java.lang.String value) {
		this.phone = value;
	}

	public java.lang.String getAddress() {
		return this.address;
	}

	public void setAddress(java.lang.String value) {
		this.address = value;
	}

	public java.lang.String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(java.lang.String value) {
		this.zipcode = value;
	}

	public java.lang.String getAvatar() {
		return this.avatar;
	}

	public void setAvatar(java.lang.String value) {
		this.avatar = value;
	}

	public java.lang.String getRegip() {
		return this.regip;
	}

	public void setRegip(java.lang.String value) {
		this.regip = value;
	}

	public java.lang.String getCompany() {
		return this.company;
	}

	public void setCompany(java.lang.String value) {
		this.company = value;
	}

	public java.lang.String getLicenseScan() {
		return this.licenseScan;
	}

	public void setLicenseScan(java.lang.String value) {
		this.licenseScan = value;
	}

	public Integer getUserType() {
		return this.userType;
	}

	public void setUserType(Integer value) {
		this.userType = value;
	}

	public BigDecimal getBalance() {
		return this.balance;
	}

	public void setBalance(BigDecimal value) {
		this.balance = value;
	}

	public java.lang.String getSalt() {
		return this.salt;
	}

	public void setSalt(java.lang.String value) {
		this.salt = value;
	}

	public java.util.Date getLastTime() {
		return this.lastTime;
	}

	public void setLastTime(java.util.Date value) {
		this.lastTime = value;
	}

	public BigDecimal getPrize() {
		return prize;
	}

	public void setPrize(BigDecimal prize) {
		this.prize = prize;
	}

	public java.util.Date getLastPrizeTime() {
		return lastPrizeTime;
	}

	public void setLastPrizeTime(java.util.Date lastPrizeTime) {
		this.lastPrizeTime = lastPrizeTime;
	}

	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer value) {
		this.flag = value;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Integer getLogintimes() {
		return logintimes;
	}

	public void setLogintimes(Integer logintimes) {
		this.logintimes = logintimes;
	}

	public java.lang.String getRemark() {
		return this.remark;
	}

	public void setRemark(java.lang.String value) {
		this.remark = value;
	}

	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}

	public java.util.Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(java.util.Date value) {
		this.modifyTime = value;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Balance> getBalanceList() {
		return balanceList;
	}

	public void setBalanceList(List<Balance> balanceList) {
		this.balanceList = balanceList;
	}

	public BigDecimal getWarnBalance() {
		if (warnBalance == null) {
			warnBalance = BigDecimal.ZERO;
		}
		return warnBalance;
	}

	public void setWarnBalance(BigDecimal warnBalance) {
		this.warnBalance = warnBalance;
	}

	public String getWarnFlag() {
		return warnFlag;
	}

	public void setWarnFlag(String warnFlag) {
		this.warnFlag = warnFlag;
	}

}