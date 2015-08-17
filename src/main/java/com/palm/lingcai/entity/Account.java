package com.palm.lingcai.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
/**
 * <p>标题：账户信息实体类 </p>
 * <p>功能： </p>
 * <p>版权： Copyright (c) 2014</p>
 * <p>公司: 北京阳光彩通</p>
 * <p>创建日期：2014年11月3日 上午11:09:24</p>
 * <p>类全名：com.palm.lingcai.entity.Account</p>
 * <p>作者：JIJI</p>
 * <p>@version 1.0</p>
 */
public class Account
{
	/*
	 * 用户ID
	 */
	Long						userid;
	/**
	 * 用户名
	 */
	String						userName;
	/*
	 * 奖金
	 */
	BigDecimal					prize;
	/*
	 * 充值余额
	 */
	BigDecimal					balance;
	/*
	 * 零彩卷
	 */
	BigDecimal					certificate;
	/*
	 * 彩票资产
	 */
	BigDecimal					lotteryAsset;
	/*
	 * 话费资产
	 */
	BigDecimal					fareAsset;
	/*
	 * 流量资产
	 */
	BigDecimal					flowAsset;
	/*
	 * 当月彩票资产
	 */
	BigDecimal					lotteryMonthAsset;
	/*
	 * 当月话费资产
	 */
	BigDecimal					fareMonthAsset;
	/*
	 * 当月流量资产
	 */
	BigDecimal					flowMonthAsset;
	/*
	 * 年收益明细
	 */
	List<Map<String,Object>>	yearProceedsDetail;
	/*
	 * 月收益明细
	 */
	List<Map<String,Object>>	monthProceedsDetai;

	public Long getUserid()
	{
		return userid;
	}

	public void setUserid(Long userid)
	{
		this.userid = userid;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public BigDecimal getPrize()
	{
		return prize;
	}

	public void setPrize(BigDecimal prize)
	{
		this.prize = prize;
	}

	public BigDecimal getBalance()
	{
		return balance;
	}

	public void setBalance(BigDecimal balance)
	{
		this.balance = balance;
	}

	public BigDecimal getCertificate()
	{
		return certificate;
	}

	public void setCertificate(BigDecimal certificate)
	{
		this.certificate = certificate;
	}

	public BigDecimal getLotteryAsset()
	{
		return lotteryAsset;
	}

	public void setLotteryAsset(BigDecimal lotteryAsset)
	{
		this.lotteryAsset = lotteryAsset;
	}

	public BigDecimal getFareAsset()
	{
		return fareAsset;
	}

	public void setFareAsset(BigDecimal fareAsset)
	{
		this.fareAsset = fareAsset;
	}

	public BigDecimal getFlowAsset()
	{
		return flowAsset;
	}

	public void setFlowAsset(BigDecimal flowAsset)
	{
		this.flowAsset = flowAsset;
	}

	public BigDecimal getLotteryMonthAsset()
	{
		return lotteryMonthAsset;
	}

	public void setLotteryMonthAsset(BigDecimal lotteryMonthAsset)
	{
		this.lotteryMonthAsset = lotteryMonthAsset;
	}

	public BigDecimal getFareMonthAsset()
	{
		return fareMonthAsset;
	}

	public void setFareMonthAsset(BigDecimal fareMonthAsset)
	{
		this.fareMonthAsset = fareMonthAsset;
	}

	public BigDecimal getFlowMonthAsset()
	{
		return flowMonthAsset;
	}

	public void setFlowMonthAsset(BigDecimal flowMonthAsset)
	{
		this.flowMonthAsset = flowMonthAsset;
	}

	public List<Map<String,Object>> getYearProceedsDetail()
	{
		return yearProceedsDetail;
	}

	public void setYearProceedsDetail(List<Map<String,Object>> yearProceedsDetail)
	{
		this.yearProceedsDetail = yearProceedsDetail;
	}

	public BigDecimal getMonthProceeds()
	{
		BigDecimal monthProceeds = BigDecimal.ZERO;
		for (Map<String,Object> recordMap : monthProceedsDetai)
		{
			BigDecimal proceeds = (BigDecimal) recordMap.get("proceeds");
			monthProceeds = monthProceeds.add(proceeds);
		}
		return monthProceeds;
	}

	public List<Map<String,Object>> getMonthProceedsDetai()
	{
		return monthProceedsDetai;
	}

	public void setMonthProceedsDetai(List<Map<String,Object>> monthProceedsDetai)
	{
		this.monthProceedsDetai = monthProceedsDetai;
	}

	/**
	 * 总余额
	 * @return
	 */
	public BigDecimal getTotalBalance()
	{
		return balance.add(certificate);
	}

	/*
	 * 累计资产(彩票 + 流量 + 话费)
	 */
	public BigDecimal getTotalAsset()
	{
		return lotteryAsset.add(fareAsset).add(flowAsset);
	}

	/**
	 * 当月资产收益(彩票 + 流量 + 话费)
	 * @return
	 */
	public BigDecimal getMonthAsset()
	{
		return lotteryMonthAsset.add(fareMonthAsset).add(flowMonthAsset);
	}

	/**
	 * 年收益(本年彩票中奖金额)
	 * @return
	 */
	public BigDecimal getYearProceeds()
	{
		BigDecimal yearProceeds = BigDecimal.ZERO;
		for (Map<String,Object> recordMap : monthProceedsDetai)
		{
			BigDecimal proceeds = (BigDecimal) recordMap.get("proceeds");
			yearProceeds = yearProceeds.add(proceeds);
		}
		return yearProceeds;
	}
}
