package com.cn.jxf.web.student;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.jxf.domain.student.Student;
import com.cn.jxf.service.student.StudentCacheService;
import com.cn.jxf.service.student.StudentService;


@Controller
@RequestMapping("/student")
public class StudentController {
	@Resource(name="studentService")
	private StudentService studentService;
	
	@Resource(name="studentCacheService")
	private StudentCacheService studentCache;
	
	@RequestMapping("/findById")
	@ResponseBody
	public Student findById(Integer id){
		return studentCache.findById(id);
	}
	@RequestMapping("/list")
	public String list(){
		List<Student> studentList = studentService.studentList();
		System.out.println(studentList.size());
		//List<Student> studentList2 = studentCache.studentList();
		//System.out.println(studentList2.size());
		return null;
	}
	
	@RequestMapping("/testList")
	@ResponseBody
	public Student testList(Integer id){
		Student findById = studentService.findById(1);
		List<Student> studentList = studentCache.studentList();
		//System.out.println(findById);
		return findById;
	}
}
