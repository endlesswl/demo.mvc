package com.palm.lingcai.repository;

import com.palm.lingcai.entity.UserPrize;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository("userPrizeDao")
public interface UserPrizeDao {

    UserPrize getByUser(@Param("userid") Long userid, @Param("id") Long id);

    UserPrize get(Long id);

    UserPrize getByUserLottery(@Param("userid") Long userid, @Param("lotteryid") Long lotteryid, @Param("gameid") String gameid, @Param("issueNo") String issueNo);

    List<UserPrize> search(Map<String, Object> parameters);

    List<UserPrize> searchUser(Map<String, Object> parameters);

    Page<UserPrize> searchPage(@Param("searchFields") Map<String, Object> searchParams, Pageable pageRequest);
    
    Page<Map<String, Object>> searchUserTotalPrizePage(@Param("searchFields") Map<String, Object> searchParams, Pageable pageRequest);

    BigDecimal sumMoney(@Param("userid") Long userid);
    
    BigDecimal getUserLotteryPrize(@Param("userid") Long userid, @Param("lotteryid") Long lotteryid);

    void insert(UserPrize userPrize);

    void delete(Long id);

    void update(UserPrize userPrize);

    /**
     * 更新兑奖状态
     *
     * @param userPrize
     */
    void updateIsFixed(UserPrize userPrize);

    /**
     * 获取中奖记录
     * @param searchParams
     * @param pageable
     * @return
     */
	Page<UserPrize> searchUserPrizePage(@Param("searchFields") Map<String, Object> searchParams, Pageable pageable);
	
	/**
	 * 根据user_Lottery_Id查询
	 * @param userLotteryId
	 * @return
	 */
	UserPrize findByUserLotId(@Param("userLotteryId") Long userLotteryId);
	
	
	//===================lingcai sdk ===============
	/**
	 * 查询中奖信息
	 * @param marketId
	 * @param orderId
	 * @param gameid
	 * @param issueNo
	 * @return
	 */
	Map<String, Object> getWinPrizeInfo(@Param("marketId")Long marketId,
			@Param("channelOrder")String channelOrder,
			@Param("gameid")String gameid, 
			@Param("issueNo")String issueNo);
	/**
	 * 查询用户所有中奖信息
	 * @param username
	 * @return
	 */
	List<UserPrize> findByUserName(@Param("username")String username);

}
