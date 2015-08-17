package com.palm.lingcai.entity;

import java.util.Date;

public class WeixinConfig {
	private Long id; // 主键id
	private Long marketid; // 营销计划id
	private String name; // 名称
	private String token; // token
	private String oauth; // 微信获取取网页授权access_token
	private String appid; // 微信服务号
	private String appsecret;// 服务号密钥
	private String userInfoUrl;// 微信获取用户基本信息
	private String snsapiUserInfo;// 微信认证授权后主动拉取用户信息
	private String oauthHtml;// oauth2.0授权
	private String attentionUrl;// 关注地址
	private String attentionUserInfoUrl;// 关注用户的信息地址
	private String messageUrl;// 推送信息地址
	private String weixinMessage1;// 自定义微信消息1
	private String weixinMessage2;// 自定义微信消息2
	private String weixinMessage3;// 自定义微信消息3
	private String smsMessage1;// 自定义短信消息1
	private String smsMessage2;// 自定义短信消息2
	private String smsMessage3;// 自定义短信消息3
	private Date createtime;// 创建时间
	private Date updatetime;// 更新时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMarketid() {
		return marketid;
	}

	public void setMarketid(Long marketid) {
		this.marketid = marketid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getOauth() {
		return oauth;
	}

	public void setOauth(String oauth) {
		this.oauth = oauth;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	public String getUserInfoUrl() {
		return userInfoUrl;
	}

	public void setUserInfoUrl(String userInfoUrl) {
		this.userInfoUrl = userInfoUrl;
	}

	public String getSnsapiUserInfo() {
		return snsapiUserInfo;
	}

	public void setSnsapiUserInfo(String snsapiUserInfo) {
		this.snsapiUserInfo = snsapiUserInfo;
	}

	public String getOauthHtml() {
		return oauthHtml;
	}

	public void setOauthHtml(String oauthHtml) {
		this.oauthHtml = oauthHtml;
	}

	public String getAttentionUrl() {
		return attentionUrl;
	}

	public void setAttentionUrl(String attentionUrl) {
		this.attentionUrl = attentionUrl;
	}

	public String getAttentionUserInfoUrl() {
		return attentionUserInfoUrl;
	}

	public void setAttentionUserInfoUrl(String attentionUserInfoUrl) {
		this.attentionUserInfoUrl = attentionUserInfoUrl;
	}

	public String getMessageUrl() {
		return messageUrl;
	}

	public void setMessageUrl(String messageUrl) {
		this.messageUrl = messageUrl;
	}

	public String getWeixinMessage1() {
		return weixinMessage1;
	}

	public void setWeixinMessage1(String weixinMessage1) {
		this.weixinMessage1 = weixinMessage1;
	}

	public String getWeixinMessage2() {
		return weixinMessage2;
	}

	public void setWeixinMessage2(String weixinMessage2) {
		this.weixinMessage2 = weixinMessage2;
	}

	public String getWeixinMessage3() {
		return weixinMessage3;
	}

	public void setWeixinMessage3(String weixinMessage3) {
		this.weixinMessage3 = weixinMessage3;
	}

	public String getSmsMessage1() {
		return smsMessage1;
	}

	public void setSmsMessage1(String smsMessage1) {
		this.smsMessage1 = smsMessage1;
	}

	public String getSmsMessage2() {
		return smsMessage2;
	}

	public void setSmsMessage2(String smsMessage2) {
		this.smsMessage2 = smsMessage2;
	}

	public String getSmsMessage3() {
		return smsMessage3;
	}

	public void setSmsMessage3(String smsMessage3) {
		this.smsMessage3 = smsMessage3;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

}
