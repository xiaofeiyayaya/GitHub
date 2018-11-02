package com.cn.jxf.domain;

import java.io.Serializable;

public class JsonResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String status;

	private Object data;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
