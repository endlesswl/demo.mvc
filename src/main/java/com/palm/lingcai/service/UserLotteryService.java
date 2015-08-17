package com.palm.lingcai.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.palm.commom.uitl.DateProvider;
import com.palm.commom.uitl.MemcacheKeyConstants;
import com.palm.commom.uitl.StringUtil;
import com.palm.commom.uitl.UUIDUtils;
import com.palm.lingcai.entity.Lottery;
import com.palm.lingcai.entity.Marketplan;
import com.palm.lingcai.entity.SysParam;
import com.palm.lingcai.entity.User;
import com.palm.lingcai.entity.UserLottery;
import com.palm.lingcai.exception.LotteryException;
import com.palm.lingcai.repository.LotterySplitDao;
import com.palm.lingcai.repository.MarketplanDao;
import com.palm.lingcai.repository.UserDao;
import com.palm.lingcai.repository.UserLotteryDao;
import com.palm.lingcai.service.AccountService;
import com.palm.lingcai.service.redis.RedisListOpsService;
import com.palm.lingcai.service.task.TicketTaskService;
import com.palm.lingcai.statuscode.Common;
import com.palm.lingcai.statuscode.RedisKeyEnum;
import com.palm.lingcai.util.BallsUtils;
import com.palm.lingcai.util.sms.SmsSendUtil;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserLotteryService {
    private static Logger logger = LoggerFactory.getLogger(UserLotteryService.class);
    @Autowired
    public RedisListOpsService redisListOpsService;
    @Autowired
    private UserLotteryDao userLotteryDao;
    @Autowired
    private LotteryService lotteryService;
    @Autowired
    private AccountService accountService;
	@Autowired
	private UserDao userDao;
	@Autowired
	private MarketplanDao marketplanDao;
    @Autowired
    private LotterySplitDao lotterySplitDao;
	@Autowired
	private TicketTaskService ticketTaskService;
	@Autowired
	private SysParamService sysParamService;
    private DateProvider dateProvider = DateProvider.DEFAULT;
    private Lock lock = new ReentrantLock(); 
    
	/**
	 * 出票成功订单推送
	 * 
	 * @param successFlag
	 * @param notifyStatus
	 * @param quantity
	 * @return
	 */
	public List<Map<String, Object>> searchNotifyOrder(String successFlag,int notifyTimes, int quantity) {
		return userLotteryDao.searchNotifyOrder(successFlag,notifyTimes, quantity);
	}
	/**
	 * 中奖信息推送
	 * @param issueNo
	 * @param notifyWinStatus
	 * @param notifyWinTimes
	 * @param isPrize
	 * @param limit
	 * @return
	 */
	public List<Map<String, Object>> notifyWin(int notifyWinTimes,int limit) {
		return userLotteryDao.notifyWin(notifyWinTimes,limit);
	}
	/**
	 * 更新推送状态
	 * @param id
	 * @param notifyStatus
	 */
	public void updateWinStatus(Long id,String winStatus){
		userLotteryDao.updateWinStatus(id, winStatus);
	}
	/**
	 * 更新推送状态
	 * @param id
	 * @param notifyStatus
	 */
	public void updateNotifyStatus(Long id,String notifyStatus){
		userLotteryDao.updateNotifyStatus(id, notifyStatus);
	}
	
	public UserLottery findOnlyChannelOrder(@Param("marketId")Long marketId,@Param("channelOrder") String channelOrder){
		return userLotteryDao.findOnlyChannelOrder(marketId, channelOrder);
	}

	
	/**
	 * 下订单
	 * @param marketplan
	 * @param orderid
	 * @param unitPrice
	 * @param moneyStr
	 * @param mobile
	 * @param isRegister
	 * @param gametype
	 * @param gameid
	 * @param integral
	 * @param notifyurl
	 * @param request
	 * @throws Exception
	 */
	public void createOrder(Marketplan marketplan, String orderid,String unitPriceCode,String moneyStr,
			String mobile,String isRegister,String gameid,String ball,HttpServletRequest request) throws	Exception	{
		if (isRegister.equals("true")) {
			// 商户为普通用户下订单
			User personal = accountService.findUserByLoginName(mobile);
			if (personal == null) {
				personal = new User();
				personal.setUsername(mobile);// 设置用户账户名称
				personal.setPwd(mobile.substring(5, mobile.length()));// 设置用户默认秘密
				personal.setUserType(1);// 设置用户普通用户
				personal.setMobile(mobile);
				personal.setRegip(Common.getIpAddr(request));
				accountService.registerUser(personal);
			}
			this.ticketOrder(personal, marketplan, orderid, unitPriceCode, moneyStr, gameid, ball,request);
		} else {
			// 商户为自己下订单
			Long userId = marketplan.getUserId();
			User merchantUser = accountService.getForCache(userId);
			this.ticketOrder(merchantUser, marketplan, orderid, unitPriceCode, moneyStr, gameid, ball,request);
		}
	}
	
	//生成彩票订单，放出票队列
	public void ticketOrder(User user, Marketplan marketplan, String orderid,
			String unitPriceCode, String moneyStr, String gameid, String ball,
			HttpServletRequest request) throws Exception {
		UserLottery userlottery = userLotteryDao.findOnlyChannelOrder(marketplan.getId(),orderid);
		if (userlottery != null) {
			throw new LotteryException("订单重复", "");
		}
		
		//检查计划的活动时间
		Calendar cls = Calendar.getInstance();
		Date endTime = marketplan.getEndTime();
		Date startTime = marketplan.getStartTime();
		long timeInMillis = cls.getTimeInMillis();
		if (timeInMillis < startTime.getTime()) {
			throw new LotteryException("计划暂未开始", "");
		}
		if (endTime != null && timeInMillis > endTime.getTime()) {
			throw new LotteryException("计划已经结束", "");
		}

		//检查计划的金额
		BigDecimal money = new BigDecimal(moneyStr);
		if (marketplan.getTotalPrice().compareTo(money) < 0) {
			throw new LotteryException("营销计划金额不足", "");
		}
		
		//从缓存中获取当前期彩票
		Lottery lot_cache = ticketTaskService.getCurrentIssue(gameid);
		BigDecimal lotMoney = new BigDecimal(lot_cache.getMoney());
		
		//获取拆分队列KEY
		StringBuffer sb = new StringBuffer();
		sb.append(RedisKeyEnum.SPLIT_QUEUE.getKey());
		sb.append(gameid).append("_");
		sb.append(lot_cache.getIssueNo()).append("_");
		sb.append(marketplan.getId()).append("_");//必须区分marketPlan，lottery表需要marketId
		sb.append(moneyStr);
		String splitKey = sb.toString();
		
		boolean isNewLottery = false;
		String lotVal = null;
		String popFlag = null;//pop标志，异常时要in回去
		int inQueueFlag = 0;//in标志，异常时要pop出去
		
		try {
			int totalSplit = lotMoney.divide(money).intValue();
			if (totalSplit <= 1) {
				// 整注
				isNewLottery = true;
				Lottery lot = this.createNewLot(lot_cache, ball, marketplan,unitPriceCode);
				lotVal = lot.getId() + "_" + lot.getBall();
			} else {
				// 零彩
				lotVal = redisListOpsService.pop(splitKey);
				popFlag = lotVal;
				if (StringUtils.isEmpty(lotVal)) {
					lock.lock();
					try {
						// (同步锁中，会多个线程在排队等着进来，可能前面排队的已经初始化队列了，需要重新判断)
						lotVal = redisListOpsService.pop(splitKey);
						popFlag = lotVal;
						if (StringUtils.isEmpty(lotVal)) {
							// 生成一注彩票
							isNewLottery = true;
							Lottery lot = this.createNewLot(lot_cache, ball,marketplan, unitPriceCode);
							lotVal = lot.getId() + "_" + lot.getBall();
							
							// 拆分彩票
							for (int i = 0; i < (totalSplit - 1); i++) {
								redisListOpsService.in(splitKey, lotVal);
								inQueueFlag ++;//记录下来用来回滚
							}
						}
					} finally {
						lock.unlock();
					}
				}
			}
			
			//保存下单记录
			String[] val_arr = lotVal.split("_");
			String lotteryId = val_arr[0];
			String ballNo = val_arr[1];
			UserLottery userLottery=new UserLottery();
			userLottery.setBall(ballNo);
			userLottery.setGameid(gameid);
			userLottery.setIssueNo(lot_cache.getIssueNo());
			userLottery.setLotteryid(Long.parseLong(lotteryId));
			userLottery.setMarketid(marketplan.getId());
			userLottery.setMoney(money);
			userLottery.setReqHead(request.getHeader("user-agent"));
			userLottery.setReferer(request.getHeader("referer"));
			userLottery.setScanip(getIpAddr(request));
			userLottery.setSource("零彩API");
			userLottery.setUsername(user.getUsername());
			userLottery.setUserid(user.getId());
			userLottery.setChannelOrder(orderid);
			userLottery.setOrderid(UUIDUtils.getSerialID()+"");
			userLottery.setCreatetime(dateProvider.getDate());
			userLottery.setNotifyStatus("N");
			userLottery.setNotifyTimes(0);
			userLottery.setNotifyWinStatus("N");
			userLottery.setNotifyWinTimes(0);
			if(!StringUtil.isEmpty(request.getParameter("drawId"))){
				userLottery.setDrawid(Integer.valueOf(request.getParameter("drawId")));
			}
			
			BigDecimal total = new BigDecimal(lot_cache.getMoney());
			BigDecimal gainPercent = money.divide(total, 2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
			userLottery.setGainPercent(gainPercent);
			userLotteryDao.insert(userLottery);
			
			//更新用户余额、营销计划余额、记录流水
			this.updateMarketBalance(marketplan, money);

			// 新生成彩票放入出票队列
			if (isNewLottery) {
				redisListOpsService.in(MemcacheKeyConstants.WAIT_TICKET_QUEUE, lotteryId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			//拆分队列回滚
			if(StringUtils.isNotEmpty(popFlag)){
				//从队列pop要回滚in
				redisListOpsService.in(splitKey, popFlag);
			}
			for (int i = 0; i < inQueueFlag; i++) {
				//往队列in，要pop出去
				redisListOpsService.pop(splitKey);
			}
			throw new LotteryException(e.getMessage());
		}
	}
	
	private Lottery createNewLot(Lottery lot_cache, String ball,Marketplan marketplan, String unitPriceCode) {
		Lottery lottery = new Lottery();
		lottery.setMoney(lot_cache.getMoney());
		String ballNew = BallsUtils.randomByGameId(lot_cache.getGameid());
		lottery.setBall(StringUtils.isBlank(ball) ? ballNew : ball);
		lottery.setCreatetime(dateProvider.getDate());
		lottery.setGameid(lot_cache.getGameid());
		lottery.setGametype(lot_cache.getGametype());
		lottery.setMarketid(marketplan.getId());
		lottery.setUnitprice(unitPriceCode);
		lottery.setRemainder(0);
		lottery.setPrizetime(lot_cache.getPrizetime());
		lottery.setStarttime(lot_cache.getStarttime());
		lottery.setEndtime(lot_cache.getEndtime());
		lottery.setIssueNo(lot_cache.getIssueNo());
		lottery.setModifytime(dateProvider.getDate());
		lottery.setOrderid(UUIDUtils.getSerialID().toString());
		lottery.setSucessFlag(6);
		lotteryService.insert(lottery);
		return lottery;
	}
	
	/**
	 * 更新营销计划余额
	 * 
	 * @return
	 * @throws LotteryException
	 */
	private void updateMarketBalance(Marketplan marketPlan, BigDecimal money)
			throws LotteryException {
		// 更新营销计划余额
		Marketplan latestMarket = marketplanDao.get(marketPlan.getId());
		if (latestMarket.getTotalPrice().compareTo(money) < 0) {
			throw new LotteryException("营销计划金额不足", "");
		}
		marketplanDao.updateMarketBanlance(marketPlan.getId(), money.negate());

		// 如果余额达到预警值，发送短信预警
		isWarn(marketPlan);
	}
	
	/**
	 * 发送预警短信
	 * @param marketplan
	 */
	private void isWarn(Marketplan m){
		try {
			// 是否需要预警
			SysParam warn = sysParamService.getByCodes(m.getId()+"","WARN_BALANCE");
			if (warn == null || StringUtil.isEmpty(warn.getCode3())) {
				return;
			}
			BigDecimal warnBanlance = new BigDecimal(warn.getCode3());
			if (m.getTotalPrice().compareTo(warnBanlance) > 0) {
				// 未达到预警值
				return;
			}
			
			// 发送短信
			SysParam sys = sysParamService.getByCode1("BALANCE_WARN_TEXT");
			SysParam sys2 = sysParamService.getByCodes(m.getId()+"", "WARN_MOBILE");
			if (sys == null || sys2 == null) {
				return;
			}
			String text = sys.getCode3();
			String mobiles = sys2.getCode3();
			if (StringUtils.isBlank(mobiles) || StringUtils.isBlank(text)) {
				return;
			}
			
			logger.info("-------余额预警 begin-----{}", mobiles);
			SmsSendUtil.sendMessage(mobiles, text);
			logger.info("-------余额预警 end-------{}", mobiles);
			
			// 设置无效下次不再预警
			warn.setStatus("2");
			sysParamService.updateCode3(warn);
		} catch (Exception e) {
			logger.info("-------余额预警失败--------");
			e.printStackTrace();
		}
	}
	
	public static String getIpAddr(HttpServletRequest request) {
		if (request == null) {
			return "local";
		}
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	 
	/**
	 * 查询订单状态,批量查询
	 * @return
	 */
	public List<Map<String, Object>> findChannelOrderStatus(String orderId_str, String mobile,
			Marketplan marketplan, String gameId) {
		
		String[] orderIdArr = orderId_str.split(",");
		//拼参数
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("marketplanId", marketplan.getId());
		params.put("mobile", mobile);
		params.put("gameId", gameId);
		params.put("orderIds", orderIdArr);
		return userLotteryDao.findChannelOrderStatus(params);
	}
	
	/**
	 * 对账查询
	 * 
	 * @param checkType
	 * @param typeNo
	 * @param gameId
	 * @param channelAppKey
	 */
	public Map<String, Object> checkAccountOrder(Long marketId, String gameId,
			String checkType, String typeNo) {
		return userLotteryDao.checkAccountOrder(marketId, gameId, checkType,typeNo);
	}
}
