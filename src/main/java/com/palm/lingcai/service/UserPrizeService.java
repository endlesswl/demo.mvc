package com.palm.lingcai.service;

import com.palm.lingcai.entity.UserPrize;
import com.palm.lingcai.repository.UserPrizeDao;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.DateProvider;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Title:
 * @Description:
 * @Author jhe
 * @Date 2013 - 2013
 * @Version V1.0
 * @Copyright © 2013 掌信彩通信息科技(中国)有限公司. All rights reserved.
 */
// Spring Service Bean的标识.
@Service
@Transactional
public class UserPrizeService {
	private static Logger logger = LoggerFactory
			.getLogger(UserPrizeService.class);

	@Autowired
	private UserPrizeDao userPrizeDao;

	private DateProvider dateProvider = DateProvider.DEFAULT;

	/**
	 * 分页查询
	 * 
	 * @param searchParams
	 *            查询条件
	 * @param pageable
	 *            分页参数
	 * @return
	 */
	public Page<UserPrize> searchPage(Map<String, Object> searchParams,
			Pageable pageable) {
		return userPrizeDao.searchPage(searchParams, pageable);
	}

	/**
	 * 统计中奖总金额
	 * 
	 * @param userid
	 * @return
	 */
	public BigDecimal sumMoney(Long userid) {
		return userPrizeDao.sumMoney(userid);
	}

	/**
	 * 不分页查询
	 * 
	 * @param searchParas
	 *            查询条件
	 * @return
	 */
	public List<UserPrize> search(Map<String, Object> searchParas) {
		return userPrizeDao.search(searchParas);
	}

	/**
	 * 查询中奖用户明细信息
	 * 
	 * @param searchParas
	 * @return
	 */
	public List<UserPrize> searchUser(Map<String, Object> searchParas) {
		return userPrizeDao.searchUser(searchParas);
	}

	public UserPrize getByUser(Long userid, Long id) {
		return userPrizeDao.getByUser(userid, id);
	}

	public UserPrize get(Long id) {
		return userPrizeDao.get(id);
	}

	/**
	 * 获取用户中奖信息
	 * 
	 * @param userid
	 * @param lotteryid
	 * @param gameid
	 * @param issueNo
	 * @return
	 */
	public UserPrize getByUserLottery(Long userid, Long lotteryid,
			String gameid, String issueNo) {
		return userPrizeDao
				.getByUserLottery(userid, lotteryid, gameid, issueNo);
	}

	public void insert(UserPrize userPrize) {
		userPrize.setCreatetime(dateProvider.getDate());
		userPrizeDao.insert(userPrize);
	}

	public void update(UserPrize userPrize) {
		userPrizeDao.update(userPrize);
	}

	public void delete(Long id) {
		userPrizeDao.delete(id);
	}

	public void setDateProvider(DateProvider dateProvider) {
		this.dateProvider = dateProvider;
	}

	/**
	 * 获取中奖记录
	 * 
	 * @param searchParams
	 * @param pageable
	 * @return
	 */
	public Page<UserPrize> searchUserPrizePage(
			Map<String, Object> searchParams, Pageable pageable) {
		return userPrizeDao.searchUserPrizePage(searchParams, pageable);
	}

	/**
	 * 用户累计中奖记录(分页)
	 * 
	 * @param searchParams
	 * @param pageable
	 * @return
	 */
	public Page<Map<String, Object>> searchUserTotalPrizePage(
			Map<String, Object> searchParams, Pageable pageable) {
		return userPrizeDao.searchUserTotalPrizePage(searchParams, pageable);
	}
	/**
	 * 根据渠道订单查询中奖信息
	 * @param marketId
	 * @param channelOrder
	 * @param gameid
	 * @param issueNo
	 * @return
	 */
	public Map<String, Object> getWinPrizeInfo(Long marketId,
			String channelOrder, String gameid, String issueNo) {
		return userPrizeDao.getWinPrizeInfo(marketId, channelOrder, gameid,issueNo);
	}
	
	/**
	 * 查询用户中奖信息
	 * @param username
	 * @return
	 */
	public List<UserPrize> findByUserName(String username){
		return userPrizeDao.findByUserName(username);
	}

}
