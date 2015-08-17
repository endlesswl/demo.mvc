package com.palm.lingcai.entity;

import java.io.Serializable;
/**
 * <p>标题：互动用户信息实体 </p>
 * <p>功能： </p>
 * <p>版权： Copyright (c) 2014</p>
 * <p>公司: 北京阳光彩通</p>
 * <p>创建日期：2014年10月28日 下午5:16:16</p>
 * <p>类全名：com.palm.lingcai.entity.InteractUserInfo</p>
 * <p>作者：JIJI</p>
 * <p>@version 1.0</p>
 */
public class InteractUserInfo implements Serializable
{
	/**
	 * @Fields serialVersionUID :
	 */
	private static final long	serialVersionUID	= 1L;
	public static final String	TABLE_ALIAS			= "互动用户信息";
	public static final String	ALIAS_USERID		= "用户ID";
	public static final String	ALIAS_NUM			= "互动次数";
	// 用户ID
	private Long				userid;
	// 互动次数
	private int					num;
	// 用户微信信息
	private UserWeixinInfo		userWeixinInfo;

	public Long getUserid()
	{
		return userid;
	}

	public void setUserid(Long userid)
	{
		this.userid = userid;
	}

	public int getNum()
	{
		return num;
	}

	public void setNum(int num)
	{
		this.num = num;
	}

	public UserWeixinInfo getUserWeixinInfo()
	{
		return userWeixinInfo;
	}

	public void setUserWeixinInfo(UserWeixinInfo userWeixinInfo)
	{
		this.userWeixinInfo = userWeixinInfo;
	}
}
