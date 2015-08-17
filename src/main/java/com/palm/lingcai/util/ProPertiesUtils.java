package com.palm.lingcai.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ProPertiesUtils {
	private static String fucaiServer;
	private static String fucaiKey;
	private static String fucaiPartnerid;
	private static String ticaiServer;
	private static String ticaiKey;
	private static String ticaiPartnerid;
	private static String prizepath;
	private static String channelPrizePath;
	private static String payBankUrl;
	private static String payReusltUrl;
	private static String payResultPath;
	static {
		InputStream inputStream = ProPertiesUtils.class.getClassLoader()
				.getResourceAsStream("application.properties");
		Properties pro = new Properties();
		try {
			pro.load(inputStream);
			channelPrizePath = (String) pro.get("channelPrizePath");
			prizepath = (String) pro.get("prizepath");
			fucaiServer = (String) pro.get("fucai.server");
			fucaiKey = (String) pro.get("fucai.key");
			fucaiPartnerid = (String) pro.get("fucai.partnerid");
			ticaiServer = (String) pro.get("ticai.server");
			ticaiKey = (String) pro.get("ticai.key");
			ticaiPartnerid = (String) pro.get("ticai.partnerid");

			payBankUrl = (String) pro.get("payBank.url");
			payReusltUrl = (String) pro.get("payReuslt.url");
			payResultPath = (String) pro.get("payResult.path");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String getFucaiServer() {
		return fucaiServer;
	}

	public static String getFucaiKey() {
		return fucaiKey;
	}

	public static String getFucaiPartnerid() {
		return fucaiPartnerid;
	}

	public static String getTicaiServer() {
		return ticaiServer;
	}

	public static String getTicaiKey() {
		return ticaiKey;
	}

	public static String getTicaiPartnerid() {
		return ticaiPartnerid;
	}

	public static String getPrizepath() {
		return prizepath;
	}

	public static String getChannelPrizePath() {
		return channelPrizePath;
	}

	public static String getPayBankUrl() {
		return payBankUrl;
	}

	public static String getPayReusltUrl() {
		return payReusltUrl;
	}

	public static String getPayResultPath() {
		return payResultPath;
	}

}
