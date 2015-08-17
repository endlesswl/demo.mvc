package com.palm.lingcai.service.marketplan;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.palm.lingcai.entity.Marketplan;
import com.palm.lingcai.entity.UserLottery;
import com.palm.lingcai.repository.MarketplanDao;
import com.palm.lingcai.repository.UserLotteryDao;
import com.palm.lingcai.service.UserLotteryService;
import com.palm.lingcai.service.redis.RedisListOpsService;

/**
 * for 蒙牛营销计划
 * 
 * @author LDL
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MengniuService {
    @Autowired
    public RedisListOpsService redisListOpsService;
    @Autowired
    private UserLotteryDao userLotteryDao;
    @Autowired
    private UserLotteryService userlotteryService;
	@Autowired
	private MarketplanDao marketplanDao;
	  
	
	/**
	 * 蒙牛零彩
	 */
    public void mengniu_order(String channelKey, String channelOrder,String unitPriceCode,String moneyType,
    		String toMobile,String toOpenId,String gameid,String fromOpenId,HttpServletRequest request) throws	Exception	{

    	Marketplan marketplan = marketplanDao.getByMarketplanKey(channelKey);
    	
		// 下订单
    	userlotteryService.createOrder(marketplan, channelOrder, unitPriceCode, moneyType, toMobile, "true", gameid, null, request);
		// 附加字段
    	UserLottery userLottery = userLotteryDao.findOnlyChannelOrder(marketplan.getId(), channelOrder);
    	userLottery.setRemark(toOpenId);
    	userLottery.setAuthor(fromOpenId);
    	userLotteryDao.update(userLottery);
    }
}
