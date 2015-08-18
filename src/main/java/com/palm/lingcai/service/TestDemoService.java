package com.palm.lingcai.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import org.springside.modules.cache.memcached.SpyMemcachedClient;

import com.palm.lingcai.dao.TestDemoDao;
import com.palm.lingcai.dao.page.Page;
import com.palm.lingcai.entity.TestDemo;
import com.palm.lingcai.service.memcached.TestDemoMemcachedClient;
import com.palm.lingcai.service.redis.RedisListOpsService;

@Service
@Transactional
public class TestDemoService {

//	@Autowired
//	private SpyMemcachedClient spymemcachedClient;

	@Autowired
	private TestDemoMemcachedClient memcachedClient;

	@Autowired
	public RedisListOpsService redisListOpsService;

	private static Logger logger = LoggerFactory.getLogger(TestDemoService.class);

	@Autowired
	private TestDemoDao testDemoDao;

	/**
	 * 分页查询
	 * 
	 * @param searchParams
	 *            查询条件
	 * @param pageable
	 *            分页参数
	 * @return
	 */
	public Page<TestDemo> searchPage(Map<String, Object> searchParams, Page pageable) {
		return testDemoDao.searchPage(searchParams, pageable);
	}

	/**
	 * 不分页查询
	 * 
	 * @param searchParas
	 *            查询条件
	 * @return
	 */
	public List<TestDemo> search(Map<String, Object> searchParas) {
		return testDemoDao.search(searchParas);
	}

	public TestDemo get(Long id) {
		return testDemoDao.get(id);
	}

	public void insert(TestDemo testDemo) {
		testDemo.setCreateTime(new Date());
		testDemoDao.insert(testDemo);
	}

	public void update(TestDemo testDemo) {
		testDemoDao.update(testDemo);
	}

	public void delete(Long id) {
		testDemoDao.delete(id);
	}

	public void aboutMem() {
//		TestDemo testDemo = spymemcachedClient.get("getMemcacheLottery");
//		spymemcachedClient.safeSet("getMemcache", 60 * 60 * 24 * 30, testDemo);
	}

	public void aboutRedis() {
		redisListOpsService.in("getRedis", "value");
		redisListOpsService.pop("getRedis");
	}
}
