package com.palm.lingcai.repository;

import com.palm.lingcai.entity.UserLottery;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("userLotteryDao")
public interface UserLotteryDao {
	
	UserLottery get(Long id);
	
	UserLottery getByUserAndLottery(@Param("userid") Long userid, @Param("lotteryid") Long lotteryid);
	
	List<UserLottery> search(Map<String, Object> parameters);
	
	Page<UserLottery> searchPage(@Param("searchFields") Map<String, Object> searchParams, Pageable pageable);
	
	List<UserLottery> searchPageByRecharge(@Param("searchFields") Map<String, Object> searchParams);
	
	Page<UserLottery> searchPageByRechargePage(@Param("searchFields") Map<String, Object> searchParams, Pageable pageable);
	
	void insert(UserLottery userLottery);
	
	void delete(Long id);
	
	void update(UserLottery userLottery);
	
	UserLottery findByMobile(@Param("marketid") Long marketid, @Param("mobile") String mobile);
	
	List<UserLottery> findByOrderid(@Param("orderid") String orderid);
	
	Integer countByUser(@Param("userid") Long userid);
	
	List<UserLottery> countUserForMarketid(@Param("userid") Long userid);
	

	//============================lingcai sdk =========================
	/***
	 * 检查同一个marketplan中订单号唯一
	 * @param marketid
	 * @param orderid
	 * @return
	 */
	UserLottery findOnlyChannelOrder(@Param("marketId")Long marketId,@Param("channelOrder") String channelOrder);
	
	/**
	 * 出票成功订单推送
	 * @param successFlag
	 * @param notifyStatus
	 * @param quantity
	 * @return
	 */
	List<Map<String, Object>> searchNotifyOrder(
			@Param("successFlag") String successFlag,
			@Param("notifyTimes") int notifyTimes,
			@Param("quantity") int quantity);

	/**
	 * 查询推送中奖订单记录
	 * @param notifyWinStatus
	 * @param notifyWinTimes
	 * @param isPrize
	 * @param quantity
	 * @return
	 */
	List<Map<String, Object>> notifyWin(
			@Param("notifyWinTimes") int notifyWinTimes,
			@Param("quantity") int quantity);
	/**
	 * 更新推送状态
	 * @param id
	 * @param notifyStatus
	 */
	void updateNotifyStatus(@Param("id") Long id,@Param("notifyStatus") String notifyStatus);
	
	/**
	 * 更新推送状态
	 * @param id
	 * @param notifyStatus
	 */
	void updateWinStatus(@Param("id") Long id,@Param("winStatus") String winStatus);
	
	/**
	 * 订单状态查询
	 * @param params
	 * @return "orderid","sucessFlag"
	 */
	List<Map<String, Object>> findChannelOrderStatus(Map<String, Object> params);
	
	/**
	 * 对账统计 
	 * @param channelAppKey
	 * @param gameId
	 * @param checkType
	 * @param typeNo
	 * @return map_key:countTotal,moneyTotal,game_id,issue_no
	 */
	Map<String,Object> checkAccountOrder(@Param("marketid") Long marketid,@Param("gameId") String gameId,@Param("checkType") String checkType, @Param("typeNo") String typeNo);

}
