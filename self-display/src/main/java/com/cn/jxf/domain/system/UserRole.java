package com.cn.jxf.domain.system;

import com.cn.jxf.domain.BaseEntity;

public class UserRole extends BaseEntity {

	private static final long serialVersionUID = -3095274735275579226L;
	private String userId;
	private String roleId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
}
