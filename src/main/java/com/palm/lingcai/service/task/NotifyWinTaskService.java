package com.palm.lingcai.service.task;

import java.util.List;
import java.util.Map;

import org.nutz.http.Http;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.palm.commom.uitl.PalmBuildParams;
import com.palm.lingcai.service.AccountService;
import com.palm.lingcai.service.MarketplanService;
import com.palm.lingcai.service.UserLotteryService;
import com.palm.lingcai.statuscode.Common;

@Service
public class NotifyWinTaskService {
	private static Logger logger = LoggerFactory.getLogger(NotifyWinTaskService.class);
	@Autowired
	private UserLotteryService userLotteryService;
    @Autowired
    private AccountService accountService;
	@Autowired
	private MarketplanService marketplanService;

	/**
	 * 推送上一期的中奖信息
	 */
	public void notifyWinByInssueNo() {
		String winStatus_NO = "N";
		String winStatus_YES = "Y";
		int notifyWinTimes = 5; 
		int limit = 1000;
		
		List<Map<String, Object>> list = userLotteryService.notifyWin(notifyWinTimes, limit);
		logger.info("----win notify -记录数量{}----",list.size());
		if (list == null || list.size() == 0) {
			return;
		}
		
		for (Map<String, Object> map : list) {
			try {
				String channelOrder = Common.NullToStr(map.get("channelOrder"));
				String issueNo = Common.NullToStr(map.get("issueNo"));
				String gameid = Common.NullToStr(map.get("gameid").toString());
				String prizeMoney = Common.NullToStr(map.get("prizeMoney"));
				String rewardMoney = Common.NullToStr(map.get("rewardMoney"));
				String prizeball = Common.NullToStr(map.get("prizeball"));
				String prizeLevel = Common.NullToStr(map.get("prizeLevel"));
				String prizeMoneyAfterTax = Common.NullToStr(map.get("prizeMoneyAfterTax"));
				String tax = Common.NullToStr(map.get("tax"));
				String signKey = Common.NullToStr(map.get("market_app_secret"));
				String notifyWinUrl = Common.NullToStr(map.get("notify_win_url"));
				Long userLotteryId = (Long) map.get("userLotteryId");
				
				//参数
				Map<String,String> paramsMap=Maps.newHashMap();
				paramsMap.put("orderId", channelOrder);
				paramsMap.put("issueNo", issueNo);
				paramsMap.put("gameId", gameid);
				paramsMap.put("prizeMoney", prizeMoney);
				paramsMap.put("rewardMoney", rewardMoney);
				paramsMap.put("prizeNo", prizeball);
				paramsMap.put("prizeLevel", prizeLevel);
				paramsMap.put("prizeMoneyAfterTax", prizeMoneyAfterTax);
				paramsMap.put("tax", tax);
				
				// 签名
				String sign=PalmBuildParams.buildRequestMysign(paramsMap,signKey);
				paramsMap.put("sign", sign);
				
				// 发送
				String url = notifyWinUrl + "?";
				for (String key : paramsMap.keySet()) {
					url += "&" + key + "=" + paramsMap.get(key);
				}
			 
				logger.info("--- win notify: send url---" + url);
				String result = Http.get(url).getContent("utf-8");
				logger.info("--- win notify: result-----" + result);
				
				String flag = parseResult(result);
				if ("1".equals(flag)) {
					userLotteryService.updateWinStatus(userLotteryId, winStatus_YES);
				}else{
					userLotteryService.updateWinStatus(userLotteryId, winStatus_NO);
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("----win notify --Exception!----");
			}
		}
	}
	
	private String parseResult(String result) {
		try {
			JSONObject jsonObject = JSON.parseObject(result);
			return (String) jsonObject.get("result");
		} catch (Exception e) {
			logger.error("---JSONObject parseObject error ! text=" + result);
		}
		return null;
	}
}
