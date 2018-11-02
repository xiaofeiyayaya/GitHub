package com.cn.jxf;



import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
@MapperScan("com.cn.jxf.mapper")
@ServletComponentScan
@SpringBootApplication
@EnableScheduling
@EnableSwagger2
//加载配置文件
//@PropertySource(value = "classpath:config/param.properties")
//springboot1.5之前的版本这么用，1.5版本之后取消了locations属性，替代方案看最下面。
//@ConfigurationProperties(prefix = "auth",locations = "classpath:/config/ehcache.xml") 
	
/*	@Bean
	public MultipartConfigElement multipartConfig() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize("10MB");
		factory.setMaxRequestSize("10MB");
		return factory.createMultipartConfig();
	}*/
	
public class Application {	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
