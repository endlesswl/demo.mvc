package com.palm.lingcai.controller.interceptor;

import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.palm.commom.uitl.PalmBuildParams;
import com.palm.lingcai.entity.Marketplan;
import com.palm.lingcai.service.MarketplanService;

/**
 * @author kelylmall
 * @version V1.0
 * @Title:验证拦截器
 * @Description:(验证拦截器)
 * @email ming.li@lingcaibao.com
 * @date 2014年5月3日 下午4:25:18
 */
@Repository
public class LotVerifyAnnotationInterceptor extends HandlerInterceptorAdapter {
	
	private static Logger logger = LoggerFactory.getLogger(LotVerifyAnnotationInterceptor.class);
	
	@Autowired
	private MarketplanService marketplanService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		logger.info("verify sign");
		HandlerMethod handlerMethod = null;
		try {
			handlerMethod = (HandlerMethod) handler;
		} catch (Exception e) {
			logger.error("verifiy sign handler:{}", e);
		}
		if (handlerMethod == null) {// 如果请求地址错误则返回404的地址错误
			return super.preHandle(request, response, handler);
		}

		Method method = handlerMethod.getMethod();

		LotVerifyType annotation = method.getAnnotation(LotVerifyType.class);
		if (annotation == null) {
			return super.preHandle(request, response, handler);
		}

		// check marketplan is exist
		Marketplan marketplan = null;
		boolean verifyChannelExistFlag = annotation.verifyChannelExist();
		if (verifyChannelExistFlag) {
			String channelAppKey = request.getParameter("channel");
			marketplan = marketplanService.getByMarketplanKey(channelAppKey);
			
			if (marketplan == null) {
				String resultMsg = getResultMsg(
						VerifyTypeEnum.INVALID_CHANNELAPPKEY.getCode(),
						VerifyTypeEnum.INVALID_CHANNELAPPKEY.getMsg());
				response.getWriter().print(resultMsg);
				return false;
			}
		}
		
		//sign
		boolean verifyLotSign = annotation.verifyLotSign();
		if (verifyLotSign) {
			Map<String, String> paramsMap = getRequestParams(request);
			String sign = (String) paramsMap.get("sign");// 签名字符串
			
			logger.info("verfiy sign params:{}", JSON.toJSONString(paramsMap));
			paramsMap.remove("sign");
			logger.info("paramsMap sign:{}", JSON.toJSONString(paramsMap));
			String mySign = PalmBuildParams.buildRequestMysign(paramsMap,marketplan.getMarketAppSecret());
			logger.info("mySign---:{}", mySign);
			if (!mySign.equals(sign)) {
				// 验签错误
				String resultMsg = getResultMsg(VerifyTypeEnum.SIGN_ERROR.getCode(),VerifyTypeEnum.SIGN_ERROR.getMsg());
				response.getWriter().print(resultMsg);
				return false;
			}
			
		}
		
		//验证签名-报文验签,从header获取报文
 		boolean needSign_batchOrder = annotation.verifySignHeaderData();
 		if (needSign_batchOrder) {
 			Map<String, String> paramsMap = this.getPostParameters(request);
 			if(paramsMap == null){
 				String resultMsg = getResultMsg(VerifyTypeEnum.PARAM_EMPTY.getCode(),VerifyTypeEnum.PARAM_EMPTY.getMsg());
				response.getWriter().print(resultMsg);
				return false;
 			}
			String channelAppKey = paramsMap.get("channel");
			String sign = paramsMap.get("sign");
			String data = paramsMap.get("data");
			
 			//渠道是否存在
			marketplan = marketplanService.getByMarketplanKey(channelAppKey);
			if (marketplan == null) {
				String resultMsg = getResultMsg(VerifyTypeEnum.INVALID_CHANNELAPPKEY.getCode(),VerifyTypeEnum.INVALID_CHANNELAPPKEY.getMsg());
				response.getWriter().print(resultMsg);
				return false;
			}
			
			//参数检查
			if (StringUtils.isBlank(channelAppKey) || StringUtils.isBlank(sign) || StringUtils.isBlank(data)) {
				String resultMsg = getResultMsg(VerifyTypeEnum.PARAM_EMPTY.getCode(),VerifyTypeEnum.PARAM_EMPTY.getMsg());
				response.getWriter().print(resultMsg);
				return false;
			}
			paramsMap.remove("sign");
			marketplan = marketplanService.getByMarketplanKey(channelAppKey);
			if (marketplan == null) {
				String resultMsg = getResultMsg(VerifyTypeEnum.INVALID_CHANNELAPPKEY.getCode(),VerifyTypeEnum.INVALID_CHANNELAPPKEY.getMsg());
				response.getWriter().print(resultMsg);
				return false;
			}
			
			logger.info("----params---:{}",JSON.toJSONString(paramsMap));
			String mySign = PalmBuildParams.buildRequestMysign(paramsMap,marketplan.getMarketAppSecret());
			logger.info("----mySign---:{}", mySign);
			if (!mySign.equals(sign)) {
				// 验签错误
				String resultMsg = getResultMsg(VerifyTypeEnum.SIGN_ERROR.getCode(),VerifyTypeEnum.SIGN_ERROR.getMsg());
				response.getWriter().print(resultMsg);
				return false;
			}
 		}

		return super.preHandle(request, response, handler);
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	private String getResultMsg(String resultCode, String resultMsg) {
		StringBuffer sbResult = new StringBuffer();
		sbResult.append("{").append("\"resultCode\"").append(":").append("\"")
				.append(resultCode).append("\",\"resultMsg\"").append(":")
				.append("\"").append(resultMsg).append("\"}");
		return sbResult.toString();
	}

	/**
	 * 获取request的参数返回
	 * 
	 * @param request
	 * @return Map
	 */
	private Map<String, String> getRequestParams(HttpServletRequest request) {
		Map requestParams = request.getParameterMap();
		StringBuffer signStr = new StringBuffer();
		Map<String, String> params = Maps.newHashMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			if (signStr.toString() != null && !signStr.toString().equals("")) {
				signStr.append("&");
			}
			// 乱码解决，这段代码在出现乱码时使用
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
			signStr.append(name).append("=").append(valueStr);
		}
		return params == null ? null : params;
	}
	
	/**
	 * 从header获取参数
	 * @param request
	 * @param paramName
	 * @return
	 */
	private Map<String, String> getPostParameters(HttpServletRequest request) {
		try {
			Map<String, String> params = Maps.newHashMap();

			String dataStr = request.getParameter("data");
			logger.info("---------------dataStr  ="+dataStr);
 			dataStr = URLDecoder.decode(dataStr, "UTF-8");
 			logger.info("---------------dataStr  URLDecoder="+dataStr);
			JSONObject jsonObject = JSON.parseObject(dataStr);
			for (String key : jsonObject.keySet()) {
				Object value = jsonObject.get(key);
				params.put(key, value.toString());
			}
			return params;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
