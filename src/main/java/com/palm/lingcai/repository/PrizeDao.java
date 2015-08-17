package com.palm.lingcai.repository;

import com.palm.lingcai.entity.Prize;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository("prizeDao")
public interface PrizeDao {

    Prize get(Long id);

    Prize getByGameidAndIssueNo(@Param("issueNo") String issueNo, @Param("gameid") String gameid);

    List<Prize> search(Map<String, Object> parameters);

    List<Prize> searchByWaitPrize();

    Page<Prize> searchPage(@Param("searchFields") Map<String, Object> searchParams, Pageable pageRequest);

    void insert(Prize prize);

    void delete(Long id);

    void update(Prize prize);

    /**
     * @param issueNo
     * @return
     */
	Prize getPrizeballByIssueNo(@Param("issueNo") String issueNo);

	/**
	 * 中奖金额
	 * @param map
	 * @return
	 */
	BigDecimal countPrizeSumMoney(Map<String, Object> map);

	/**
	 * 各级中奖详情
	 * @param map
	 * @return
	 */
	int countPrizeInfo(Map<String, Object> map);

	/**
	 * 根据订单查询中奖详情
	 * @param orderid
	 * @return
	 */
	Prize searchByOrderId(@Param("orderid") String orderid);

	List<Prize> findListPrize(@Param("gametype") int gametype);
	
	/**
	 * 通过彩种获取上期开奖号码
	 * @param gameId
	 * @return
	 */
	Prize getLatestPrizeBall(@Param("gameId") String gameId);

}
