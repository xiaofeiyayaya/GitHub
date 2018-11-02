package com.cn.jxf.domain;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity implements Serializable{
	private static final long serialVersionUID = 1195957903922350193L;
	private String id;
	private String updateId;
	private Date updateTime;
	private int flag;
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getUpdateId() {
		return updateId;
	}
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
