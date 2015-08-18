package com.palm.lingcai.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.nutz.http.Http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
//import com.palm.commom.uitl.PalmBuildParams;
//import com.palm.commom.uitl.RestPostClient;
//import com.palm.commom.uitl.UUIDUtils;

/**
 * 订单测试
 * @author LDL
 *
 */
public class BetaTest {
	
	/**
	 * 用户信息
	 */
//	@Test
//	public void getUserInfo(){
//		try {
//			Map<String, String> paramMap = new HashMap<String, String>();
//			paramMap.put("channel", "lingcaibao");
//			paramMap.put("mobile", "13426297024");
//			String sign = PalmBuildParams.buildRequestMysign(paramMap,"123456");
//			System.out.println(sign); 
//			paramMap.put("sign", sign); 
//			
//			//URLEncoder
//			paramMap = paramToURLEncoder(paramMap, "UTF-8");		 	
//			
//			//send
//			String url = "http://test.lingcaibao.com/lingcaiapi/getUserInfo";
//			url= paramToUrl(url,paramMap);
//			System.out.println(url);
//			//return
//			String userInfo_result = Http.get(url).getContent();
//			System.out.println(userInfo_result);
//			
//			JSONObject jsonObject = JSON.parseObject(userInfo_result);
//			for (String key : jsonObject.keySet()) {
//				Object value = jsonObject.get(key);
//				System.out.println(key + "=" + value);
//				if (key.equals("withdraw")) {
//					JSONArray arr = JSON.parseArray(value.toString());
//					JSONObject obj = (JSONObject) arr.get(0);
//					System.out.println("cardNo=" + obj.get("cardNo"));
//				}
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	/**
//	 * 、下订单_test_1
//	 */
//	@Test
//	public void order(){ 
//		try {
//			
//			Map<String, String> paramMap = new HashMap<String, String>();
//			paramMap.put("channel", "lingcaibao");
//			paramMap.put("orderId", "LCB_"+UUIDUtils.getSerialID());
//			paramMap.put("mobile", "13426297024");
//			paramMap.put("gameId", "SSQ");
//			paramMap.put("moneyType", "2");
//			String sign = PalmBuildParams.buildRequestMysign(paramMap,"123456");
//			System.out.println(sign); 
//			paramMap.put("sign", sign); 
//			
//			//URLEncoder
//			paramMap = paramToURLEncoder(paramMap, "UTF-8");		 	
//			
//			//send
//			String url = "http://test.lingcaibao.com/lingcaiapi/order";
//			url= paramToUrl(url,paramMap);
//			System.out.println(url);
//			//return
//			String result = Http.get(url).getContent();
//			System.out.println(result);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	/**
//	 * 批量下订单 
//	 * POST传输，数据放入header
//	 */
//	@Test
//	public void orderBatch(){
//		String url = "http://test.lingcaibao.com/lingcaiapi/batchOrder";
//		try {
//			//模拟参数
//			Map<String, String> paramMap = new HashMap<String, String>();
//			paramMap.put("channel", "lingcaibao");
//			paramMap.put("data", "[{\"ballNo\":\"04,11,14,17,25,27:03\",\"gameId\":\"SSQ\",\"mobile\":\"13426297024\",\"moneyType\":\"0.5\",\"orderId\":\"201410281139051263\",\"userId\":\"0\"},"
//					+			  "{\"ballNo\":\"06,13,18,19,20,26:10\",\"gameId\":\"SSQ\",\"mobile\":\"13426297024\",\"moneyType\":\"0.5\",\"orderId\":\"201410281139051264\",\"userId\":\"0\"}"
//					+ "]");
//			
//			//对channel值和data值签名
//			String sign = PalmBuildParams.buildRequestMysign(paramMap,"123456");
//			System.out.println(sign);
//			
//			//拼参数放header中
//			Map<String, String> headerParam = new HashMap<String, String>();
//			String datastr = "{\"sign\":\""+sign+"\",\"data\":"+paramMap.get("data")+",\"channel\":\""+paramMap.get("channel") +"\"}";
//			System.out.println("----"+datastr);
//			datastr = URLEncoder.encode(datastr,"utf-8");
//			System.out.println("----"+datastr);
//
//			headerParam.put("data", datastr);
//			
//			//send 
//			RestPostClient client = new RestPostClient();
//			String result = client.callRestRPC(url, headerParam, headerParam);
//			
//			//return
//			System.out.println(result);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	/**
//	 * 订单状态查询
//	 */
//	@Test
//	public void getOrderState(){
//		try {
//			Map<String, String> paramMap = new HashMap<String, String>();
//			paramMap.put("channel", "lingcaibao");
//			paramMap.put("orderId", "2014121715194520674");
//			paramMap.put("mobile", "13426297024");
//			paramMap.put("gameId", "DLT");
//			String sign = PalmBuildParams.buildRequestMysign(paramMap,"123456");
//			System.out.println(sign);
//			paramMap.put("sign", sign);
//			
//			//URLEncoder
//			paramMap = paramToURLEncoder(paramMap, "UTF-8");			
//			
//			//send
//			String url = "http://test.lingcaibao.com/lingcaiapi/getOrderState";
//			url= paramToUrl(url,paramMap);
//			System.out.println(url);
//			//return
//			String result = Http.get(url).getContent();
//			System.out.println(result);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	
//	/**
//	 * 对账查询 
//	 */
//	@Test
//	public void checkAccount(){
//		try {
//			Map<String, String> paramMap = new HashMap<String, String>();
//			paramMap.put("checkType", "2");
//			paramMap.put("typeNo", "");
//			paramMap.put("channel", "lingcaibao");
//			paramMap.put("gameId", "SSQ");
//			String sign = PalmBuildParams.buildRequestMysign(paramMap,"123456");
//			System.out.println(sign);
//			paramMap.put("sign", sign);
//			
//			//URLEncoder
//			paramMap = paramToURLEncoder(paramMap, "UTF-8");			
//			
//			//send
//			String url = "http://test.lingcaibao.com/lingcaiapi/check";
//			url= paramToUrl(url,paramMap);
//			System.out.println(url);
//			//return
//			String result = Http.get(url).getContent();
//			System.out.println(result);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	/**
//	 * 中奖状态查询
//	 */
//	@Test
//	public void prize(){
//		try {
//			Map<String, String> paramMap = new HashMap<String, String>();
//			paramMap.put("channel", "ulefa");
//			paramMap.put("gameId", "SSQ");
//			paramMap.put("issueNo", "2015302");
//			paramMap.put("orderId", "LB0116A");
//			String sign = PalmBuildParams.buildRequestMysign(paramMap,"123456");
//			System.out.println(sign);
//			paramMap.put("sign", sign);
//			
//			//URLEncoder
//			paramMap = paramToURLEncoder(paramMap, "UTF-8");			
//			
//			//send
//			String url = "http://test.lingcaibao.com/lingcaiapi/prize";
//			url= paramToUrl(url,paramMap);
//			System.out.println(url);
//			//return
//			String result = Http.get(url).getContent();
//			System.out.println(result);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	
//	/**
//	 * 上期开奖号码查询
//	 */
//	@Test
//	public void getPreviousBall(){
//		try {
//			Map<String, String> paramMap = new HashMap<String, String>();
//			//send
//			String url = "http://test.lingcaibao.com/lingcaiapi/getPreviousBall";
//			url= paramToUrl(url,paramMap);
//			System.out.println(url);
//			//return
//			String result = Http.get(url).getContent();
//			System.out.println(result);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	
//	
//	
//	
//	private static String paramToUrl(String url,Map<String, String> paramMap){
//		url += "?";
//		for (String key : paramMap.keySet()) {
//			url+="&"+key+"="+paramMap.get(key);
//		}
//		return url;
//	}
//	
//	private static Map<String, String> paramToURLEncoder(
//			Map<String, String> paramMap,String encode) throws UnsupportedEncodingException {
//		for (String key : paramMap.keySet()) {
//			paramMap.put(key, URLEncoder.encode(paramMap.get(key), encode));
//		}
//		return paramMap;
//	}
//	
//	
//	/**
//	 * 派奖提现
//	 */
//	@Test
//	public void getMoney(){
//		try {
//			Map<String, String> paramMap = new HashMap<String, String>();
//			paramMap.put("channel", "lingcaibao");
//			paramMap.put("serialNo", "20140100012");
//			paramMap.put("mobile", "13426297024");
//			paramMap.put("money", "5");
//			paramMap.put("username", "梁大龙");
//			paramMap.put("bankNo", "1212122222222222");
//			paramMap.put("bankName", "招商银行");
//			paramMap.put("cardNo", "410104198609100030");
//			String sign = PalmBuildParams.buildRequestMysign(paramMap,"123456");
//			System.out.println(sign);
//			paramMap.put("sign", sign);
//			
//			//URLEncoder
//			paramMap = paramToURLEncoder(paramMap, "UTF-8");			
//			
//			//send
//			String url = "http://test.lingcaibao.com/lingcaiapi/getMoney";
//			url= paramToUrl(url,paramMap);
//			System.out.println(url);
//			//return
//			String result = Http.get(url).getContent();
//			System.out.println(result);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	/**
//	 * 批量派奖提现
//	 * POST传输
//	 */
//	@Test
//	public void getMoney_batch(){
//		String url = "http://test.lingcaibao.com/lingcaiapi/getMoney";
//		//String dataStr = "{\"channel\": \"chuanyu\",\"data\": [{\"mobile\": \"13520770811\",\"gameId\": \"SSQ\",\"moneyType\": \"0.02\", \"orderId\": \"20140100002\",\"ballNo\":\"\"},{\"mobile\": \"13520770811\",\"gameId\": \"SSQ\",\"moneyType\": \"0.02\", \"orderId\": \"20140100001\",\"ballNo\":\"\"}],\"sign\": \"abcdefg\"}";
//		try {
//			//模拟参数
//			Map<String, String> paramMap = new HashMap<String, String>();
//			paramMap.put("channel", "lingcaibao");
//			paramMap.put("data","[{\"bankName\":\"招商银行\",\"bankNo\":\"6214850202171001\",\"cardNo\":\"431129198509157517\",\"mobile\":\"13426297024\",\"money\":\"1000\",\"serialNo\":\"002015870264\",\"username\":\"sssss\"}]" );
//			
//			
//			//对channel值和data值签名
//			String sign = PalmBuildParams.buildRequestMysign(paramMap,"123456");
//			
//			//拼参数放header中
//			Map<String, String> headerParam = new HashMap<String, String>();
//			String datastr = "{\"sign\":\""+sign+"\",\"data\":"+paramMap.get("data")+",\"channel\":\""+paramMap.get("channel") +"\"}";
//			System.out.println("         -- --"+datastr);
//			datastr = URLEncoder.encode(datastr,"utf-8");
//			System.out.println("URLEncoder----"+datastr);
//			
//			headerParam.put("data", datastr);
//			
//			//send 
//			RestPostClient client = new RestPostClient();
//			String result = client.callRestRPC(url, null, headerParam);
//			
//			//return
//			System.out.println(result);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
