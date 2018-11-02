package com.cn.jxf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger2Config {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.cn.jxf.web.swagger"))//api接口包扫描路径
				.paths(PathSelectors.any())//可以根据url路径设置哪些请求加入文档，忽略哪些请求
				.build();
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("self-display")//设置文档的标题
				.description("后台接口测试")//设置文档的描述->1.Overview
				.contact(new Contact("ABC Boot", "http://www.abc.comt", ""))//设置文档的联系方式->1.2 Contact information
				.termsOfServiceUrl("http://blog.csdn.net/saytime")//设置文档的License信息->1.3 License information
				.version("1.0")//设置文档的版本信息-> 1.1 Version information
				.build();
	}

}
