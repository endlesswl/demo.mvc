package com.palm.lingcai.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.cache.memcached.SpyMemcachedClient;

import com.alibaba.fastjson.JSON;
import com.palm.lingcai.entity.Lottery;
import com.palm.lingcai.exception.LotteryException;
import com.palm.lingcai.service.PrizeService;
import com.palm.lingcai.service.redis.RedisService;
import com.palm.lingcai.service.task.NotifyWinTaskService;
import com.palm.lingcai.service.task.TicketTaskService;
import com.palm.lingcai.service.task.WithdrawTask;
import com.palm.lingcai.statuscode.RedisKeyEnum;

/**
 * root
 * 
 * @author LDL
 * 
 */
@Controller
@RequestMapping(value = "/admin/root")
public class RootController {
	@Autowired
	private NotifyWinTaskService notifyWinTaskService;
    @Autowired
    private	SpyMemcachedClient memcachedClient;
    @Autowired
    private	PrizeService prizeService;
    @Autowired
    private WithdrawTask withdrawTask;
    @Autowired
    private TicketTaskService ticketTaskService;
	@Autowired
	private RedisService redisService;
	
    /**
	 * 获取期次
	 * @return
	 * @throws LotteryException 
	 */
	@ResponseBody
	@RequestMapping(value = "/getCurrentIssue")
	public String getCurrentIssue(String gameId) throws LotteryException {
		Lottery lot = ticketTaskService.getCurrentIssue(gameId);
		return lot.getIssueNo();
	}
	
    /**
     * 手动推送中奖信息
     * @return
     */
	@ResponseBody
	@RequestMapping(value = "/notifyWin")
	public String notifyWin() {
		notifyWinTaskService.notifyWinByInssueNo();
		return "ok";
	}
	
	/**
	 * 手动触发提现文件上传
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadPayFile")
	public String uploadPayFile(){
		withdrawTask.uploadPayFile();
		return "ok";
	}
	
	/**
	 * 手动触发初始化上期开奖球号
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/initPrizeBall")
	public String initPrizeBall(){
		ticketTaskService.initPrizeBall();
		return "ok";
	}
	
	/**
	 * 加载最新期次（测试用）
	 * @return
	 * @throws LotteryException 
	 */
	@ResponseBody
	@RequestMapping(value = "/initIssue")
	public String initIssue(String gameId) throws LotteryException {
		Lottery lottery = ticketTaskService.initIssue(gameId);
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
			// 双色球走老程序
			memcachedClient.safeSet("getMemcacheLottery",60 * 60 * 24 * 30, lottery);
			return lottery.getIssueNo();
		}
		redisService.set(key, JSON.toJSONString(lottery), -1);
		return lottery.getIssueNo();
	}
	
}
