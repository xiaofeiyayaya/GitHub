package com.cn.jxf.web.swagger;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cn.jxf.domain.student.Student;
import com.cn.jxf.service.student.StudentService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/student")
public class UserRestFulController {

	@Resource
	private StudentService studentService;

	/**
	 * 根据ID查询用户
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path")
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public Student getUserById(@PathVariable(value = "id") Integer id) {
		// JsonResult r = new JsonResult();
		Student student = new Student();
		try {
			student = studentService.findById(id);
			// r.setResult(user);
			// r.setStatus("ok");
		} catch (Exception e) {
			// r.setResult(e.getClass().getName() + ":" + e.getMessage());
			// r.setStatus("error");
			e.printStackTrace();
		}
		return student;
	}

}
