package com.cn.jxf.domain.trade;

import java.util.Date;

import com.cn.jxf.domain.BaseEntity;


public class TradeRelation extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//外汇交易对手ID（主）
	private String memberId;
	
	//外汇交易对手名称（从）
	private String memberLongname;
	
	//外汇交易对手ID（主）
	private String subMemberId;
	
	//外汇交易对手名称（从）
	private String subMemberLongname;
	
	//主关系类型名称
	private String relaType;
	
	//从关系类型名称
	private String subRelaType;
	
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

	public String getSubMemberId() {
		return subMemberId;
	}

	public void setSubMemberId(String subMemberId) {
		this.subMemberId = subMemberId;
	}

	public String getSubMemberLongname() {
		return subMemberLongname;
	}

	public void setSubMemberLongname(String subMemberLongname) {
		this.subMemberLongname = subMemberLongname;
	}

	public String getRelaType() {
		return relaType;
	}

	public void setRelaType(String relaType) {
		this.relaType = relaType;
	}

	public String getSubRelaType() {
		return subRelaType;
	}

	public void setSubRelaType(String subRelaType) {
		this.subRelaType = subRelaType;
	}

	public Date getGroundTime() {
		return groundTime;
	}

	public void setGroundTime(Date groundTime) {
		this.groundTime = groundTime;
	}
}
