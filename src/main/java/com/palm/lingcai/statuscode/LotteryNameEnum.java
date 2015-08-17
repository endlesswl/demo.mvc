package com.palm.lingcai.statuscode;

/**
 * 彩种名称枚举 Created by jianhe on 14-2-19.
 */
public enum LotteryNameEnum {
	DLT("DLT", 1, "大乐透"), // 大乐透
	QXC("QXC", 1, "七星彩"), // 七星彩
	QLC("QLC", 2, "七乐彩"), // 七乐彩
	SSQ("SSQ", 2, "双色球"), // 双色球
	SD("3D", 2, "3D");// 3D

	private String code;
	private Integer gameType;
	private String name;
	public static String[] gameids = { "SSQ", "DLT", "3D", "QXC", "QLC" };

	private LotteryNameEnum(String code, Integer gameType, String name) {
		this.code = code;
		this.name = name;
		this.gameType = gameType;
	}

	public String getName() {
		return this.name;
	}
	public String getCode() {
		return code;
	}
	public Integer getGameType() {
		return gameType;
	}

	/**
	 * 根据编码获取中文名称
	 * 
	 * @param value
	 * @return
	 */
	public static String getName(String code) {
		for (LotteryNameEnum lotteryNameEnum : LotteryNameEnum.values()) {
			if (lotteryNameEnum.code.equals(code)) {
				return lotteryNameEnum.name;
			}
		}
		return "";
	}

	/**
	 * 根据编码获取中文名称
	 * 
	 * @param value
	 * @return
	 */
	public static Integer getGameType(String code) {
		for (LotteryNameEnum lotteryNameEnum : LotteryNameEnum.values()) {
			if (lotteryNameEnum.code.equals(code)) {
				return lotteryNameEnum.gameType;
			}
		}
		return -1;
	}
}
