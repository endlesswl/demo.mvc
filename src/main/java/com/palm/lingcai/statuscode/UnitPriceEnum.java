package com.palm.lingcai.statuscode;

import java.text.DecimalFormat;

/**
 * @Title: UnitPriceEnum.java
 * @Description:(拆分彩票单价状态枚举)
 * @author kelylmall
 * @email ming.li@lingcaibao.com
 * @date 2014年10月15日 上午10:50:37
 * @version V1.0
 */
public enum UnitPriceEnum {
	// 拆分金额状态码(单位分)
	UNIT_PRICE_001("0.01", "001", "0.01元"), 
	UNIT_PRICE_002("0.02", "002", "0.02元"), 
	UNIT_PRICE_005("0.05", "005", "0.05元"), 
	UNIT_PRICE_010("0.10", "010", "0.1元"), 
	UNIT_PRICE_020("0.20", "020", "0.2元"), 
	UNIT_PRICE_040("0.40", "040", "0.4元"), 
	UNIT_PRICE_050("0.50", "050", "0.5元"), 
	UNIT_PRICE_100("1.00", "100", "1元"), 
	UNIT_PRICE_200("2.00", "200", "2元")
	;
	private String alias;// 简称
	private String code;// 状态码
	private String msg;// 描述

	private UnitPriceEnum(String alias, String code, String msg) {
		this.alias = alias;
		this.code = code;
		this.msg = msg;
	}

	public String getAlias() {
		return alias;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	/**
	 * 检查拆分金额是否匹配
	 * 
	 * @param unitPrice
	 * @return
	 */
	public static boolean checkUnitPriceCode(Object unitPrice) {
		for (UnitPriceEnum unitPriceEnum : UnitPriceEnum.values()) {
			String code = unitPriceEnum.getCode();
			if (code.equals((String) unitPrice)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 检查拆分金额是否匹配
	 * 
	 * @param unitPrice
	 * @return
	 */
	public static boolean checkUnitPriceAlias(String unitPrice) {
		for (UnitPriceEnum unitPriceEnum : UnitPriceEnum.values()) {
			String alias = unitPriceEnum.getAlias();
			if (alias.equals(unitPrice)) {
				return true;
			}
		}
		return false;
	}
	
	public static UnitPriceEnum getUnitPriceByAlias(String unitPrice) {
		for (UnitPriceEnum unitPriceEnum : UnitPriceEnum.values()) {
			if (unitPriceEnum.getAlias().equals(unitPrice)) {
				return unitPriceEnum;
			}
		}
		return null;
	}

	/**
	 * 格式化：0.00
	 * 
	 * @param unitPrice
	 * @return
	 */
	public static String formatPrice(String unitPrice) {
		try {
			DecimalFormat dformat = new DecimalFormat("0.00");
			return dformat.format(Double.parseDouble(unitPrice));
		} catch (Exception e) {
			return unitPrice;
		}
	}
}