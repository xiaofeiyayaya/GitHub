package com.cn.jxf.service.student.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cn.jxf.domain.student.Student;
import com.cn.jxf.domain.system.Page;
import com.cn.jxf.mapper.student.StudentMapper;
import com.cn.jxf.service.student.StudentService;
import com.cn.jxf.util.PageUtil;

import javax.annotation.Resource;


/**
 * Student的服务接口的实现类
 * 
 * @author 
 *
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {

	@Resource(name = "studentMapper")
	private StudentMapper studentMapper;
	
	@Override
	public Page<Student> list(Map<String, Object> map) {
		PageUtil.setStartEnd(map);
		List<Student> list = studentMapper.list(map);
		Page<Student> page = new Page<Student>();
		PageUtil.getPageFromMap(page, map);
		page.setData(list);
		int totalCount = studentMapper.count(map);
		page.setCount(totalCount);
		return page;
	}
	
	@Override
	public Student select(Integer id){
		return studentMapper.select(id);
	}
	
	@Override
	public void insert(Student student) {
		studentMapper.insert(student);
	}

	@Override
	public void update(Student student) {
		studentMapper.update(student);
	}
	
	@Override
	public void delete(Integer id) {
		studentMapper.delete(id);
	}

}