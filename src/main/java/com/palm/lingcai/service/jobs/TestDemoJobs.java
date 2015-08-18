package com.palm.lingcai.service.jobs;

import org.springframework.stereotype.Service;

@Service
public class TestDemoJobs {
	
    /**
	 * 定时服务
	 */
	public static Integer num = 1;
	public void initDemoMsg() {
		System.out.println("定时器时间输出：" + (++num));
	}
}
