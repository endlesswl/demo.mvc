package com.palm.lingcai.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.palm.lingcai.entity.Role;
import com.palm.lingcai.entity.User;
@Repository("userDao")
public interface UserDao
{
	User findByUserName(@Param("username") String username);
	
	User findUserByUserType(@Param("username") String username, @Param("userType") int userType);

	User findByEmail(@Param("id") Long id, @Param("email") String email);

	User findByMobile(String mobile);

	User get(@Param("id") Long id);

	List<User> search(Map<String,Object> parameters);

	Page<User> searchPage(@Param("searchFields") Map<String,Object> searchParams, Pageable pageRequest);

	List<User> searchPageByRecharge(@Param("searchFields") Map<String,Object> searchParams);

	Page<User> searchPageByRechargePage(@Param("searchFields") Map<String,Object> searchParams, Pageable pageable);

	Page<User> searchByUserMarket(@Param("userid") Long userid, Pageable pageRequest);

	int countByUserMarket(@Param("userid") Long userid, Pageable pageRequest);
	
	int countUser(@Param("mobile")String mobile);

	void insert(User user);

	void delete(Long id);

	void update(User user);

	void updateBalance(User user);

	void updateBalanceNoPlus(User user);

	void updatePwd(User user);

	void updateLogintimes(User user);

	List<Role> findRoleLinks(Long userId);

	int countByMarketPlan(Long userid);

	User findByPwd(@Param("userid") Long userid, @Param("pwd") String password);

	User findByPwdAndUserName(@Param("username") String username, @Param("pwd") String password);

	Map<String,Object> balanceConfirm(@Param("userid") Long userid);

	User findUserByOpenid(@Param("openid") String openid);

	void updatePayPwd(User user);
	
	//====================lingcai sdk============
	/**
	 * 更新返奖余额
	 * @param user
	 */
	void updateRewardBalance(User user);
	
	/**
	 * 更新余额预警状态
	 * @param warnStatus
	 */
	void updateWarnFlag(@Param("id")Long id,@Param("warnFlag")String warnFlag);
 }
