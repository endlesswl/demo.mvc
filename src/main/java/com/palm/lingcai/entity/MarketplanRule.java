package com.palm.lingcai.entity;

/**
 *  各种优惠劵 编号
 * @author Administrator
 *
 */
public class MarketplanRule {

    /**
     *
     */
    private Long id;
    /**
     * 规则字符串
     */
    private String rule;
    /** 
     * 单张票分给多少人
     */
    private Integer ticketUserNum;
    /**
     * 单张票的分组
     */
    private Integer ticketGroup;
    /**
     * 单住彩票拆分份数
     */
    private Integer ticketCopies;
    
	public Integer getTicketUserNum() {
		return ticketUserNum;
	}
	public void setTicketUserNum(Integer ticketUserNum) {
		this.ticketUserNum = ticketUserNum;
	}
	public Integer getTicketGroup() {
		return ticketGroup;
	}
	public void setTicketGroup(Integer ticketGroup) {
		this.ticketGroup = ticketGroup;
	}
	public Integer getTicketCopies() {
		return ticketCopies;
	}
	public void setTicketCopies(Integer ticketCopies) {
		this.ticketCopies = ticketCopies;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
    
}