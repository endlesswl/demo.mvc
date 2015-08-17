package com.palm.lingcai.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.palm.lingcai.entity.MarketRotateRule;


@Repository("marketRotateRuleDao")
public interface MarketRotateRuleDao {

	MarketRotateRule get(String id);
	
	MarketRotateRule getByName(@Param("rotateName")String rotateName);

	List<MarketRotateRule> search(Map<String, Object> parameters);

	Page<MarketRotateRule> searchPage(@Param("searchFields") Map<String, Object> searchParams, Pageable pageRequest);

	void insert(MarketRotateRule marketRotateRule);

	void delete(String id);

	void update(MarketRotateRule marketRotateRule);

}
