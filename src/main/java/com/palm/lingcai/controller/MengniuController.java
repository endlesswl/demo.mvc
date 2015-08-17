package com.palm.lingcai.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.palm.commom.uitl.StringUtil;
import com.palm.lingcai.controller.interceptor.LotVerifyType;
import com.palm.lingcai.entity.MarketRotateRule;
import com.palm.lingcai.entity.Marketplan;
import com.palm.lingcai.service.MarketRotateRuleService;
import com.palm.lingcai.service.MarketplanService;
import com.palm.lingcai.service.marketplan.MengniuService;
import com.palm.lingcai.service.redis.RedisService;
import com.palm.lingcai.statuscode.UnitPriceEnum;
import com.palm.lingcai.util.PrizeDrawUtil;

/**
 * API for 蒙牛
 * 
 * @author LDL
 */
@Controller
public class MengniuController {
	private static Logger logger = LoggerFactory.getLogger(LotApiController.class);
	@Autowired
	private MengniuService mengniuService;
	@Autowired
	private MarketplanService marketplanService;
	@Autowired
	private RedisService redisService;
	@Autowired
	private MarketRotateRuleService marketRotateRuleService;
	
	/**
	 * 蒙牛下单接口
	 * (按概率随机获取彩票面额)
	 */
	@LotVerifyType(verifyLotSign = true,verifyChannelExist=true)
	@RequestMapping(value = "/orderMnhd", method = RequestMethod.GET)
	@ResponseBody
	public String orderMnhd(
			@RequestParam(value = "orderId") String orderId,
			@RequestParam(value = "channel") String channelKey,
			@RequestParam(value = "fromMobile") String fromMobile,
			@RequestParam(value = "toMobile") String toMobile,
			@RequestParam(value = "toOpenId") String toOpenId,
			@RequestParam(value = "gameId", defaultValue = "SSQ") String gameId,
			@RequestParam(value = "sign") String sign,
			HttpServletRequest request) {
		try {
			//根据概率生成彩票面额
			String rule = this.getMarketRule();
			String moneyType = PrizeDrawUtil.draw(rule);
			moneyType = UnitPriceEnum.formatPrice(moneyType);//格式化面额
			
			logger.info("----随机抽取彩票面额moneyType={}",moneyType);
			UnitPriceEnum priceEnum = UnitPriceEnum.getUnitPriceByAlias(moneyType);
			String unitPriceCode = priceEnum.getCode();
			
			
			mengniuService.mengniu_order(channelKey, orderId, unitPriceCode, moneyType, toMobile, toOpenId,gameId,fromMobile, request);
			return getOrderResult(orderId, "1","");
		} catch (Exception e) {
			e.printStackTrace();
			return getOrderResult(orderId, "2",e.getMessage());
		}
	}
	
	/**
	 * 下单结果返回
	 * 	1-成功，2-失败
	 * @param resultCode
	 * @param resultMsg
	 * 
	 * @return
	 */
	private String getOrderResult(String orderId, String result,String mesg) {
		if (mesg == null || mesg.length() > 100) {
			mesg = "error";
		}
		JSONObject obj = new JSONObject();
		obj.put("orderId", orderId);
		obj.put("result", result);
		obj.put("mesg", mesg);
		return obj.toString();
	}
	
	/**
	 * 获取概率规则
	 * @return
	 */
	private String getMarketRule() {
		String redisKey = "mengniu_moneyType_rule";
		String rotateName = "MENG_NIU_RULE";
		String str = redisService.get(redisKey);
		if (StringUtil.isEmpty(str)) {
			MarketRotateRule rule = marketRotateRuleService.getByName(rotateName);
			redisService.set(redisKey, JSON.toJSONString(rule));
			return rule.getRotateRule();
		} else {
			MarketRotateRule rule = JSON.parseObject(str,MarketRotateRule.class);
			return rule.getRotateRule();
		}
	}
	
	
}
