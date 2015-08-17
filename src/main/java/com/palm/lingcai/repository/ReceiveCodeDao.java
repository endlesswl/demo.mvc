package com.palm.lingcai.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.palm.lingcai.entity.ReceiveCode;

@Repository("receiveCodeDao")
public interface ReceiveCodeDao {

	void updateFlagByOrderId(@Param("orderId") String orderId);
	
	void insert(ReceiveCode receiveCode);
	
	ReceiveCode findReceiveCodeByOrderId(@Param("orderId") String orderId);
	
	void update(ReceiveCode receiveCode);
}
