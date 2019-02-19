package com.cn.jxf.domain.trade;

import java.util.Date;

import com.cn.jxf.domain.BaseEntity;


public class TraderInfo extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//外汇交易对手成员ID
	private String memberId;
	
	//外汇交易对手中文全称
	private String memberLongname;
	
	//交易对手交易员ID
	private String traderId;
	
	//交易对手交易员姓名
	private String traderName;
	
	//电话
	private String tel;
	
	//传真
	private String fax;
	
	//邮箱
	private String email;
	
	//MSN
	private String msn;
	
	//联系地址
	private String contactAddress;
	
	//邮编
	private String postcode;
	
	//落地时间
	private Date groundTime;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberLongname() {
		return memberLongname;
	}

	public void setMemberLongname(String memberLongname) {
		this.memberLongname = memberLongname;
	}

	public String getTraderId() {
		return traderId;
	}

	public void setTraderId(String traderId) {
		this.traderId = traderId;
	}

	public String getTraderName() {
		return traderName;
	}

	public void setTraderName(String traderName) {
		this.traderName = traderName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMsn() {
		return msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public Date getGroundTime() {
		return groundTime;
	}

	public void setGroundTime(Date groundTime) {
		this.groundTime = groundTime;
	}
	
}
