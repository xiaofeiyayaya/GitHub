package com.cn.jxf.mapper.student;

import com.cn.jxf.domain.student.Student;
import com.cn.jxf.mapper.BaseMapper;

/**
 * Student的Mapper接口
 * 
 * @author 
 *
 */
public interface StudentMapper extends BaseMapper<Student, String>{
	
	Student select(Integer id);
	
	void delete(Integer id);
	
}