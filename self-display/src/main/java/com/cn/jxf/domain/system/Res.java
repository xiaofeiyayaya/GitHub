package com.cn.jxf.domain.system;

import java.util.List;

import com.cn.jxf.domain.BaseEntity;

public class Res extends BaseEntity {

	private static final long serialVersionUID = 3933733800364686524L;
	private int resId;
	private String resName;
	private int resType;
	private String url;
	private String resLogo;
	private String parentId;
	private int resOrder;
	private int resLevel;
	private String isSys;
	private boolean check;
	private List<Res> ress;

	public List<Res> getRess() {
		return ress;
	}

	public void setRess(List<Res> ress) {
		this.ress = ress;
	}

	public String getId() {
		return String.valueOf(this.resId);
	}

	public int getResId() {
		return resId;
	}

	public void setResId(int resId) {
		this.resId = resId;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public int getResType() {
		return resType;
	}

	public void setResType(int resType) {
		this.resType = resType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getResLogo() {
		return resLogo;
	}

	public void setResLogo(String resLogo) {
		this.resLogo = resLogo;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public int getResOrder() {
		return resOrder;
	}

	public void setResOrder(int resOrder) {
		this.resOrder = resOrder;
	}

	public int getResLevel() {
		return resLevel;
	}

	public void setResLevel(int resLevel) {
		this.resLevel = resLevel;
	}

	public String getIsSys() {
		return isSys;
	}

	public void setIsSys(String isSys) {
		this.isSys = isSys;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

}
