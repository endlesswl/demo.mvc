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

import com.palm.commom.uitl.DateProvider;
import com.palm.lingcai.entity.MarketRotateRule;
import com.palm.lingcai.repository.MarketRotateRuleDao;


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
public class MarketRotateRuleService {

	private static Logger logger = LoggerFactory
			.getLogger(MarketRotateRuleService.class);

	@Autowired
	private MarketRotateRuleDao marketRotateRuleDao;

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
	public Page<MarketRotateRule> searchPage(Map<String, Object> searchParams,
			Pageable pageable) {
		return marketRotateRuleDao.searchPage(searchParams, pageable);
	}

	/**
	 * 不分页查询
	 * 
	 * @param searchParas
	 *            查询条件
	 * @return
	 */
	public List<MarketRotateRule> search(Map<String, Object> searchParas) {
		return marketRotateRuleDao.search(searchParas);
	}

	
	public MarketRotateRule get(String id) {
		return marketRotateRuleDao.get(id);
	}
	
	public MarketRotateRule getByName(String rotateName) {
		return marketRotateRuleDao.getByName(rotateName);
	}

	public void insert(MarketRotateRule marketRotateRule) {
		marketRotateRule.setCreateTime(dateProvider.getDate());
		marketRotateRuleDao.insert(marketRotateRule);
	}
	
	public void update(MarketRotateRule marketRotateRule) {
		marketRotateRule.setModifyTime(dateProvider.getDate());
		marketRotateRuleDao.update(marketRotateRule);
	}

	public void delete(String id) {
		marketRotateRuleDao.delete(id);
	}

	public void setDateProvider(DateProvider dateProvider) {
		this.dateProvider = dateProvider;
	}
}
