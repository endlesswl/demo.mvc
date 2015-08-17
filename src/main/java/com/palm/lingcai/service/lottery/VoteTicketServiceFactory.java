package com.palm.lingcai.service.lottery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 出票工厂类
 * <p/>
 * 福彩和体彩分别返回不同的实例
 * <p/>
 * Created by jianhe on 14-2-7.
 */
@Service
public class VoteTicketServiceFactory {
    @Autowired
    private VoteTicketService ticaiVoteTicketService;

    @Autowired
    private VoteTicketService fucaiVoteTicketService;

    /**
     * 返回具体处理实例对象
     *
     * @param gametype
     * @return
     */
    public VoteTicketService getInstance(final int gametype) {
        if (1 == gametype) {
            return ticaiVoteTicketService;
        } else if (2 == gametype) {
            return fucaiVoteTicketService;
        } 
        return null;
    }
}
