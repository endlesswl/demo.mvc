package com.palm.lingcai.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.DateProvider;

import com.palm.lingcai.entity.Balance;
import com.palm.lingcai.repository.BalanceDao;

/**
* @Title: 
* @Description: 
* @Author jhe   
* @Date 2013 - 2014
* @Version V1.0
* @Copyright © 2013 掌信彩通信息科技(中国)有限公司. All rights reserved.
*/
// Spring Service Bean的标识.
@Service
@Transactional
public class BalanceService {

	private static Logger logger = LoggerFactory
			.getLogger(BalanceService.class);

	@Autowired
	private BalanceDao balanceDao;

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
	public Page<Balance> searchPage(Map<String, Object> searchParams,
			Pageable pageable) {
		return balanceDao.searchPage(searchParams, pageable);
	}

	/**
	 * 不分页查询
	 * 
	 * @param searchParas
	 *            查询条件
	 * @return
	 */
	public List<Balance> search(Map<String, Object> searchParas) {
		return balanceDao.search(searchParas);
	}

	
	public Balance get(Long id) {
		return balanceDao.get(id);
	}

	public void insert(Balance balance) {
		balanceDao.insert(balance);
	}
	
	public void update(Balance balance) {
		balanceDao.update(balance);
	}

	public void delete(Long id) {
		balanceDao.delete(id);
	}

	public void setDateProvider(DateProvider dateProvider) {
		this.dateProvider = dateProvider;
	}

	public Balance findBalanceByUserIdAndServerId(Long userid, Long serverId) {
		return balanceDao.findBalanceByUserIdAndServerId(userid,serverId);
	}

	public void updateBalance(Balance balance) {
		balanceDao.updateBalance(balance);
	}

	/**
	 * 提现统计
	 * @param marketid
	 * @return
	 */
	public int withdrawCount(String marketid) {
		return balanceDao.withdrawCount(marketid);
	}

	/**
	 * 未提现统计
	 * @param marketid
	 * @return
	 */
	public int noWithdrawCount(String marketid) {
		return balanceDao.noWithdrawCount(marketid);
	}
}
