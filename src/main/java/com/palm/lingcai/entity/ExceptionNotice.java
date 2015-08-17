package com.palm.lingcai.entity;

/**
 * 错误提示实体
 * @author pangxin
 *
 */
public class ExceptionNotice {
	
	//电话号码s
	private String mobiles;
	//提示信息
	private String content;
	public String getMobiles() {
		return mobiles;
	}
	public void setMobiles(String mobiles) {
		this.mobiles = mobiles;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
