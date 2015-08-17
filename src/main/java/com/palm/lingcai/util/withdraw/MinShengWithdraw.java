package com.palm.lingcai.util.withdraw;

import java.math.BigDecimal;

public class MinShengWithdraw {
	
	private String cardno;//提现银行卡
	
	private BigDecimal money;//提现金额
	
	private String realName;//提现姓名

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	
	
	

}
