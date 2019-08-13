package com.cn.jxf.domain.user;

import com.cn.jxf.domain.BaseEntity;

public class User extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer userId;
	
	private String userName;
	
	private String userCode;
	
	private String passWord;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	
}
