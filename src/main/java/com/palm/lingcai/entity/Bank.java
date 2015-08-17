package com.palm.lingcai.entity;

public class Bank {

    // alias
    public static final String TABLE_ALIAS = "银行卡";
    public static final String ALIAS_ID = "ID";
    public static final String ALIAS_USERID = "用户ID";
    public static final String ALIAS_BANKNAME = "开户行名称";
    public static final String ALIAS_ABBREVIATION = "开户行简称";
    public static final String ALIAS_DESCRIPTION = "描述";
    public static final String ALIAS_ACCOUNTNAME = "户名";
    public static final String ALIAS_CARDNO = "卡号";
    public static final String ALIAS_CARDTYPE = "卡片类型";

    /**
     *
     */
    private Long id;
    /**
     * 用户ID
     */
    private Long userid;
    /**
     * 开户行全称
     */
    private String bankname;
    /**
     * 开户行简写
     */
    private String abbreviation;
    /**
     * 描述
     */
    private String description;
    /**
     * 户名
     */
    private String accountname;
    /**
     * 卡号
     */
    private String cardno;
    /**
     * 卡类型
     */
    private Integer cardtype;
    
    private String city;
    
    private String province;
    
    private String subbranch;
    
    private Integer deleteFlag=0;
    

    public java.lang.Long getId() {
        return this.id;
    }

    public void setId(java.lang.Long value) {
        this.id = value;
    }

    public java.lang.Long getUserid() {
        return this.userid;
    }

    public void setUserid(java.lang.Long value) {
        this.userid = value;
    }

    public java.lang.String getBankname() {
        return this.bankname;
    }

    public void setBankname(java.lang.String value) {
        this.bankname = value;
    }

    public java.lang.String getAbbreviation() {
        return this.abbreviation;
    }

    public void setAbbreviation(java.lang.String value) {
        this.abbreviation = value;
    }

    public java.lang.String getDescription() {
        return this.description;
    }

    public void setDescription(java.lang.String value) {
        this.description = value;
    }

    public java.lang.String getAccountname() {
        return this.accountname;
    }

    public void setAccountname(java.lang.String value) {
        this.accountname = value;
    }

    public java.lang.String getCardno() {
        return this.cardno;
    }

    public void setCardno(java.lang.String value) {
        this.cardno = value;
    }

    public Integer getCardtype() {
        return this.cardtype;
    }

    public void setCardtype(Integer value) {
        this.cardtype = value;
    }

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getSubbranch() {
		return subbranch;
	}

	public void setSubbranch(String subbranch) {
		this.subbranch = subbranch;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
	
}