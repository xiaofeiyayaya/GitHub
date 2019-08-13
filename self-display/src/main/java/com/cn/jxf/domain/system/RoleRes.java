package com.cn.jxf.domain.system;

import com.cn.jxf.domain.BaseEntity;

public class RoleRes extends BaseEntity {

	private static final long serialVersionUID = 4846568592965730162L;
	private String resId;
	private String roleId;

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}
