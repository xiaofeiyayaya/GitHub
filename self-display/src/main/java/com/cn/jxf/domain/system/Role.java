package com.cn.jxf.domain.system;

import com.cn.jxf.domain.BaseEntity;

public class Role extends BaseEntity {

	private static final long serialVersionUID = 7746031427397117550L;
	private String roleName;
	private String roleLogo;
	private String description;
	private String isSys;
	
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleLogo() {
		return roleLogo;
	}

	public void setRoleLogo(String roleLogo) {
		this.roleLogo = roleLogo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsSys() {
		return isSys;
	}

	public void setIsSys(String isSys) {
		this.isSys = isSys;
	}

}
