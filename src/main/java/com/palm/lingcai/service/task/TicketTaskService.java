package com.palm.lingcai.service.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springside.modules.cache.memcached.SpyMemcachedClient;

import com.alibaba.fastjson.JSON;
import com.palm.commom.uitl.CommonStatus;
import com.palm.commom.uitl.DateFormatUtil;
import com.palm.lingcai.entity.Lottery;
import com.palm.lingcai.entity.Prize;
import com.palm.lingcai.exception.LotteryException;
import com.palm.lingcai.service.PrizeService;
import com.palm.lingcai.service.lottery.VoteTicketService;
import com.palm.lingcai.service.lottery.VoteTicketServiceFactory;
import com.palm.lingcai.service.redis.RedisService;
import com.palm.lingcai.statuscode.LotteryNameEnum;
import com.palm.lingcai.statuscode.PrizeTimeEnum;
import com.palm.lingcai.statuscode.RedisKeyEnum;

/**
 * 彩票期次相关服务
 * @author LDL
 * 2014年12月16日
 */
@Service
public class TicketTaskService {
	private static Logger logger = LoggerFactory.getLogger(TicketTaskService.class);
	@Autowired
	private RedisService redisService;
	private VoteTicketService voteTicketService;
	@Autowired
	private VoteTicketServiceFactory voteTicketServiceFactory;
	@Autowired
	private SpyMemcachedClient memcachedClient;
	@Autowired
	private PrizeService prizeService;
	private static final String resetKey = "reset_issue_time_";//重置缓存期次的时间
	/**
	 * DLT初始化新期7:30
	 * 每周1、3、6开奖
	 * @throws ParseException
	 * 
	 */
    public void initIssueNo_DLT() {
    	String gameId = "DLT";
		try {
			int weekday = DateFormatUtil.getWeekOfDate(new Date());
			String prizeDay = PrizeTimeEnum.getPrizeDay(gameId);
			if (!prizeDay.contains(weekday + "")) {
				// 不是开奖日
				return;
			}
			Lottery lottery = initIssue(gameId);
			redisService.set(RedisKeyEnum.DLT_ISSUE_KEY.getKey(), JSON.toJSONString(lottery), -1);
		} catch (LotteryException e) {
			e.printStackTrace();
		}
    }
	/**
	 * QXC初始化新期7:30
	 * 每周2、5、7开奖
	 * @throws ParseException
	 * 
	 */
    public void initIssueNo_QXC() {
		try {
			int weekday = DateFormatUtil.getWeekOfDate(new Date());
			String prizeDay = PrizeTimeEnum.QXC_PRIZEDAY.code;
			if (!prizeDay.contains(weekday + "")) {
				// 不是开奖日
				return;
			}
			Lottery lottery = initIssue("QXC");
			redisService.set(RedisKeyEnum.QXC_ISSUE_KEY.getKey(),JSON.toJSONString(lottery), -1);
		} catch (LotteryException e) {
			e.printStackTrace();
		}
    }
	/**
	 * 3D初始化新期7:30
	 * 每日开奖
	 * @throws ParseException
	 * 
	 */
    public void initIssueNo_3D() {
		try {
			String gameId = "3D";
			Lottery lottery = initIssue(gameId);
			redisService.set(RedisKeyEnum.D3_ISSUE_KEY.getKey(),JSON.toJSONString(lottery), -1);
		} catch (LotteryException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 从缓存获取当前期次(DLT、QXC、3D、SSQ)
     * @param gameId
     * @return
     * @throws LotteryException
     */
    public Lottery getCurrentIssue(String gameId) throws LotteryException{
    	String key = "";
		if ("DLT".equals(gameId)) {
			key = RedisKeyEnum.DLT_ISSUE_KEY.getKey();
		}
		if ("QXC".equals(gameId)) {
			key = RedisKeyEnum.DLT_ISSUE_KEY.getKey();
		}
		if ("3D".equals(gameId)) {
			key = RedisKeyEnum.DLT_ISSUE_KEY.getKey();
		}
		if ("SSQ".equals(gameId)) {
			//双色球走老程序
			Lottery lot = memcachedClient.get("getMemcacheLottery");
			if (lot == null) {
				lot = this.initIssue(gameId);
				memcachedClient.safeSet("getMemcacheLottery",60 * 60 * 24 * 30, lot);
			}
			return lot;
		}
		String lotstr = redisService.get(key);
		if (StringUtils.isEmpty(lotstr)) {
			Lottery lottery = this.initIssue(gameId);
			redisService.set(key, JSON.toJSONString(lottery), -1);
			return lottery;
		}
    	
		// 如果是开奖日,七点半之后期次缓存的时间依然小于七点半,可能定时器未执行，需要重置期次缓存
		if (needResetIssue(gameId, key)) {
			Lottery lottery = this.initIssue(gameId);
			redisService.set(key, JSON.toJSONString(lottery), -1);
			return lottery;
		}
		
    	Lottery lot = JSON.parseObject(lotstr, Lottery.class);
    	return lot;
    }
    
    
    /**
     * 是否需要重新加载缓存期次
     * （每天重置，如果开奖日7点半未加载缓存，会重新获取期次）
     * @param gameId
     * @param key
     * @return
     * @throws LotteryException
     */
	private boolean needResetIssue(String gameId, String key) {
		// 上次重置缓存的时间
		String resetTimeStr = redisService.get(resetKey + gameId);
		if(StringUtils.isBlank(resetTimeStr)){
			return true;
		}
		Date resetDate = DateFormatUtil.toDate(resetTimeStr, "yyyyMMddHHmmss");
		Date now = new Date();
		
		// 1、上次重置时间是否是今天,不是则重新加载缓存
		SimpleDateFormat day_f = new SimpleDateFormat("yyyy-MM-dd");
		if (!day_f.format(now).equals(day_f.format(resetDate))) {
			return true;
		}
		
		// 2、判断当前时间段19:3000-23:235959,不是则重新加载缓存
		String str193000 = DateFormatUtil.dateToString(now, "yyyyMMdd193000");
		Date today1930 = DateFormatUtil.toDate(str193000, "yyyyMMddHHmmss");
		
		// 3、开奖日&&缓存时间7点半之前
		String prizeDay = PrizeTimeEnum.getPrizeDay(gameId);
		String weekday = DateFormatUtil.getWeekOfDate(now) + "";
		if (prizeDay.contains(weekday)) {
			//在当前时间过了7点半，期次缓存未重置过的需要重新重置
			if (now.getTime() >= today1930.getTime() && resetDate.getTime() < today1930.getTime()) {
				return true;
			}
		}
		return false;
	}
    
	/**
	 * 获取当前期或下一期(体彩、福彩)
	 * 
	 * @param gameid
	 * @return
	 * @throws LotteryException
	 */
    public Lottery initIssue(String gameid) throws LotteryException{
    	
		logger.info("--------初始化期次缓存---initIssue,gameid=" + gameid);
    	int gameType = LotteryNameEnum.getGameType(gameid);

		// 请求全网平台获取期次
		voteTicketService = voteTicketServiceFactory.getInstance(gameType);
		Lottery lottery = voteTicketService.getIssueNo(gameid);
		lottery.setGametype(gameType);
		logger.info("issueno lottery:{}", JSON.toJSONString(lottery));
		
		//替换时分秒：20141227195700>20141227193000
		String palmtime = lottery.getPalmtime();
		palmtime = palmtime.replace("195700", "193000");// 福彩截期时间
		palmtime = palmtime.replace("195800", "193000");// 体彩截期时间
		Long lingcaiTime = DateFormatUtil.getLongFormatDate("yyyyMMddHHmmss", palmtime);
		
		// 当前时间大于等于7:30时,取下一期次
		Date nowTime = new Date();
		if (nowTime.getTime() >= lingcaiTime) {
			lottery.setStarttime(getNextTime(gameid, lottery.getStarttime()));
			lottery.setEndtime(getNextTime(gameid, lottery.getEndtime()));
			lottery.setPrizetime(getNextTime(gameid, lottery.getPrizetime()));
			lottery.setPalmtime(getNextTime(gameid, lottery.getPalmtime()));
			// 下一期期次
			String nextIssue = getNextIssue(lottery);
			lottery.setIssueNo(nextIssue);
		}
		
		/**
		 * 插入待开奖的彩种的期次
		 */
		Prize prize = prizeService.getByGameidAndIssueNo(lottery.getIssueNo(), lottery.getGameid());
		if (null == prize) {
			prize = new Prize();
			prize.setGameid(lottery.getGameid());
			prize.setGametype(lottery.getGametype());
			prize.setIssueNo(lottery.getIssueNo());
			prize.setPrizetime(lottery.getPrizetime());
			prize.setPrizeStatus(CommonStatus.PRIZE_STATUS_UNOPEN);
			prizeService.insert(prize);
		}
		
		// 记录当前期次到加载缓存的时间
		String timeFlag = DateFormatUtil.dateToString(nowTime, "yyyyMMddHHmmss");
		redisService.set((resetKey + gameid), timeFlag);
		return lottery;
    }

    /**
     * 下一期期次，判断夸年情况
     * @param lottery
     * @return
     */
	private String getNextIssue(Lottery lottery) {
		int gameType = lottery.getGametype();
		//判断是否跨年
		int currYear = Calendar.getInstance().get(Calendar.YEAR);
		String strPrizeYear = lottery.getPrizetime().substring(0,4);
		int prizeYear = Integer.parseInt(strPrizeYear);
		
		if (currYear < prizeYear) {
			if (gameType == 1) {
				//体彩第一期：15001
				String year = prizeYear + "001";
				return year.substring(2);
			} else {
				//福彩第一期：2015001
				return prizeYear + "001";
			}
		} else {
			// 期次+1
			Long nextIssue = Long.parseLong(lottery.getIssueNo()) + 1;
			return nextIssue.toString();
		}
	}
	
	/**
	 * 计算下一天的开奖时间
	 *  SSQ 每周2、4、7开奖
	 *	DLT 每周1、3、6开奖
	 *	QXC 每周2、5、7开奖
	 *	3D 	每日开奖
	 * @return
	 */
	private String getNextTime(String gameId, String prizetime) {
		Long ltime = DateFormatUtil.getLongFormatDate("yyyyMMddHHmmss", prizetime);
		Long result = ltime;

		int week = DateFormatUtil.getWeekOfDate(new Date(ltime));
		if ("SSQ".equals(gameId)) {
			switch (week) {
			case 2:
				result = ltime + 1000 * 60 * 60 * 24 * 2;
				break;
			case 4:
				result = ltime + 1000 * 60 * 60 * 24 * 3;
				break;
			case 7:
				result = ltime + 1000 * 60 * 60 * 24 * 2;
				break;
			}
		} else if ("DLT".equals(gameId)) {
			switch (week) {
			case 1:
				result = ltime + 1000 * 60 * 60 * 24 * 2;
				break;
			case 3:
				result = ltime + 1000 * 60 * 60 * 24 * 3;
				break;
			case 6:
				result = ltime + 1000 * 60 * 60 * 24 * 2;
				break;
			}
		} else if ("QXC".equals(gameId)) {
			switch (week) {
			case 2:
				result = ltime + 1000 * 60 * 60 * 24 * 3;
				break;
			case 5:
				result = ltime + 1000 * 60 * 60 * 24 * 2;
				break;
			case 7:
				result = ltime + 1000 * 60 * 60 * 24 * 2;
				break;
			}
		} else if ("3D".equals(gameId)) {
			result = ltime + 1000 * 60 * 60 * 24 * 1;
		}
		
		return DateFormatUtil.getFormatStr("yyyyMMddHHmmss", result);
	}
	
    /**
	 * 上期开奖号码(DLT、SSQ、QXC、3D)
	 */
	public void initPrizeBall() {
		for (String gameId : LotteryNameEnum.gameids) {
			Prize prize = prizeService.getLatestPrizeBall(gameId);
			if (prize != null) {
				String key = gameId + RedisKeyEnum._PREVIOUS_PRIZE_BALL.getKey();
				redisService.set(key, "{\"ballNo\":\""+prize.getPrizeball()+"\",\"issueNo\":\""+prize.getIssueNo()+"\",\"gameId\":\""+prize.getGameid()+"\"}");
			}
		}
	}
	
}
