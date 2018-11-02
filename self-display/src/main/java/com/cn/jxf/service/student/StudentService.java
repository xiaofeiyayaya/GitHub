package com.cn.jxf.service.student;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.cn.jxf.domain.student.Student;
@WebService
public interface StudentService {
	@WebMethod
	Student findById(@WebParam(name = "id") Integer id);
	
	@WebMethod
	Student findByName(@WebParam(name = "name") String name);
	
	List<Student> studentList();
}
