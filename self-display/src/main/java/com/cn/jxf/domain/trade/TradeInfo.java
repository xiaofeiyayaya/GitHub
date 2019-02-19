package com.cn.jxf.domain.trade;

import java.util.Date;

import com.cn.jxf.domain.BaseEntity;


public class TradeInfo extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//外汇交易对手ID
	private String memberId;
	
	//外汇内码
	private String whPartyId;
	
	//交易对手中文全称
	private String memberLongname;
	
	//交易对手中文简称
	private String memberName;
	
	//交易对手英文全称
	private String memberLongnameEn;
	
	//交易对手英文简称
	private String memberNameEn;
	
	//落地时间
	private Date groundTime;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getWhPartyId() {
		return whPartyId;
	}

	public void setWhPartyId(String whPartyId) {
		this.whPartyId = whPartyId;
	}

	public String getMemberLongname() {
		return memberLongname;
	}

	public void setMemberLongname(String memberLongname) {
		this.memberLongname = memberLongname;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberLongnameEn() {
		return memberLongnameEn;
	}

	public void setMemberLongnameEn(String memberLongnameEn) {
		this.memberLongnameEn = memberLongnameEn;
	}

	public String getMemberNameEn() {
		return memberNameEn;
	}

	public void setMemberNameEn(String memberNameEn) {
		this.memberNameEn = memberNameEn;
	}

	public Date getGroundTime() {
		return groundTime;
	}

	public void setGroundTime(Date groundTime) {
		this.groundTime = groundTime;
	}
}
