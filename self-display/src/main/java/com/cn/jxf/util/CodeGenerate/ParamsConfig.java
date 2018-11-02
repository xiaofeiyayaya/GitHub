package com.cn.jxf.util.CodeGenerate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config/param.properties")//加载一个属性文件
public class ParamsConfig {
		
	@Value("${my.name}")
	private String myName;

	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}
	
}
