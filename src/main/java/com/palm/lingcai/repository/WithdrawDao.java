package com.palm.lingcai.repository;

import com.palm.lingcai.entity.Withdraw;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository("withdrawDao")
public interface WithdrawDao {

	Withdraw get(Long id);

	List<Withdraw> search(Map<String, Object> parameters);

	Page<Withdraw> searchPage(@Param("searchFields") Map<String, Object> searchParams, Pageable pageRequest);

	void insert(Withdraw withdraw);

	void delete(Long id);

	void update(Withdraw withdraw);

	List<Withdraw> searchByIds(@Param("idStr") String idStr);

    Map<String,Object> statisWithdraw(@Param("startDate") String startDate, @Param("endDate") String endDate);

	Withdraw findWithdrawByUserIdAndId(@Param("userId")Long userId,@Param("id") Long id);
	
	Page<Withdraw> searchPageAndServerId(@Param("searchFields") Map<String, Object> searchParams,Pageable pageable);

	List<Withdraw> findwithdrawList(Map<String, Object> searchParams);

	Withdraw findMinShengWithdrawByUserIdAndBankIdAndServerId(@Param("userid") Long userid,
			@Param("amount")	BigDecimal amount,@Param("bankid") Long bankid,
			@Param("wxserverid") long wxserverid, @Param("status")int status);

	Withdraw findWithdrawByOrderId(@Param("orderid") String orderId);
	
	/**
	 * 查询用户所有提现信息
	 * @param username
	 * @return
	 */
	List<Withdraw> findByUserName(@Param("username")String username);
	
	/**
	 * 提现状态
	 * @param params
	 * @return
	 */
	List<Withdraw> findWithdrawStatus(Map<String, Object> params);
	
	/**
	 * 查询待转账的,金额大于1元的记录
	 * @param status
	 * @param limitNum
	 * @return
	 */
	List<Withdraw> findWithdraws(@Param("status")String status,@Param("limitNum")Integer limitNum);
	
	void updateWithdrawByIds(@Param("ids") String ids,@Param("status") Integer status,@Param("mesg")String mesg);

	/**
	 * 根据渠道流水号查询
	 * @param serialNo
	 * @return
	 */
	List<Withdraw> findBySerialNo(@Param("marketId") Long marketId,@Param("serialNo") String serialNo);	
	
}
