package com.palm.lingcai.service;

import java.math.BigDecimal;

import com.palm.lingcai.entity.*;
import com.palm.lingcai.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Title:
 * @Description:
 * @Author jhe
 * @Date 2013 - 2013
 * @Version V1.0
 * @Copyright © 2013 掌信彩通信息科技(中国)有限公司. All rights reserved.
 */
// Spring Service Bean的标识.
@Service
@Transactional
public class MarketplanService {
	@Autowired
	private MarketplanDao marketplanDao;

    public Marketplan get(Long id) {
        return marketplanDao.get(id);
    }
    
	public Marketplan getByMarketplanKey(String marketAppKey) {
		return marketplanDao.getByMarketplanKey(marketAppKey);
	}
	
	public void updateMarketBanlance(Long id,BigDecimal money) {
		marketplanDao.updateMarketBanlance(id, money);
	}
}
