package com.palm.lingcai.util.sms;

import org.nutz.http.Http;
import org.nutz.http.Response;

/**
 * 发送短信
 * 
 * @author LDL
 * 
 */
public class SmsSendUtil {
	/**
	 * 发送消息
	 */
	public static String sendMessage(String mobile, String content) {
		try {
			SmsContentBean smsContentBean = new SmsContentBean();
			smsContentBean.setPhone(mobile);
			smsContentBean.setMsgcont(content);
			Response response = Http.get(smsContentBean.toUrlPostfix(),SmsContentBean.SMS_SERVER_TIMEOUT);
			String result = response.getContent();
			System.out.println("send sms...mobile=" + mobile + ",content="+ content + ",return=" + result);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "send error";
	}
}
