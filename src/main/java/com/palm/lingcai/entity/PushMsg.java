package com.palm.lingcai.entity;

/**
 * <p>标题：商户后台-消息推送对象 </p>
 * <p>版权： Copyright © 2014 ZXC </p>
 * <p>公司: 零彩宝 </p>
 * <p>创建日期：2014年7月31日 上午11:10:45</p>
 * <p>类全名：com.palm.lingcai.entity.PushMsg</p>
 * <p>作者：JIJI </p>
 * <p>版本：1.0 </p>
 */
public class PushMsg
{
	//alias
	public static final String	TABLE_ALIAS			= "PushMsg";
	public static final String	ALIAS_ID			= "id";
	public static final String	ALIAS_CONTENT		= "content";
	public static final String	ALIAS_USERID		= "userId";
	public static final String	ALIAS_USERTYPE		= "userType";
	public static final String	ALIAS_CREATETIME	= "createTime";
	public static final String	ALIAS_MODIFYTIME	= "modifyTime";
	/**
	 * 唯一标识
	 */
	private Long				id;
	/**
	 * 消息内容
	 */
	private String				content;
	/**
	 * 用户ID
	 */
	private Long				userId;
	/**
	 * 用户类型1:个人用户 2:商户 3:管理员
	 */
	private Integer				userType;
	/**
	 * 创建时间
	 */
	private java.util.Date		createTime;
	/**
	 * 修改时间
	 */
	private java.util.Date		modifyTime;

	public java.lang.Long getId()
	{
		return this.id;
	}

	public void setId(java.lang.Long value)
	{
		this.id = value;
	}

	public java.lang.String getContent()
	{
		return this.content;
	}

	public void setContent(java.lang.String value)
	{
		this.content = value;
	}

	public java.lang.Long getUserId()
	{
		return this.userId;
	}

	public void setUserId(java.lang.Long value)
	{
		this.userId = value;
	}

	public Integer getUserType()
	{
		return this.userType;
	}

	public void setUserType(Integer value)
	{
		this.userType = value;
	}

	public java.util.Date getCreateTime()
	{
		return this.createTime;
	}

	public void setCreateTime(java.util.Date value)
	{
		this.createTime = value;
	}

	public java.util.Date getModifyTime()
	{
		return this.modifyTime;
	}

	public void setModifyTime(java.util.Date value)
	{
		this.modifyTime = value;
	}

	@Override
	public String toString()
	{
		return "PushMsg [id=" + id + ", content=" + content + ", userId=" + userId + ", userType=" + userType + ", createTime=" + createTime + ", modifyTime=" + modifyTime + "]";
	}
}