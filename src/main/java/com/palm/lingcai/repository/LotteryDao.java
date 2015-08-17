package com.palm.lingcai.repository;

import com.palm.lingcai.entity.Lottery;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("lotteryDao")
public interface LotteryDao {

    Lottery get(Long id);

    List<Lottery> search(Map<String, Object> parameters);

    Page<Lottery> searchPage(@Param("searchFields") Map<String, Object> searchParams, Pageable pageRequest);

    Page<Lottery> searchPageByUser(@Param("searchFields") Map<String, Object> searchParams, Pageable pageRequest);

    Page<Lottery> searchPrizeList(@Param("searchFields") Map<String, Object> searchParams, Pageable pageRequest);

    /**
     * 按条件统计
     *
     * @param parameters
     * @return
     */
    int count(Map<String, Object> parameters);

    /**
     * 批量更新中奖状态
     *
     * @param lottery
     */
    void updateIsPrize(Lottery lottery);

    void insert(Lottery lottery);

    void delete(Long id);

    void update(Lottery lottery);

    /**
     * 更新期号
     *
     * @param lottery
     */
    void updateIssueNo(Lottery lottery);

    void updateRemainder(@Param("id") Long id);

    /**
     * 更新出票状态
     *
     * @param sucessFlag
     * @param id
     */
    void updateTicket(@Param("sucessFlag") Integer sucessFlag, @Param("id") Long id);

    void updateForOrder(Lottery lottery);

    Lottery findSingleLottery(@Param("gameid") String gameid, @Param("unitprice") String unitprice, @Param("currentTime") String currentTime);

    Lottery findSingleLotteryByPercent(@Param("userid") String userid, @Param("marketid") String marketid);

    Map<String, Object> statisLottery(@Param("startDate") String startDate, @Param("endDate") String endDate);

    List<Lottery> getByLotterySplitid(@Param("lotterySplitid") Long lotterySplitid);

    List<Lottery> findNotInLottery();

    /**
     * 查询符合出票规则的彩票
     *
     * @return
     */
    List<Lottery> findCheckOutLottery(@Param("endDate") String endDate);

    /**
     * 查询符合出票规则的彩票
     *
     * @return
     */
    List<Lottery> findCheckOutLotteryCompare();

    /**
     * 统计彩票出票情况
     *
     * @param map
     * @return
     */
    int countLottery(Map<String, Object> map);

    /**
     * 根据用户领彩与实际应该出票总数相对比，如果返回记录不为空则代表彩票未领取完毕
     *
     * @param id
     * @return
     */
    Lottery countByUserLottery(@Param("id") Long id);

    /**
     * 查询土豪榜或没有拆分的彩票的待出票数据
     *
     * @param lotteryids
     * @return
     */
    List<Lottery> searchTuhaoByLotteryids(@Param("lotteryids") String lotteryids);

    /**
     * 查询世界杯待待兑奖的彩票
     *
     * @param isprize
     * @param gametype
     * @return
     */
    List<Lottery> searchByWCWaitPrize(@Param("gametype") int gametype, @Param("isprize") int isprize, @Param("item") int item);

    /**
     * 查询世界杯出错数据待出票的定单
     * 主要是由于初始化票错误引起的数据错乱，一张票分了N个人
     *
     * @return
     */
    List<Lottery> searchByWrongTicket();

    /**
     * 查询世界杯应该出票但是因为各种情况未出票的定单
     *
     * @return
     */
    List<Lottery> searchByUnTicket();

	Lottery findLotteryByOrderId(@Param("orderid") String orderid);
}
