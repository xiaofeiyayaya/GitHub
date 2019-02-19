package com.cn.jxf.service.student;
import java.util.Map;


import com.cn.jxf.domain.student.Student;
import com.cn.jxf.domain.system.Page;

/**
 * Student的服务接口
 * 
 * @author 
 *
 */
public interface StudentService {


	Student select(Integer id);
	
	Page<Student> list(Map<String, Object> map);
	
	void insert(Student student);
	
	void update(Student student);
	
	void delete(Integer id);
	
}
