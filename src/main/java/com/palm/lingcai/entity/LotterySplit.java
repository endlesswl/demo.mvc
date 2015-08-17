package com.palm.lingcai.entity;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LotterySplit implements Serializable {

    public static final String ALIAS_TOTALCOUNT = "总分配次数";
    public static final String ALIAS_ISBUY = "是否出票";
    public static final String ALIAS_REMAINDERPERCENT = "剩余百分比";
    public static final String ALIAS_REMAINDECOUNT = "剩余次数";
    public static final String ALIAS_ISTUHAO = "是否土豪送";

    /**
     *
     */
    private Long id;
    /**
     * 用户ID
     */
    private Long userid;
    /**
     * 计划ID
     */
    private Long marketid;
    /**
     * 彩票ID
     */
    private Long lotteryid;
    /**
     * 彩票IDs 用于土豪送显示
     */
    private String lotteryids;
    /**
     * 总分配次数
     */
    private Integer totalCount;
    /**
     * 剩余百分比
     */
    private BigDecimal remainderPercent;
    /**
     * 剩余次数
     */
    private Integer remainderCount;
    /**
     * 是否土豪送,0:是，1：否
     */
    private Integer isTuhao;
    private Date createtime;
    private Date updatetime;
    /**
     * 是否开奖
     */
    private Integer isprize;
    /**
     * 彩种
     */
    private String gameid;
    /**
     * 用户名
     *
     * @return
     */
    private String username;
    /**
     * 总记录数
     */
    private Integer count;

    /**
     * 是否出票 0未出票 1已出票
     */
    private Integer isticket;

    /**
     * 微信用户信息
     */
    private WXUser wxuser;

    /**
     * 用户中奖信息
     */
    private UserPrize userPrize;
    /**
     * 彩票信息
     */
    private Lottery lottery;

    public Lottery getLottery() {
        return lottery;
    }

    public void setLottery(Lottery lottery) {
        this.lottery = lottery;
    }

    public UserPrize getUserPrize() {
        return userPrize;
    }

    public void setUserPrize(UserPrize userPrize) {
        this.userPrize = userPrize;
    }

    public WXUser getWxuser() {
        return wxuser;
    }

    public void setWxuser(WXUser wxuser) {
        this.wxuser = wxuser;
    }

    public String getLotteryids() {
        return lotteryids;
    }

    public void setLotteryids(String lotteryids) {
        this.lotteryids = lotteryids;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGameid() {
        return gameid;
    }

    public void setGameid(String gameid) {
        this.gameid = gameid;
    }

    public Integer getIsprize() {
        return isprize;
    }

    public void setIsprize(Integer isprize) {
        this.isprize = isprize;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getLotteryid() {
        return lotteryid;
    }

    public void setLotteryid(Long lotteryid) {
        this.lotteryid = lotteryid;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public BigDecimal getRemainderPercent() {
        return remainderPercent;
    }

    public void setRemainderPercent(BigDecimal remainderPercent) {
        this.remainderPercent = remainderPercent;
    }

    public Integer getRemainderCount() {
        return remainderCount;
    }

    public void setRemainderCount(Integer remainderCount) {
        this.remainderCount = remainderCount;
    }

    public Integer getIsTuhao() {
        return isTuhao;
    }

    public void setIsTuhao(Integer isTuhao) {
        this.isTuhao = isTuhao;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Long getMarketid() {
        return marketid;
    }

    public void setMarketid(Long marketid) {
        this.marketid = marketid;
    }

    public Integer getIsticket() {
        return isticket;
    }

    public void setIsticket(Integer isticket) {
        this.isticket = isticket;
    }
}
