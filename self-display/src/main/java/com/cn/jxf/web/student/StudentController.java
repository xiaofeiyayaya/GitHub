package com.cn.jxf.web.student;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cn.jxf.config.Constants;
import com.cn.jxf.service.student.StudentService;
import com.cn.jxf.domain.student.Student;
import com.cn.jxf.domain.system.Page;

/**
 * Student的路由接口服务
 * 
 * @author 
 *
 */
@Controller
@RequestMapping("/student")
public class StudentController {

	@Resource(name = "studentService")
	private StudentService studentService;
	
	@RequestMapping("/studentList")
	public String studentList() {
		
		return "/student/studentList";
	}
	
	@RequestMapping("/studentPage")
	@ResponseBody
	public Page<Student> studentPage(Student student, 
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "limit", required = false, defaultValue = Constants.PAGE_SIZE) String limit) throws UnsupportedEncodingException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotBlank("")) {
			map.put("", URLDecoder.decode("", "utf-8"));
		}

		map.put("orderby", "id");
		map.put("order", "asc");
		
		map.put("page", page);
		map.put("limit", limit);
		
		Page<Student> studentPage = studentService.list(map);
		return studentPage;
	}
	
	@RequestMapping("/toAddstudent")
	public String toAddstudent() {
		return "/student/addStudent";
	}

	@RequestMapping("/addStudent")
	@ResponseBody
	public String addStudent(Student student){
		try {
			Student stu = new Student();
			stu.setName("小飞2");
			studentService.insert(stu);
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"result\":\"false\"}";
		}
		return "{\"result\":\"true\"}";
	}
	
	@RequestMapping("/toUpdatestudent")
	public String toUpdatestudent() {
		return "/student/editStudent";
	}

	@RequestMapping("/updateStudent")
	@ResponseBody
	public String updateStudent(Student student){
		try {
			
			Student select = studentService.select(3);
			select.setName("小飞3");
			studentService.update(select);
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"result\":\"false\"}";
		}
		return "{\"result\":\"true\"}";
	}
	
	@RequestMapping("/deleteStudent")
	@ResponseBody
	public String deleteStudent(Integer stuId){
		try {
			studentService.delete(2);
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"result\":\"false\"}";
		}
		return "{\"result\":\"true\"}";
	}
}
