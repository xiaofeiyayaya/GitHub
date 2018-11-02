package com.cn.jxf.config;

import javax.annotation.Resource;
import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cn.jxf.service.student.StudentService;


@Configuration
public class WebServiceConfig {
	
	@Resource
	private StudentService studentService;

	@Bean(name = Bus.DEFAULT_BUS_ID)
	public SpringBus springBus() {
		return new SpringBus();
	}
	
	@Bean
	public Endpoint userEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(springBus(), studentService);
		endpoint.publish("/user");//接口发布在 /user 目录下
		return endpoint;
	}
  
}
