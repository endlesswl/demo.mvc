package com.palm.lingcai.entity;

import java.io.Serializable;
import java.util.Date;

public class AccessLog implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7062762408698960160L;
	private Long id;
    /**
     * 微信用户id
     */
    private String openid;
    /**
     * 来源
     */
    private String refurl;

    /**
     * 微信服务号
     */
    private String serverid;

    /**
     * 时间
     */
    private Date createtime;

    /**
     * 分享次数
     */
    private Integer send;
    /**
     * 领取次数
     */
    private Integer receive;
    /**
     * 是否關注
     *
     * @return
     */
    private Integer isFocus;
    /**
     * 注册
     *
     * @return
     */
    private Integer register;
    /**
     * 访问点击数
     */
    private Long click;

    public Long getClick() {
        return click;
    }

    public void setClick(Long click) {
        this.click = click;
    }

    public Integer getRegister() {
        return register;
    }

    public void setRegister(Integer register) {
        this.register = register;
    }

    public Integer getIsFocus() {
        return isFocus;
    }

    public void setIsFocus(Integer isFocus) {
        this.isFocus = isFocus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getRefurl() {
        return refurl;
    }

    public void setRefurl(String refurl) {
        this.refurl = refurl;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getSend() {
        return send;
    }

    public void setSend(Integer send) {
        this.send = send;
    }

    public Integer getReceive() {
        return receive;
    }

    public void setReceive(Integer receive) {
        this.receive = receive;
    }

    public String getServerid() {
        return serverid;
    }

    public void setServerid(String serverid) {
        this.serverid = serverid;
    }
}
