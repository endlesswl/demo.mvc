package com.palm.lingcai.statuscode;

/**
 * 统一定义Redis中存储的各种对象的Key前缀和超时时间.
 * @author calvin
 */
public enum RedisKeyType {
	
	TICKET_PALN("ticket_paln:",60*60*30), 
	
	
	;
	private String prefix;
	private int expiredTime;

	RedisKeyType(String prefix, int expiredTime) {
		this.prefix = prefix;
		this.expiredTime = expiredTime;
	}

	public String getPrefix() {
		return prefix;
	}

	public int getExpiredTime() {
		return expiredTime;
	}

}
