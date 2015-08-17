package com.palm.lingcai.entity.entityenum;

public enum YiliLotteryUserEnum
{
	//葛宏伟
	GEHONGWEI(18),
	//冀冀
	JIJI(346604),
	//冯树林
	FENGSHULIN(17424),
	// 何建
	HEJIAN(7),
	// 晓东
	XIAODONG(62116);
	private int	userid;

	private YiliLotteryUserEnum(int userid)
	{
		this.userid = userid;
	}

	public int getUserid()
	{
		return this.userid;
	}
}
