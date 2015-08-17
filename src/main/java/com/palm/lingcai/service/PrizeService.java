package com.palm.lingcai.service;

import com.palm.lingcai.entity.Prize;
import com.palm.lingcai.repository.PrizeDao;

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
public class PrizeService {

    private static Logger logger = LoggerFactory
            .getLogger(PrizeService.class);

    @Autowired
    private PrizeDao prizeDao;
    private DateProvider dateProvider = DateProvider.DEFAULT;
    /**
     * 分页查询
     *
     * @param searchParams 查询条件
     * @param pageable     分页参数
     * @return
     */
    public Page<Prize> searchPage(Map<String, Object> searchParams,
                                  Pageable pageable) {
        return prizeDao.searchPage(searchParams, pageable);
    }

    /**
     * 不分页查询
     *
     * @param searchParas 查询条件
     * @return
     */
    public List<Prize> search(Map<String, Object> searchParas) {
        return prizeDao.search(searchParas);
    }

    /**
     * 根据彩种取当前需要更新的开奖公告列表
     *
     * @param gametype
     * @return
     */
    public List<Prize> searchByWaitPrize() {
        return prizeDao.searchByWaitPrize();
    }


    public Prize get(Long id) {
        return prizeDao.get(id);
    }

    /**
     * 根据期号和彩种查询开奖信息
     *
     * @param issueNo
     * @param gameid
     * @return
     */
    public Prize getByGameidAndIssueNo(String issueNo, String gameid) {
        return prizeDao.getByGameidAndIssueNo(issueNo, gameid);
    }


    public void insert(Prize prize) {
        prize.setCreatetime(dateProvider.getDate());
        prizeDao.insert(prize);
    }

    public void update(Prize prize) {
        prizeDao.update(prize);
    }

    public void delete(Long id) {
        prizeDao.delete(id);
    }

    public void setDateProvider(DateProvider dateProvider) {
        this.dateProvider = dateProvider;
    }

    /**
     * 根据彩票期号查询中奖信息
     * @param issueNo
     * @return
     */
	public Prize getPrizeballByIssueNo(String issueNo) {
		return prizeDao.getPrizeballByIssueNo(issueNo);
	}

	/**
	 * 中奖金额
	 * @param map
	 * @return
	 */
	public BigDecimal countPrizeSumMoney(Map<String, Object> map) {
		return prizeDao.countPrizeSumMoney(map);
	}

	/**
	 * 各级中奖详情
	 * @param map
	 * @return
	 */
	public int countPrizeInfo(Map<String, Object> map) {
		return prizeDao.countPrizeInfo(map);
	}

	/**
	 * 根据订单id查询中奖信息
	 * @param orderid
	 * @return
	 */
	public Prize searchByOrderId(String orderid) {
		return prizeDao.searchByOrderId(orderid);
	}
	
	/**
	 * 通过彩种获取上期开奖号码
	 * @param gameId
	 * @return
	 */
	public Prize getLatestPrizeBall(String gameId) {
		return prizeDao.getLatestPrizeBall(gameId);
	}
}
