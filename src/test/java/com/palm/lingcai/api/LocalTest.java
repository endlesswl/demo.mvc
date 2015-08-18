package com.palm.lingcai.api;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
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
public class LocalTest {
//	public static void main(String[] args) {
//		JSONObject json = new JSONObject();
//		JSONArray jdataArr = new JSONArray();
//		JSONObject jdata = new JSONObject();
//		jdata.put("bankName", "交通银行");
//		jdata.put("bankNo", "6222629530002072529");
//		jdata.put("cardNo", "510781199002221016");
//		jdata.put("mobile", "cFnGKAloKllUgwhNkvYEaA==");
//		jdata.put("money", "");
//		jdata.put("orderId", "2014122616081910808,2014122616293710813,2014123014221110929,2014123110302010968,2014123110302010962,2014123110302010991,2014123110302010986,2014123110302010949,2014123110302010947,2014123110302010984,2014123110302010975,2014123110302010976,2014122615574710798,2014123110302010945,2014123110302010964,2014123110302010957,2014123110302010989,2014123110302010985,2014123110302010965,2014123110302010978,2014123110302010960,2014123110302010988,2014123110302010963,2014123110302010980,2014123110302010961,2014123110302010971,2014123110302010946,2014123110302010952,2014123110302010982,2014123110302010955,2014123110302010987,2014123110302010967,2014123110302010951,2014123110302010944,2014123110302010974,2014123110302010979,2014123110302010948,2014123110302010942,2014123110302010958,2014123110302010959,2014123110302010983,2014123110302010981,2014123110302010966,2014123110302010973,2014123110302010953,2014123110302010956,2014123110302010970,2014123110302010954,2014123110302010969,2014123110302010950,2014123110302010977,2014123110302010943,2014123110302010941,2014123110302010972,2014123110302010992,2014123110302010990,2014123113351011043,2015010509402711060,2015010514411611065,2015010714082211664,2015010714163911674,2015010714082211665,2015010714155811673,2015010714173311675,2015010714082211666,2015010810114511693,2015010810114511689,2015010810383411697,2015010810383411696,2015010810383411698,2015010810383411695,2015010810383411699,2015010810383411700,2015010810114511688,2015010810114511694,2015010810114511691,2015010810114511692,2015010810114511690");
//		jdata.put("serialNo", "201501081216253500");
//		jdata.put("username", "12312321");
//		jdataArr.add(jdata);
//		
//	}
//	
//	/**
//	 * 、下订单_test_1
//	 */
//	@Test
//	public void order(){ 
//		try {
//			String signKey = "123456";
//			Map<String, String> paramMap = new HashMap<String, String>();
//			paramMap.put("channel", "lingcaibao");
//			paramMap.put("orderId", "LDL"+UUIDUtils.getSerialID());
//			paramMap.put("mobile", "13426297024");
//			paramMap.put("gameId", "SSQ");
//			paramMap.put("moneyType", "1");
//			String sign = PalmBuildParams.buildRequestMysign(paramMap,signKey);
//			System.out.println(sign); 
//			paramMap.put("sign", sign); 
//			
//			//URLEncoder
//			paramMap = paramToURLEncoder(paramMap, "UTF-8");		 	
//			
//			//send
//			String url = "http://localhost:8082/lingcaiapi/order";
//			url= paramToUrl(url,paramMap);
//			System.out.println("url:"+url);
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
//		String url = "http://localhost:8082/lingcaiapi/batchOrder";
//		try {
//			//模拟参数
//			Map<String, String> paramMap = new HashMap<String, String>();
//			paramMap.put("channel", "lcb");
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
//			String signKey = "123456";
//			Map<String, String> paramMap = new HashMap<String, String>();
//			paramMap.put("channel", "lcb");
//			paramMap.put("orderId", "20141212003");
//			paramMap.put("mobile", "13426297024");
//			paramMap.put("gameId", "SSQ");
//			String sign = PalmBuildParams.buildRequestMysign(paramMap,signKey);
//			System.out.println(sign);
//			paramMap.put("sign", sign);
//			
//			//URLEncoder
//			paramMap = paramToURLEncoder(paramMap, "UTF-8");			
//			
//			//send
//			String url = "http://localhost:8082/lingcaiapi/getOrderState";
//			url= paramToUrl(url,paramMap);
//			System.out.println("url:"+url);
//			//return
//			String result = Http.get(url).getContent();
//			System.out.println(result);
//			JSONObject jsonObject = JSON.parseObject(result);
//			JSONArray array = JSON.parseArray(jsonObject.get("result").toString());
//			JSONObject record = (JSONObject) array.get(0);
//			System.out.println(record.get("orderId"));
//			System.out.println(record.get("status"));
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
//			String signKey = "123456";
//			Map<String, String> paramMap = new HashMap<String, String>();
//			paramMap.put("checkType", "2");
//			paramMap.put("typeNo", "");
//			paramMap.put("channel", "lcb");
//			paramMap.put("gameId", "SSQ");
//			String sign = PalmBuildParams.buildRequestMysign(paramMap,signKey);
//			System.out.println(sign);
//			paramMap.put("sign", sign);
//			
//			//URLEncoder
//			paramMap = paramToURLEncoder(paramMap, "UTF-8");			
//			
//			//send
//			String url = "http://localhost:8082/lingcaiapi/check";
//			url= paramToUrl(url,paramMap);
//			System.out.println("url:"+url);
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
//			String signKey = "123456";
//			Map<String, String> paramMap = new HashMap<String, String>();
//			paramMap.put("channel", "aaa");
//			paramMap.put("gameId", "SSQ");
//			paramMap.put("issueNo", "2014007");
//			paramMap.put("orderId", "aaaaa");
//			String sign = PalmBuildParams.buildRequestMysign(paramMap,signKey);
//			System.out.println(sign);
//			paramMap.put("sign", sign);
//			
//			//URLEncoder
//			paramMap = paramToURLEncoder(paramMap, "UTF-8");			
//			
//			//send
//			String url = "http://localhost:8082/lingcaiapi/prize";
//			url= paramToUrl(url,paramMap);
//			System.out.println("url:"+url);
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
//			String url = "http://localhost:8082/lingcaiapi/getPreviousBall";
//			url= paramToUrl(url,paramMap);
//			System.out.println("url:"+url);
//			//return
//			String result = Http.get(url).getContent();
//			System.out.println(result);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	/**
//	 * 上期开奖号码查询
//	 */
//	@Test
//	public void getPreviousBalls(){
//		try {
//			Map<String, String> paramMap = new HashMap<String, String>();
//			//send
//			String url = "http://localhost:8082/lingcaiapi/getPreviousBalls";
//			url= paramToUrl(url,paramMap);
//			System.out.println("url:"+url);
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
//			String signKey = "123456";
//			Map<String, String> paramMap = new HashMap<String, String>();
//			paramMap.put("channel", "lcb");
//			paramMap.put("orderId", "20140100012");
//			paramMap.put("mobile", "ulCVBPH5E5vguLaV5h4k6Q==");
//			paramMap.put("money", "5");
//			paramMap.put("username", "梁大龙");
//			paramMap.put("bankNo", "1212122222222222");
//			paramMap.put("bankName", "招商银行");
//			paramMap.put("cardNo", "410104198609100030");
//			String sign = PalmBuildParams.buildRequestMysign(paramMap,signKey);
//			System.out.println(sign);
//			paramMap.put("sign", sign);
//			
//			//URLEncoder
//			paramMap = paramToURLEncoder(paramMap, "UTF-8");			
//			
//			//send
//			String url = "http://localhost:8088/lot/getMoney";
//			url= paramToUrl(url,paramMap);
//			System.out.println("url:"+url);
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
//	 * POST传输，数据放入header
//	 */
//	@Test
//	public void getMoney_batch(){
//		String url = "http://localhost:8082/lingcaiapi/getMoney";
//		//String dataStr = "{\"channel\": \"chuanyu\",\"data\": [{\"mobile\": \"13520770811\",\"gameId\": \"SSQ\",\"moneyType\": \"0.02\", \"orderId\": \"20140100002\",\"ballNo\":\"\"},{\"mobile\": \"13520770811\",\"gameId\": \"SSQ\",\"moneyType\": \"0.02\", \"orderId\": \"20140100001\",\"ballNo\":\"\"}],\"sign\": \"abcdefg\"}";
//		try {
//			//模拟参数
//			Map<String, String> paramMap = new HashMap<String, String>();
//			paramMap.put("channel", "lcb");
//			paramMap.put("data","[{\"bankName\":\"电击小子\",\"bankNo\":\"555555551231212\",\"cardNo\":\"534343432423423432\",\"mobile\":\"13426297024\",\"money\":\"\",\"serialNo\":\"90001\",\"username\":\"猪猪侠\"},"
//							+ "{\"bankName\":\"电击小子\",\"bankNo\":\"555555551231212\",\"cardNo\":\"534343432423423432\",\"mobile\":\"13880074423\",\"money\":\"\",\"serialNo\":\"90001\",\"username\":\"猪猪侠\"}]" );
//			
//			//对channel值和data值签名
//			String sign = PalmBuildParams.buildRequestMysign(paramMap,"haoyi_lingcaibao_teamwork_herry");
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
