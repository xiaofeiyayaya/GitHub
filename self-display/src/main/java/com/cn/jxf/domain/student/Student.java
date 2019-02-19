package com.cn.jxf.domain.student;

import com.cn.jxf.domain.BaseEntity;

/**
 * student实体类
 * 
 * @author 
 *
 */
public class Student extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//
	private Integer stuId; 
	//姓名
	private String name; 
	//性别，女为0，男为1
	private Integer sex; 
	//备注
	private String note; 
	
	
	public Integer getStuId() {
		return stuId;
	}

	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
