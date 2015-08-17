package com.palm.lingcai.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.palm.lingcai.entity.Balance;

@Repository("balanceDao")
public interface BalanceDao {

	Balance get(Long id);

	List<Balance> search(Map<String, Object> parameters);

	Page<Balance> searchPage(@Param("searchFields") Map<String, Object> searchParams, Pageable pageRequest);

	void insert(Balance balance);

	void delete(Long id);

	void update(Balance balance);
	
	List<Balance> findBalancesByUserId(@Param("userid") Long userid);

	Balance findBalanceByUserIdAndServerId(@Param("userid")Long userid,@Param("wxserverid") Long serverId);

	void updateBalance(Balance balance);

	/**
	 * 提现统计
	 * @param marketid
	 * @return
	 */
	int withdrawCount(@Param("wxserverid") String marketid);

	/**
	 * 未提现统计
	 * @param marketid
	 * @return
	 */
	int noWithdrawCount(@Param("wxserverid") String marketid);
	
	
}
