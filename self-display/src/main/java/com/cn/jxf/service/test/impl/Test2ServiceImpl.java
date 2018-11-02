package com.cn.jxf.service.test.impl;

import org.springframework.stereotype.Service;

import com.cn.jxf.service.test.TestService;

@Service
public class Test2ServiceImpl implements  TestService{

	@Override
	public String test() {

		System.out.println("2222222");
		
		return "2222";
	}

}
