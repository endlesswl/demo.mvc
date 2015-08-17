package com.palm.lingcai.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.palm.lingcai.entity.Marketplan;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository("marketplanDao")
public interface MarketplanDao {
	Marketplan get(Long id);

	List<Marketplan> search(Map<String, Object> parameters);
	
	Page<Marketplan> searchPage(@Param("searchFields") Map<String, Object> searchParams, Pageable pageRequest);
	
	Page<Marketplan> searchForMessageByUserid(@Param("userid") Long userid, Pageable pageRequest);
	
	Page<Marketplan> searchFundsDetailList(@Param("userid") Long userid, @Param("channel") Integer channel, Pageable pageRequest);
	
	void insert(Marketplan marketplan);
	
	void delete(Long id);
	
	void update(Marketplan marketplan);

	/**
	 * 根据marketplan中marketAppKey查询
	 * @param marketAppKey
	 * @return
	 */
	Marketplan getByMarketplanKey(@Param("marketAppKey")String marketAppKey);

	/**
	 * 更新营销余额
	 * @param marketplan
	 */
	void updateMarketBanlance(@Param("id")Long id, @Param("money")BigDecimal money);

}
