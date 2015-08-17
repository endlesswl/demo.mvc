package com.palm.lingcai.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.palm.lingcai.entity.ReceiveCode;
import com.palm.lingcai.repository.ReceiveCodeDao;
import com.palm.lingcai.util.UUIDUtils;

@Service
@Transactional
public class ReceiveCodeService {

	private static Logger logger = LoggerFactory.getLogger(ReceiveCodeService.class);

	@Autowired
	private ReceiveCodeDao receiveCodeDao;

	/**
	 * 获取领取码
	 * 
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public String getCode(String orderId) throws Exception{
		receiveCodeDao.updateFlagByOrderId(orderId);
		
		String code = UUIDUtils.getNumberBySix();
		
		ReceiveCode receiveCode = new ReceiveCode();
		receiveCode.setCreatetime(new Date());
		receiveCode.setFlag(1);
		receiveCode.setOrderId(orderId);
		receiveCode.setReceiveCode(Long.parseLong(code));
		receiveCodeDao.insert(receiveCode);
		
		return code;
	}
	
	/**
	 * 检查领取码是否失效
	 * 
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public boolean checkCode(String orderId) throws Exception {
		ReceiveCode receiveCode = receiveCodeDao.findReceiveCodeByOrderId(orderId);
		if(receiveCode == null){
			throw new Exception("未获取领取码");
		}
		
		boolean flag = compareToMinute(receiveCode.getCreatetime(), new Date());
		if(!flag){
			receiveCodeDao.updateFlagByOrderId(orderId);
			throw new Exception("领取码过期失效");
		}
		
		receiveCode.setFlag(0);
		receiveCodeDao.update(receiveCode);
		return true;
	}
	
	/**
	 * 判断领取码的时间是否超过30分钟
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public boolean compareToMinute(Date beginDate, Date endDate) {
		long between = (endDate.getTime() - beginDate.getTime()) / 1000;
		long day = between / (24 * 3600);
		long hour = between % (24 * 3600) / 3600;
		long minute = between % 3600 / 60;
		if ((day == 0) && (hour == 0) && (minute <= 30)) {
			return true;
		} else {
			return false;
		}
	}
}
