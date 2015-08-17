package com.palm.lingcai.entity;

/**
 *  各种优惠劵 编号
 * @author Administrator
 *
 */
public class Code {

    /**
     *
     */
    private Long id;
    /**
     * 营销计划ID
     */
    private Long marketid;
    /**
     * 营销计划ID
     */
    private Long lotterySplitid;
    /**
     * code
     */
    private String code;
    /**
     * 是否使用
     */
    private Integer isUse;
    /**
     * 创建时间
     */
    private java.util.Date createtime;
    /**
     * 使用时间
     */
    private java.util.Date updatetime;
    /**
     * 領取用戶ID
     */
    private Long userid;
    /**
     * 
     */
    private String openid;
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getLotterySplitid() {
		return lotterySplitid;
	}
	public void setLotterySplitid(Long lotterySplitid) {
		this.lotterySplitid = lotterySplitid;
	}
	public Long getMarketid() {
		return marketid;
	}
	public void setMarketid(Long marketid) {
		this.marketid = marketid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getIsUse() {
		return isUse;
	}
	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}
	public java.util.Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}
	public java.util.Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(java.util.Date updatetime) {
		this.updatetime = updatetime;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
}