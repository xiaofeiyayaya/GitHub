package com.cn.jxf.netJson;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.cn.jxf.Application;
import com.cn.jxf.domain.student.Student;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class NetJson {
	
	@Test
	public void netJson(){
		Student student = new Student();
		student.setStuId(111);
		student.setName("2222");
		
		List<Student> list = new ArrayList<>();
		list.add(student);
		
		
		JSONObject jsonObject = JSONObject.fromObject(student); //javabean 转json对象
		String string = jsonObject.toString(); //json对象转 json字符串
		JSONObject jsonObject2 = JSONObject.fromObject(string); //json字符串转json对象
		String id = jsonObject2.getString("id"); //json对象根据key获取value
		JSONArray jsonArray = JSONArray.fromObject(list); //list转json
		//json转list
		@SuppressWarnings("unchecked")
		List<Student> list3 = (List<Student>) JSONArray.toList(jsonArray, new Student(), new JsonConfig());
		
		
		List<Student> list4 = new ArrayList<>();
		JSONArray fromObject = JSONArray.fromObject(list4);
		
		System.out.println(fromObject.toString());
		System.out.println(jsonObject);
		System.out.println(string);
		System.out.println(jsonObject2);
		System.out.println(id);
		System.out.println(jsonArray);
		System.out.println(list3.get(0).getName());
	}

}
