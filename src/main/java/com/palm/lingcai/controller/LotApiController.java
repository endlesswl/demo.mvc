package com.palm.lingcai.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.palm.commom.entityenum.GameEnum;
import com.palm.lingcai.entity.Bank;
import com.palm.lingcai.entity.Marketplan;
import com.palm.lingcai.entity.User;
import com.palm.lingcai.entity.UserPrize;
import com.palm.lingcai.entity.Withdraw;
import com.palm.lingcai.service.AccountService;
import com.palm.lingcai.service.MarketplanService;
import com.palm.lingcai.service.PrizeService;
import com.palm.lingcai.service.UserLotteryService;
import com.palm.lingcai.service.UserPrizeService;
import com.palm.lingcai.service.WithdrawService;
import com.palm.lingcai.service.redis.RedisService;
import com.palm.lingcai.statuscode.Common;
import com.palm.lingcai.statuscode.LotteryNameEnum;
import com.palm.lingcai.statuscode.RedisKeyEnum;
import com.palm.lingcai.statuscode.UnitPriceEnum;
import com.palm.lingcai.util.ProPertiesUtils;
import com.palm.lingcai.controller.interceptor.LotVerifyType;

@Controller
public class LotApiController {
	private static Logger logger = LoggerFactory.getLogger(LotApiController.class);
	@Autowired
	private UserLotteryService userLotteryService;
	@Autowired
	private MarketplanService marketplanService;
	@Autowired
	private UserPrizeService userPrizeService;
	@Autowired
	private PrizeService prizeService;
	@Autowired
	private WithdrawService withdrawService;
	@Autowired
	private AccountService accountService;
	@Autowired
    public StringRedisTemplate stringRedisTemplate;
	@Autowired
    public RedisService redisService;
	
	/**
	 * 商户下单接口
	 * @param orderid 订单id
	 * @param mobile  
	 * @param gametype
	 * @param notifyurl
	 * @param gameid
	 * @param integral
	 * @param channelKey
	 * @param response
	 * @return
	 */
	@LotVerifyType(verifyLotSign = true,verifyChannelExist=true)
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	@ResponseBody
	public String order(
			@RequestParam(value = "orderId", required = true) String orderId,
			@RequestParam(value = "mobile") String mobile,
			@RequestParam(value = "gameId") String gameId,
			@RequestParam(value = "moneyType") String money,
			@RequestParam(value = "channel") String channelKey,
			@RequestParam(value = "userId", required = false) String userId,
			@RequestParam(value = "ballNo", required = false) String ballNo,
			@RequestParam(value = "sign") String sign,
			@RequestParam(value = "isRegister", defaultValue = "true") String isRegister,
			HttpServletRequest request){
		try {
			GameEnum genum = GameEnum.getGameType(gameId);
			if (genum == null) {
				return getOrderResult(orderId, "2", "彩种错误");
			}
			money = UnitPriceEnum.formatPrice(money);
			if(!UnitPriceEnum.checkUnitPriceAlias(money)){
				return getOrderResult(orderId, "2","彩票面额错误");
			}
			UnitPriceEnum priceEnum = UnitPriceEnum.getUnitPriceByAlias(money);
			String unitPriceCode = priceEnum.getCode();
			
			if (StringUtils.isNotBlank(ballNo)
					&& !unitPriceCode.equals(UnitPriceEnum.UNIT_PRICE_200.getCode())) {
				return getOrderResult(orderId, "2", "传球号时彩票必须是整注");
			}
			
			Marketplan marketplan = marketplanService.getByMarketplanKey(channelKey);
			if (marketplan == null) {
				return getOrderResult(orderId, "2", "投注计划不存在");
			}
			
			userLotteryService.createOrder(marketplan,orderId,unitPriceCode,money, mobile,isRegister,gameId,ballNo,request);
			return getOrderResult(orderId, "1","");
		} catch (Exception e) {
			e.printStackTrace();
			return getOrderResult(orderId, "2",e.getMessage());
		}
	}
	
	/**
	 * 批量下单
	 * @param request
	 * @return
	 */
	@LotVerifyType(verifySignHeaderData = true)
	@RequestMapping(value = "/batchOrder", method = RequestMethod.POST)
	@ResponseBody
	public String batchOrder(HttpServletRequest request) {
		try {
			String isRegister ="true";
			StringBuffer err = new StringBuffer();
			Map<String, String> paramsMap = this.getPostParameters(request);
			// 解析数据
			String channelKey = paramsMap.get("channel");
			JSONArray dataArr = JSON.parseArray(paramsMap.get("data"));
			//循环下单
			String orderId = "";
			for (int i = 0; i < dataArr.size(); i++){
				try {
					JSONObject obj = (JSONObject) dataArr.get(i);
					String mobile = obj.getString("mobile");
					String gameId = obj.getString("gameId");
					orderId = obj.getString("orderId");
					String ballNo = obj.getString("ballNo");
					String money = obj.getString("moneyType");
					//参数检查
					if (StringUtils.isBlank(mobile) 
							|| StringUtils.isBlank(gameId)
							|| StringUtils.isBlank(orderId)
							|| StringUtils.isBlank(money)) {
						err.append(this.getBatchOrderResult(orderId, "2","参数错误"));
						continue;
					}
					money = UnitPriceEnum.formatPrice(money);
					
					GameEnum genum = GameEnum.getGameType(gameId);
					if(genum==null){
						err.append(this.getBatchOrderResult(orderId, "2","彩种错误"));
						continue;
					}
					if(!UnitPriceEnum.checkUnitPriceAlias(money)){
						err.append(this.getBatchOrderResult(orderId, "2","彩票面额错误"));
						continue;
					}
					
					UnitPriceEnum priceEnum = UnitPriceEnum.getUnitPriceByAlias(money);
					String unitPrice = priceEnum.getCode();
					if(StringUtils.isNotBlank(ballNo) && !unitPrice.equals(UnitPriceEnum.UNIT_PRICE_200.getCode())){
						err.append(this.getBatchOrderResult(orderId, "2","传球号时彩票必须是整注"));
						continue;
					}
					
					Marketplan marketplan = marketplanService.getByMarketplanKey(channelKey);
					if(marketplan==null){
						return getOrderResult(orderId, "2","投注计划不存在");
					}
					userLotteryService.createOrder(marketplan,orderId,unitPrice,money, mobile,isRegister,gameId,ballNo,request);
					err.append(this.getBatchOrderResult(orderId, "1",""));
				} catch (Exception e) {
					e.printStackTrace();
					err.append(this.getBatchOrderResult(orderId, "2",e.getMessage()));
				}
			}
			
			String result = "{\"data\":["+err.toString().substring(1)+"]}";
			logger.info("---batchOrder----"+result); 
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return getOrderResult("error", "2", e.getMessage());
		}
	}
	
	/**
	 * 渠道订单状态查询,支持批量查询
	 * @param orderIdStr 多个orderId英文逗号分隔
	 * @param mobile
	 * @param channelAppKey
	 * @param gameId
	 * @param request
	 * @return
	 */
	@LotVerifyType(verifyLotSign = true,verifyChannelExist=true)
	@RequestMapping(value = "/getOrderState", method = RequestMethod.GET)
	@ResponseBody
	public String getOrderState(
			@RequestParam("orderId") String orderIdStr,
			@RequestParam(value = "mobile", required = false) String mobile,
			@RequestParam("channel") String channelAppKey,
			@RequestParam("gameId") String gameId,
			HttpServletRequest request) {
		try {
			//查询
			Marketplan marketplan = marketplanService.getByMarketplanKey(channelAppKey);
			List<Map<String, Object>> resultMap = userLotteryService.findChannelOrderStatus(orderIdStr, mobile, marketplan, gameId);
			
			//拼结果
			String tmpstr = "";
			for (Map<String, Object> map : resultMap) {
				String orderId = (String) map.get("channelOrder");
				Integer status = (Integer) map.get("sucessFlag");
				String issueNo = (String) map.get("issueNo");
				String ball = (String) map.get("ball");
				
				tmpstr += ",{\"orderId\":\"" + orderId + "\",\"status\":\"" + status + "\",\"issueNo\":\"" + issueNo + "\",\"ball\":\"" + ball + "\"}";
			}
			
			String str = tmpstr.length() > 0 ? tmpstr.substring(1) : "";
			String result = "{\"result\":["+str+"]}";
			logger.info("---getOrderState----"+result); 
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("error"); 
			return "{result:error}";
		}
	}
	
	/**
	 * 中奖查询接口
	 * @param orderId 订单ID
	 * @param issueNo 期次
	 * @param gameId 彩票类型
	 * @param channel 渠道ID
	 * @param sign 验证签名
	 * @param request 
	 * @return 查询的数据
	 */
	@LotVerifyType(verifyLotSign = true,verifyChannelExist=true)
	@RequestMapping(value = "/prize", method = RequestMethod.GET)
	@ResponseBody
	public String getPrize(
			@RequestParam(value = "orderId",required=true) String orderId,
			@RequestParam(value = "issueNo",required=false) String issueNo,
			@RequestParam(value = "gameId",required=false) String gameId,
			@RequestParam(value = "channel",required=true) String channelKey,
			@RequestParam(value = "sign",required=true) String sign,
			HttpServletRequest request)  {
		try {
			
			Marketplan marketplan = marketplanService.getByMarketplanKey(channelKey);
			Map<String, Object> prizeMap = userPrizeService.getWinPrizeInfo(marketplan.getId(), orderId, gameId, issueNo);
			if (prizeMap == null) {
				JSONObject obj = new JSONObject();
				obj.put("orderId", orderId);
				obj.put("result", "2");
				obj.put("mesg", "订单不存在");
				return obj.toString();
			}
			
			logger.info("---------"+prizeMap.toString());
			String isPrize = Common.NullToStr(prizeMap.get("isprize"));
			
			if ("0".equals(isPrize)) {
				JSONObject obj = new JSONObject();
				obj.put("orderId", orderId);
				obj.put("result", "0");
				obj.put("mesg", "订单未中奖");
				return obj.toString();
			} else if ("-1".equals(isPrize)) {
				JSONObject obj = new JSONObject();
				obj.put("orderId", orderId);
				obj.put("result", "-1");
				obj.put("mesg", "等待开奖");
				return obj.toString();
			} else {
				JSONObject obj = new JSONObject();
				obj.put("orderId", orderId);
				obj.put("issueNo", Common.NullToStr(prizeMap.get("issueNo")));
				obj.put("gameId", Common.NullToStr(prizeMap.get("gameid")));
				obj.put("rewardMoney", Common.NullToStr(prizeMap.get("rewardMoney")));
				obj.put("prizeMoney", Common.NullToStr(prizeMap.get("prizeMoney")));
				obj.put("prizeNo", Common.NullToStr(prizeMap.get("prizeball")));
				obj.put("prizeLevel", Common.NullToStr(prizeMap.get("prizeLevel")));
				obj.put("prizeMoneyAfterTax", Common.NullToStr(prizeMap.get("prizeMoneyAfterTax")));
				obj.put("tax", Common.NullToStr(prizeMap.get("tax")));
				return obj.toJSONString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("orderId", orderId);
			jsonObject.put("result", "2");
			jsonObject.put("mesg", "系统异常");
			if(request.getParameterMap()!=null){
				logger.info("SearchPrizeController.getPrize ==== {}={}","查询失败",request.getParameterMap().toString());
			}else{
				logger.info("SearchPrizeController.getPrize ==== {}={}","查询失败","null");
			}
			return jsonObject.toString();
		}
	}
	
	
	/**
	 * 对账
	 * @param checkType
	 * @param typeNo
	 * @param gameId
	 * @param channelAppKey
	 * @param request
	 * @return
	 */
	@LotVerifyType(verifyLotSign = true,verifyChannelExist=true)
	@RequestMapping(value = "/check", method = RequestMethod.GET)
	@ResponseBody
	public String check(
			@RequestParam("checkType") String checkType,
			@RequestParam(value = "typeNo", required = false) String typeNo,
			@RequestParam("gameId") String gameId,
			@RequestParam("channel") String channelKey,
			HttpServletRequest request) {
		
		try {
			if(StringUtils.isEmpty(typeNo) && "1".equals(checkType)){
				typeNo =" ";
			}
			if(StringUtils.isEmpty(typeNo) && "2".equals(checkType)){
				//默认当前日期
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				typeNo =format.format(new Date());
			}
			if(StringUtils.isEmpty(typeNo) && "3".equals(checkType)){
				//默认当前月
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				typeNo =format.format(new Date());
			}
			
			//查询
			Marketplan marketplan = marketplanService.getByMarketplanKey(channelKey);
			Map<String, Object> map = userLotteryService.checkAccountOrder(marketplan.getId(), gameId, checkType, typeNo);
			
			Long countTotal = (Long) map.get("countTotal");
			Object moneyTotal = map.get("moneyTotal");
			String issue_no = (String) map.get("issueNo");
			if(issue_no==null && "1".equals(checkType)){
				issue_no = typeNo;
			}
			
			//结果
			String result = "{\"countTotal\":\"" + countTotal
						 + "\",\"moneyTotal\":\"" + (moneyTotal==null?0:moneyTotal) 
						 + "\",\"gameId\":\"" + gameId
						 + "\",\"issueNo\":\"" + StringUtils.trimToEmpty(issue_no) + "\"}";
			
			logger.info("check---"+result); 
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("check----error "); 
			return "error";
		}		
	}
	
	/**
	 * 中奖提现
	 * orderId和money不能都为空
	 * @param orderId
	 * @param money
	 * @param mobile
	 * @param username
	 * @param bankNo
	 * @param bankName
	 * @param cardNo
	 * @param channel
	 * @param request
	 * @return
	 */
	@LotVerifyType(verifyLotSign = true,verifyChannelExist=true)
	@RequestMapping(value = "/getMoney", method = RequestMethod.GET)
	@ResponseBody
	public String getMoney(
			@RequestParam(value = "money", required = true) String money,
			@RequestParam("serialNo") String serialNo,
			@RequestParam("mobile") String mobile,
			@RequestParam("username") String username,
			@RequestParam("bankNo") String bankNo,
			@RequestParam("bankName") String bankName,
			@RequestParam("cardNo") String cardNo,
			@RequestParam("channel") String marketAppKey,
			HttpServletRequest request) {
		try {
			Marketplan marketplan = marketplanService.getByMarketplanKey(marketAppKey);
			Bank bank = new Bank();
			bank.setUserid(marketplan.getUserId());
			bank.setBankname(bankName);
			bank.setAccountname(username);
			bank.setCardno(bankNo);
			
			//根据mobile+money提现
			withdrawService.withdrawByMoney(request, marketplan, bank, cardNo, mobile, money,serialNo);
			
			return getWithdrawResult(serialNo, "1","");
		} catch (Exception e) {
			e.printStackTrace();
			return getWithdrawResult(serialNo, "2",e.getMessage());
		}
	}
	
	/**
	 * 中奖提现--批量
	 * @param request
	 * @return
	 */
	@LotVerifyType(verifySignHeaderData = true)
	@RequestMapping(value = "/getMoney", method = RequestMethod.POST)
	@ResponseBody
	public String getMoney(HttpServletRequest request) {
		try {
			Map<String, String> paramsMap = this.getPostParameters(request);
			String marketAppKey = paramsMap.get("channel");
			Marketplan marketplan = marketplanService.getByMarketplanKey(marketAppKey);
	
			// 解析数据
			StringBuffer sb = new StringBuffer();
			JSONArray dataArr = JSON.parseArray(paramsMap.get("data"));
			for (int i = 0; i < dataArr.size(); i++){
				String serialNo ="";
				try {
					JSONObject obj = (JSONObject) dataArr.get(i);
					serialNo = obj.getString("serialNo");
					String mobile = obj.getString("mobile");
					String username = obj.getString("username");
					String bankNo = obj.getString("bankNo");
					String bankName = obj.getString("bankName");
					String cardNo = obj.getString("cardNo");
					String money = obj.getString("money");
					//参数检查
					if (StringUtils.isBlank(mobile) 
							|| StringUtils.isBlank(username) 
							|| StringUtils.isBlank(bankNo)
							|| StringUtils.isBlank(serialNo)
							|| StringUtils.isBlank(bankName)
							|| StringUtils.isBlank(money)
							|| StringUtils.isBlank(cardNo)) {
						sb.append(this.getWithdrawResult(serialNo, "2", "参数非空错误"));
						continue;
					}
				
					Bank bank = new Bank();
					bank.setUserid(marketplan.getUserId());
					bank.setBankname(bankName);
					bank.setAccountname(username);
					bank.setCardno(bankNo);
					
					//处理提现
					logger.info("---begin getMoney by mobile, money =" + money);
					withdrawService.withdrawByMoney(request, marketplan, bank, cardNo, mobile, money,serialNo);
					sb.append(this.getWithdrawResult(serialNo, "1",""));
				} catch (Exception e) {
					e.printStackTrace();
					sb.append(this.getWithdrawResult(serialNo, "2", e.getMessage()));
				}
			}
			
			String result = "{\"data\":[" + sb.toString().substring(1) + "]}";
			logger.info("getMoney result====" + result); 
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return getWithdrawResult("error", "2", e.getMessage());
		}
	}

	/**
	 * 中奖文件下载
	 * @param issueNo
	 * @param gameId
	 * @param channelId
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getPrizeFileView", method = RequestMethod.GET)
	public void getPrizeFileView(
			@RequestParam("issueNo") String issueNo,
			@RequestParam("gameId") String gameId,
			@RequestParam("channel") String channelId,
			HttpServletRequest request, 
			HttpServletResponse response) {
		OutputStream outputStream=null;
		try {
			String fileName = channelId + "-" + gameId + "-" + issueNo + ".txt";
			String channelPrizeFile = ProPertiesUtils.getChannelPrizePath();
			// 下载中奖文件
			File file = new File(channelPrizeFile + fileName);
			if (file.exists()) {
				byte[] readFileToByteArray = FileUtils.readFileToByteArray(file);
				/** 重置response **/
				response.reset();
				/** 设置HTML头部信息 **/
				response.setHeader("Content-Disposition","attachment; filename=\"" + fileName);
				response.addHeader("Content-Length", ""+ readFileToByteArray.length);
				response.setContentType("application/octet-stream;charset=UTF-8");
				/** 获得输出流 **/
				outputStream = new BufferedOutputStream(response.getOutputStream());
				/** 从字节数组中将文件写到输出流中 **/
				outputStream.write(readFileToByteArray);
				outputStream.flush();
			}else{
				response.getWriter().print("file is not found!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if (outputStream != null)
					outputStream.close();
			} catch (IOException e) {
			}
		}
	}
	
	/**
	 * 查询账户基本信息接口 
	 * @param mobile
	 * @param marketAppKey
	 * @param request
	 * @return
	 */
	@LotVerifyType(verifyLotSign = true,verifyChannelExist=true)
	@RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
	@ResponseBody
	public String getUserInfo(
			@RequestParam("mobile") String mobile,
			@RequestParam("channel") String marketAppKey) {
		
		try {
			User user = accountService.findByUserName(mobile);
			if(user==null){
				return getResult("2", "无该手机号的用户");
			}
			
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("mobile", mobile);
			jsonObj.put("balance", user.getPrize());
			jsonObj.put("result", "1");
			
			//withdraw
			List<Withdraw> wlist = withdrawService.findByUserName(mobile);
			if (wlist.size() > 0) {
				JSONArray withdrawObj = new JSONArray();
				for (Withdraw w : wlist) {
					JSONObject obj = new JSONObject();
					obj.put("username", w.getUsername());
					obj.put("bankNo", w.getCardno());
					obj.put("bankName", w.getBankname());
					obj.put("cardNo", w.getCardid());
					obj.put("money", w.getAmount());
					obj.put("serialNo", w.getSerialNo());
					obj.put("time", w.getCreatetime());
					obj.put("channelName", w.getSource());
					obj.put("sendPrizeChannelName", "零彩宝");
					withdrawObj.add(obj);
				}
				jsonObj.put("withdraw", withdrawObj);
			}
			
			//prizeInfo
			List<UserPrize> plist = userPrizeService.findByUserName(mobile);
			if (plist.size() > 0) {
				JSONArray prizeObj = new JSONArray();
				for (UserPrize p : plist) {
					JSONObject obj = new JSONObject();
					obj.put("username", p.getRef_username());
					obj.put("ballNo", p.getRef_ball());
					obj.put("issueNo", p.getRef_issueNo());
					obj.put("rewardMoney", p.getPrizeMoneyAfterTax());
					obj.put("gameId", p.getRef_gameid());
					obj.put("channelName", p.getRef_marketName());
					prizeObj.add(obj);
				}
				jsonObj.put("prizeInfo", prizeObj);
			}
			logger.info("getUserInfo---"+jsonObj.toString()); 
			return jsonObj.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return getResult("2","查询失败");
		}
	}
	
	
	/**
	 * 提现状态查询接口,支持批量查询
	 * @param serialNoStr 客户的提现流水号，多个serialNo英文逗号分隔
	 * @param channel
	 * @param request
	 * @return
	 */
	@LotVerifyType(verifyLotSign = true,verifyChannelExist=true)
	@RequestMapping(value = "/getWithdrawStatus", method = RequestMethod.GET)
	@ResponseBody
	public String getWithdrawStatus(
			@RequestParam("serialNo") String serialNoStr,
			@RequestParam("channel") String marketAppKey,
			HttpServletRequest request) {
		try {
			Marketplan marketplan = marketplanService.getByMarketplanKey(marketAppKey);
 
			String[] serialNoArr = serialNoStr.split(",");
			
			//拼参数
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("marketplanId", marketplan.getId());
			params.put("serialNos", serialNoArr);
			List<Withdraw> wlist = withdrawService.findWithdrawStatus(params);
			
			//组装结果
			String tmpstr = "";
			for (String serialNo : serialNoArr) {
				Integer status = 0;
				for (Withdraw w : wlist) {
					if (serialNo.equals(w.getSerialNo())) {
						status = w.getStatus();
						break;
					}
				}
				JSONObject json = new JSONObject();
				json.put("serialNo", serialNo);
				json.put("status", status);
				tmpstr += "," + json.toString();
			}
			
			String result = "{result:["+tmpstr.substring(1)+"]}";
			logger.info("---getWithdrawStatus result----" + result);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("error"); 
			return "{result:error}";
		}
	}
	
	/**
	 * 获取上期中奖号码
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getPreviousBalls", method = RequestMethod.GET)
	@ResponseBody
	public String getPreviousBalls() {
		try {
			String data ="";
			for (String gameId : LotteryNameEnum.gameids) {
				String key = gameId + RedisKeyEnum._PREVIOUS_PRIZE_BALL.getKey();
				String tmp = redisService.get(key);
				if (StringUtils.isNotBlank(tmp)) {
					data += tmp + ",";
				}
			}

			if (data.length() > 0) {
				data = data.substring(0, data.length() - 1);
			}
			
			String previousBalls = "{\"data\":[" + data + "]}";
			return previousBalls;
		} catch (Exception e) {
			e.printStackTrace();
			return "error:数据错误";
		}
	}
	
	//--------------------------------------------------
	//--------------------------------------------------
	//--------------------------------------------------
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
 			dataStr = URLDecoder.decode(dataStr, "UTF-8");
 			
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
	 * 下单结果返回
	 * 	1-成功，2-失败
	 * @param resultCode
	 * @param resultMsg
	 * 
	 * @return
	 */
	private String getResult(String result,String mesg) {
		if (mesg == null || mesg.length() > 100) {
			mesg = "error";
		}
		JSONObject obj = new JSONObject();
		obj.put("result", result);
		obj.put("mesg", mesg);
		return obj.toString();
	}
	
	private String getBatchOrderResult(String orderId, String status,String mesg) {
		String str = ",{\"orderId\":\"" + orderId + "\",\"result\":\"" + status + "\",\"mesg\":\"" + mesg + "\"}";
		return str;
	}
	private String getWithdrawResult(String serialNo, String status,String mesg) {
		if (mesg == null || mesg.length() > 100) {
			mesg = "error";
		}
		
		String str = ",{\"serialNo\":\"" + serialNo + "\",\"result\":\"" + status + "\",\"mesg\":\"" + mesg + "\"}";
		return str;
	}
	
}
