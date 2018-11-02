package com.cn.jxf.web.test;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.jxf.service.test.TestService;

@Controller
@RequestMapping("/demo")
public class TestController {
	
	@Resource(name = "test2ServiceImpl")
	private TestService testService;
	
	@RequestMapping("/demo")
	@ResponseBody
	public String demo (){
		return testService.test();
	}
}
