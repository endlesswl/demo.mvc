package com.palm.lingcai.statuscode;

/**
 * 开奖时间枚举类
 * 
 * @author LDL 2015年1月4日
 */
public enum PrizeTimeEnum {
	SSQ_PRIZEDAY("2,4,7"), // 开奖日
	DLT_PRIZEDAY("1,3,6"), // 开奖日
	QXC_PRIZEDAY("2,5,7"), // 开奖日
	LINGCAI_STOP_TIME("193000"), // 零彩宝结期时间
	PALMTIME("195700"), // 平台截止收单时间(体彩是195800，这里统一195700)
	PALMNEWISSUETIME("235959");// 平台开新期时间

	public String code;

	private PrizeTimeEnum(String code) {
		this.code = code;
	}

	/**
	 * 获取开奖日
	 * @param gameId
	 * @return
	 */
	public static String getPrizeDay(String gameId) {
		if (gameId.equals("SSQ")) {
			return SSQ_PRIZEDAY.code;
		}
		if (gameId.equals("DLT")) {
			return DLT_PRIZEDAY.code;
		}
		if (gameId.equals("QXC")) {
			return QXC_PRIZEDAY.code;
		}
		return null;
	}
}
