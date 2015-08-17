package com.palm.lingcai.statuscode;
/**
 * @Title: RedisKeyEnum.java
 * @Description:(交易记录表中的状态枚举)
 * @author kelylmall
 * @email ming.li@lingcaibao.com
 * @date 2014年10月15日 上午10:50:37
 * @version V1.0
 */
public enum RedisKeyEnum {
	SPLIT_QUEUE("lc_split_queue:",-1,"拆分彩票队列"),
	
	// 上期开奖球号
	_PREVIOUS_PRIZE_BALL("_previous_prize_ball",-1,"上期开奖号码"),//PRIZE_BALL_+gameId
	
	//各个彩KEY
	SSQ_ISSUE_KEY("ssq_issue_key",-1,"双色球的当前期次"),
	DLT_ISSUE_KEY("dlt_issue_key",-1,"大乐透当前期次"),
	QXC_ISSUE_KEY("qxc_issue_key",-1,"qxc当前期次"),
	D3_ISSUE_KEY("3d_issue_key",-1,"3d当前期次");
	
	private String key;// 简称
	private int timeOut;// 过期时间
	private String msg;//描述
	private RedisKeyEnum(String key,  int timeOut,String msg) {
		this.key = key;
		this.timeOut = timeOut;
		this.msg=msg;
	}

	public String getKey() {
		return key;
	}

	public int getTimeOut() {
		return timeOut;
	}

	public String getMsg() {
		return msg;
	}

}
