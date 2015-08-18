package com.palm.lingcai.dao;

import java.util.List;
import java.util.Map;

import com.palm.lingcai.entity.TestDemo;
import com.palm.lingcai.dao.page.Page;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository("testDemoDao")
public interface TestDemoDao {
	
	TestDemo get(Long id);

	List<TestDemo> search(Map<String, Object> parameters);

	Page<TestDemo> searchPage(@Param("searchFields") Map<String, Object> searchParams, Page pageRequest);

	void insert(TestDemo testDemo);

	void delete(Long id);

	void update(TestDemo testDemo);
}
