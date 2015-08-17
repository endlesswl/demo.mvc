package com.palm.lingcai.repository;

import com.palm.lingcai.entity.LotterySplit;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("lotterySplitDao")
public interface LotterySplitDao {

    LotterySplit get(Long id);

    List<LotterySplit> search(Map<String, Object> parameters);

    Page<LotterySplit> searchPage(@Param("searchFields") Map<String, Object> searchParams, Pageable pageRequest);

    void insert(LotterySplit lotterySplit);

    void delete(Long id);

    void update(LotterySplit lotterySplit);

    LotterySplit getByLotteryId(Long lotteryid);

    int getCountByUserid(@Param("userid") String userid, @Param("marketid") String marketid, @Param("isTuhao") Integer isTuhao);

    /**
     * 分页查询送出彩票
     *
     * @param searchParams
     * @param pageable
     * @return
     */
    Page<LotterySplit> searchSendLotterySplit(@Param("searchFields") Map<String, Object> searchParams, Pageable pageable);

    /**
     * 分页查询领取彩票
     *
     * @param searchParams
     * @param pageable
     * @return
     */
    Page<LotterySplit> searchReceiveLottery(@Param("searchFields") Map<String, Object> searchParams,
                                            Pageable pageable);

    /**
     * 查询乐享榜
     *
     * @param userid
     * @return
     */
    List<LotterySplit> searchLenJoyCount(@Param("userid") Long userid);

    /**
     * 根据用户id查询彩票
     *
     * @param userid
     * @return
     */
    List<LotterySplit> searchLotterySplit(@Param("userid") Long userid);

    /**
     * 查询收到的土豪
     *
     * @param userid
     * @return
     */
    List<LotterySplit> searchReceiveTuhao(@Param("userid") Long userid, @Param("marketid") String marketid);

    /**
     * 查询送彩土豪榜
     *
     * @param searchParams
     * @param pageable
     * @return
     */
    Page<LotterySplit> searchReceiveByUseridAndReceivetime(@Param("searchFields") Map<String, Object> searchParams, Pageable pageable);

    /**
     * 当日领取出票成功人数
     *
     * @param marketid
     * @param today
     * @return
     */
    Integer countByTodayTicketCount(@Param("marketid") String marketid, @Param("today") String today);

    /**
     * TODO:一次性方法
     *
     * @return
     */
    List<LotterySplit> findErrorSplit();

    /**
     * 查询lotteryids字段里是否包含
     *
     * @param lotteryid
     * @return
     */
    LotterySplit getByLotteryids(@Param("lotteryid") String lotteryid);

    /**
     * 取得指定用户未分配完成的数据
     *
     * @param lotteryid
     * @return
     */
    LotterySplit getNotOver(@Param("userid") Long userid, @Param("marketid") Long marketid);

    /**
     * 查询所有土豪订单
     * 如果已出票，则会更新updatetime字段，下次将不在校验
     *
     * @return
     */
    List<LotterySplit> searchTuhao(@Param("palmtime") String palmtime);

    /**
     * 查询每日人气排行
     *
     * @param searchParams
     * @return
     */
    List<LotterySplit> searchTop10(@Param("searchFields") Map<String, Object> searchParams);
}
