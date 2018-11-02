package com.cn.jxf.service.test.impl;

import org.springframework.stereotype.Service;

import com.cn.jxf.service.test.TestService;

@Service
public class Test1ServiceImpl implements TestService{

	@Override
	public String test() {
		System.out.println("11111");// TODO Auto-generated method stub
		return "1111111";
	}

}
