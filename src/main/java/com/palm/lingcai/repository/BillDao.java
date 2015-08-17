package com.palm.lingcai.repository;

import com.palm.lingcai.entity.Bill;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("billDao")
public interface BillDao {
    Bill get(Long id);

    Bill getByOrderid(Long orderid);

    Bill getWithdrawBill(Map<String, Object> parameters);

    Bill getOneByOrderidAndIndecr(@Param("orderid") Long orderid, @Param("indecr") int indecr);

    List<Bill> search(Map<String, Object> parameters);

    Page<Bill> searchPage(@Param("searchFields") Map<String, Object> searchParams, Pageable pageRequest);

    void insert(Bill bill);

    void delete(Long id);

    void update(Bill bill);

    /**
     * 账户的交易明细
     *
     * @param searchParams
     * @param pageable
     * @return
     */
    Page<Bill> searchAccountPage(@Param("searchFields") Map<String, Object> searchParams,
                                 Pageable pageable);

    void deleteByMarketplanId(@Param("orderId") Long orderId);

    /**
     * 根据交易渠道和具体方式、交易订单号、用户id查询交易记录
     *
     * @param ordinal
     * @param method
     * @param orderid
     * @param userid
     * @return
     */
    Bill findBillByChannleAndMethodAndOrderIdAndUserId(
            @Param("billchannel") Integer billchannel,
            @Param("method") Integer method, @Param("orderid") Long orderid, @Param("userid") Long userid);

}
