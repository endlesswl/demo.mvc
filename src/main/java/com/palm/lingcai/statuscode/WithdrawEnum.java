package com.palm.lingcai.statuscode;

/**
 * 提现转账状态
 * @author LDL
 * 2014年12月24日
 */
public enum WithdrawEnum {
    WAIT(0,"待处理"),
    SUCCESS(1,"转账成功"),
    FAIL(2,"转账失败"),
    IN_PROGRESS(3,"转账中");
    
	public String name;
	public Integer code;

	private WithdrawEnum(Integer code, String name) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public Integer getCode() {
		return this.code;
	}
}
