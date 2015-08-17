package com.palm.lingcai.service.lottery;

import com.palm.lingcai.entity.Lottery;
import com.palm.lingcai.entity.Prize;
import com.palm.lingcai.exception.LotteryException;

import java.util.List;

/**
 * 投注接口
 * <p/>
 * 投注分为大体三步实现：获取当前期(请求-解析响应)，投注(请求-解析响应)，查询结果(请求-解析响应)
 * Created by jianhe on 13-12-16.
 */
public interface VoteTicketService {
    /**
     * 获取当前期
     *
     * @param gameid    彩种
     * @param transcode 接口代码
     * @return
     */
    public Lottery getIssueNo(String gameid) throws LotteryException;

    /**
     * 投注
     *
     * @param lottery
     * @return
     */
    public void voteTicket(Lottery lottery) throws LotteryException;

    /**
     * 查询投注结果
     *
     * @param lottery
     * @return
     */
    public void getResult(Lottery lottery) throws LotteryException;

    /**
     * 开奖查询
     *
     * @param lottery
     */
    public void prize(Prize prize) throws LotteryException;

    /**
     * 解析开奖文件
     *
     * @param prize
     * @return
     */
    public List<Prize> prizeFile(Prize prize) throws LotteryException;

    public <T> T prize(Lottery Lottery);
}
