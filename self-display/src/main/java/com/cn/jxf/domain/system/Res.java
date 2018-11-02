package com.cn.jxf.domain.system;

import java.io.Serializable;

public class Res implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer resId;// 主键.
	
	private String resName;// 名称.
	
	private String resType;// 资源类型，[menu|button]
	
	private String resUrl;// 资源路径.
	
	private String resLogo; // 权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view
	
	private Long parentId; // 父编号
	
	private Integer resLever;//级别
	
	private Integer resOrder;//排序

	public Integer getResId() {
		return resId;
	}

	public void setResId(Integer resId) {
		this.resId = resId;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}

	public String getResUrl() {
		return resUrl;
	}

	public void setResUrl(String resUrl) {
		this.resUrl = resUrl;
	}

	public String getResLogo() {
		return resLogo;
	}

	public void setResLogo(String resLogo) {
		this.resLogo = resLogo;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getResLever() {
		return resLever;
	}

	public void setResLever(Integer resLever) {
		this.resLever = resLever;
	}

	public Integer getResOrder() {
		return resOrder;
	}

	public void setResOrder(Integer resOrder) {
		this.resOrder = resOrder;
	}
	
}
