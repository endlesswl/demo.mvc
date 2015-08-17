package com.palm.lingcai.service.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.http.Http;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.palm.commom.uitl.PalmBuildParams;
import com.palm.lingcai.service.AccountService;
import com.palm.lingcai.service.MarketplanService;
import com.palm.lingcai.service.UserLotteryService;

/**
 * 出票成功状态推送给渠道
 * @author LDL
 *
 */
public class NotifyOrderListener<T> implements InitializingBean, DisposableBean {
	private static Logger logger = LoggerFactory.getLogger(NotifyOrderListener.class);
	@Autowired
	private UserLotteryService userLotteryService;
    @Autowired
    private AccountService accountService;
	@Autowired
	private MarketplanService marketplanService;
	private long idleTime = 2;// 秒
	private Thread listenerThread;
	private boolean isClosed;
	
	public void setIsClosed(boolean isClosed) {
		this.isClosed = isClosed;
	}

	public void setIdleTime(int idleTime) {
		this.idleTime = idleTime;
	}
	
	/**
	 * 注销
	 * 
	 * @throws Exception
	 */
	@Override
	public void destroy() throws Exception {
		if (isClosed) {
			return;
		}
		shutdown();
	}

	private void shutdown() {
		try {
			listenerThread.interrupt();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 初始化
	 *
	 * @throws Exception
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		listenerThread = new ListenerThread();
		listenerThread.setDaemon(true);
		listenerThread.start();
	}
	
	/**
	 * 线程，不停的扫
	 */
	class ListenerThread extends Thread {
		@Override
		public void run() {
			logger.info("---NotifyOrderListener-start");
			while (true) {
				try {
					//查询
					String notifyStatus_NO = "N";
					String notifyStatus_YES = "Y";
					int quantity = 5000;// 分批查询
					int notifyTimes = 50;//最大推送次数
					String successFlag = "3";
					
					Map<String, Object> searchParams = new HashMap<String, Object>();
					searchParams.put("notifyStatus", notifyStatus_NO);
					searchParams.put("successFlag", successFlag);
					
					List<Map<String, Object>> list = userLotteryService.searchNotifyOrder(successFlag,notifyTimes, quantity);
					logger.info("----NotifyOrderListener -list.size ---"+list.size());
					
					if (list == null || list.size() == 0) {
						continue;
					}
					
					Long userLotteryId = null;
					for (Map<String, Object> map : list) {
						try {
							userLotteryId = (Long) map.get("userLotteryId");
							String notify_url = (String) map.get("notify_url");
							String signKey = (String) map.get("market_app_secret");
							// 参数
							Map<String, String> paramsMap = new HashMap<String, String>();
							paramsMap.put("orderId", (String) map.get("channelOrder"));
							paramsMap.put("status",	map.get("sucessFlag").toString());
							paramsMap.put("gameId", map.get("gameid").toString());
							paramsMap.put("moneyType",  map.get("money").toString());
							paramsMap.put("ballNo", map.get("ball").toString());
							paramsMap.put("issueNo", map.get("issueNo").toString());
							// 签名
							String sign = PalmBuildParams.buildRequestMysign(paramsMap, signKey);
							paramsMap.put("sign", sign);
		
							// 发送
							String url = notify_url + "?";
							for (String key : paramsMap.keySet()) {
								url += "&" + key + "=" + paramsMap.get(key);
							}
							
							// 结果
							logger.info(" send url--" + url);
							String result = Http.get(url).getContent("UTF-8");
							logger.info(" send result ----" + result);

							String flag = parseResult(result);
							if ("1".equals(flag)) {
								userLotteryService.updateNotifyStatus(userLotteryId, notifyStatus_YES);
							} else {
								userLotteryService.updateNotifyStatus(userLotteryId, notifyStatus_NO);
							}
						} catch (Exception e) {
							e.printStackTrace();
							try {
								userLotteryService.updateNotifyStatus(userLotteryId, notifyStatus_NO);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							logger.error("---NotifyOrderListener---订单状态推送渠道异常");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						Thread.sleep(idleTime * 1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
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
}
