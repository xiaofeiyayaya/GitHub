package com.cn.jxf.service.student;
import java.util.List;

import com.cn.jxf.domain.student.Student;

public interface StudentCacheService {
	
	Student findById(Integer id);
	List<Student> studentList();
}
