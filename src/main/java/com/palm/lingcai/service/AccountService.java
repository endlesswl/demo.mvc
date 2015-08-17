package com.palm.lingcai.service;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.cache.memcached.SpyMemcachedClient;
import org.springside.modules.mapper.JsonMapper;

import com.palm.commom.entityenum.MemcachedObjectType;
import com.palm.commom.uitl.DateProvider;
import com.palm.commom.uitl.Digests;
import com.palm.commom.uitl.Encodes;
import com.palm.lingcai.entity.User;
import com.palm.lingcai.repository.UserDao;

@Service
@Transactional
public class AccountService {
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;
	@Autowired
	private UserDao userDao;
	@Autowired
	private SpyMemcachedClient memcachedClient; // memcached
	private DateProvider dateProvider = DateProvider.DEFAULT;
	private final JsonMapper jsonMapper = JsonMapper.nonDefaultMapper();

	public Long registerUser(User user) {
		entryptPassword(user);
		user.setCreateTime(dateProvider.getDate());
		user.setFlag(0);
		user.setSource(0);
		user.setPrize(BigDecimal.ZERO);
		user.setBalance(new BigDecimal("0.00"));// 新注册用户设置余额为0

		userDao.insert(user);
		return user.getId();
	}

	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(User user) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.setPwdSalt(Encodes.encodeHex(salt));
		byte[] hashPassword = Digests.sha1(user.getPwd().getBytes(), salt,
				HASH_INTERATIONS);
		user.setPwd(Encodes.encodeHex(hashPassword));
	}

	public User getForCache(Long id) {
		String userInfo = (String) memcachedClient.get(MemcachedObjectType.USER.getPrefix() + id);
		User user = null;
		if (StringUtils.isEmpty(userInfo)) {
			user = userDao.get(id);
			if (user != null) {
				memcachedClient.set(MemcachedObjectType.USER.getPrefix() + id, MemcachedObjectType.USER.getExpiredTime(), jsonMapper.toJson(user));
			}
		} else {
			user = jsonMapper.fromJson(userInfo, User.class);
		}
		return user;
	}

	/**
	 * 根据用户名查找用户
	 * 
	 * @param userName
	 * @return
	 */
	public User findUserByLoginName(String userName) {
		return userDao.findByUserName(userName);
	}

	public User findByUserName(String userName) {
		return userDao.findByUserName(userName);
	}
}
