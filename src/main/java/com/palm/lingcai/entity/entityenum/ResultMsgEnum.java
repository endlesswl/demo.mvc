package com.palm.lingcai.entity.entityenum;

import org.apache.commons.lang3.StringUtils;
import com.palm.lingcai.util.JsonUtils;
/**
 * <p>标题：结果信息枚举类 </p>
 * <p>公司: 北京阳光彩通 </p>
 * <p>创建日期：2014年8月18日 上午11:20:28</p>
 * <p>类全名：com.palm.lingcai.entity.entityenum.ResultMsgEnum</p>
 * <p>作者：JIJI </p>
 * <p>版本：1.0 </p>
 */
public enum ResultMsgEnum
{
	SUCCESS_SETPAYPWD("success", "交易密码设置成功"), // 交易密码设置
	SUCCESS_MODPAYPWD("success", "交易密码修改成功"), // 交易密码修改
	SUCCESS_VALICAPTCHA("success", "验证码正确"), // 验证码校验
	SUCCESS_CHECKPWD("success", "登陆密码正确"), // 登录密码校验
	SUCCESS_CHECKPAYPWD("success", "交易密码正确"), // 交易密码校验
	SUCCESS_UPDATEUSER("success", "用户信息更新成功"), // 用户信息更新
	SUCCESS_MSGCAPTCHA("success", "手机验证码发送成功"), // 用户不存在
	SUCCESS_MODPWD("success", "密码修改成功"), // 密码修改
	SUCCESS_WITHDRAW("success", "申请提现成功"), // 提现
	SUCCESS_DEPOSIT("success", "充值成功"), // 充值
	SUCCESS_ADDBANKCARD("success", "银行卡添加成功"), // 银行卡
	SUCCESS_BEUSER("success", "用户存在"), // 用户
	SUCCESS_QRCODEDOWNLOAD("success", "二维码下载成功"), // 二维码下载
	SUCCESS_MASTERBIND("success", "主账户绑定成功"), // 主账户绑定成功
	FAIL_SETPAYPWD("fail", "交易密码设置失败"), // 交易密码设置
	FAIL_MODPAYPWD("fail", "交易密码设置失败"), // 交易密码修改
	FAIL_VALICAPTCHA("fail", "验证码错误"), // 验证码校验
	FAIL_CHECKPWD("fail", "登陆密码错误"), // 登录密码校验
	FAIL_CHECKPAYPWD("fail", "交易密码错误"), // 交易密码校验
	FAIL_UPDATEUSER("fail", "用户信息更新失败"), // 用户信息更新
	FAIL_MODPWD("fail", "密码设置失败"), // 密码修改
	FAIL_MSGCAPTCHA("fail", "对不起，不存在此用户/手机号码。"), // 用户不存在
	FAIL_WITHDRAW("fail", "申请提现失败"), // 提现
	FAIL_DEPOSIT("fail", "充值失败"), // 充值
	FAIL_ADDBANKCARD("fail", "银行卡添加失败"), // 银行卡
	FAIL_BEUSER("success", "用户不存在"), // 用户
	FAIL_QRCODEDOWNLOAD("fail", "二维码下载失败"), // 二维码下载
	FAIL_MASTERNOTEXIST("fail", "主账户不存在"), // 主账户不存在
	FAIL_MASTERPWDERR("fail", "主账户密码不正确"), // 主账户密码不正确
	FAIL_WRONGACCOUNTTYPE("fail", "账户类型错误"), // 账户类型错误
	FAIL_MASTERBINDERR("fail", "主账户绑定异常"); // 主账户绑定异常
	private String	code;
	private String	msg;

	private ResultMsgEnum(String code, String msg)
	{
		this.code = code;
		this.msg = msg;
	}

	public String getCode()
	{
		return code;
	}

	public String getMsg()
	{
		return msg;
	}

	public String toJson()
	{
		return JsonUtils.getJsonResult(code, msg);
	}

	/**
	 * 按错误码获取错误信息
	 * @param code
	 * @return
	 */
	public static String getMsg(String code)
	{
		for (ResultMsgEnum msgEnum : ResultMsgEnum.values())
		{
			if (StringUtils.equals(msgEnum.code, code))
			{
				return msgEnum.msg;
			}
		}
		return "";
	}
}
