package com.cn.jxf.service.student.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebService;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cn.jxf.domain.student.Student;
import com.cn.jxf.mapper.student.StudentMapper;
import com.cn.jxf.service.student.StudentService;

@Service("studentService")
@WebService(serviceName = "studentService"//服务名
,targetNamespace = "http://student.service.jxf.cn.com/"//报名倒叙，并且和接口定义保持一致
,endpointInterface = "com.cn.jxf.service.student.StudentService")//包名
public class StudentServiceImpl implements StudentService{
	
	@Resource(name="studentMapper")
	private StudentMapper studentMapper;
	

	
	@Cacheable(value = "student", key = "#id")
	public Student findById(Integer id) {
		Student student = studentMapper.selectByPrimaryKey(id);
		return student;
	}

	@Override
	public Student findByName(String name) {
		Student student = studentMapper.findByName(name);
		return student;
	}

	@CachePut(value = "student")
	public List<Student> studentList() {
		List<Student> list = studentMapper.list();
		return list;
	}

}
