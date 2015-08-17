package com.palm.lingcai.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.DateProvider;

import com.alibaba.fastjson.JSON;
import com.palm.lingcai.entity.Lottery;
import com.palm.lingcai.repository.LotteryDao;
import com.palm.lingcai.service.redis.RedisService;
import com.palm.lingcai.statuscode.LotteryStatusEnum;

/**
 * @Title: 系统彩票投注
 * @Description:
 * @Author jhe
 * @Date 2013 - 2013
 * @Version V1.0
 * @Copyright © 2013 掌信彩通信息科技(中国)有限公司. All rights reserved.
 */
// Spring Service Bean的标识.
@Service
@Transactional
public class LotteryService {
    private static Logger logger = LoggerFactory.getLogger(LotteryService.class);
    @Autowired
    public RedisService redisService;
    @Autowired
    private LotteryDao lotteryDao;
    private DateProvider dateProvider = DateProvider.DEFAULT;
    
    public Lottery get(Long id) {
        return lotteryDao.get(id);
    }
    
    public void insert(Lottery lottery) {
        lottery.setLotteryStatus(LotteryStatusEnum.PROCESSING.getValue());
        lottery.setCreatetime(dateProvider.getDate());
        lotteryDao.insert(lottery);
        redisService.set("lottery:" + lottery.getId(), JSON.toJSONString(lottery), 60 * 60 * 24 * 30);
    }
}
