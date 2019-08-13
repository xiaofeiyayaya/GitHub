package com.cn.jxf.cxfAndFastJson;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.namespace.QName;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cn.jxf.Application;
import com.cn.jxf.domain.student.Student;
import com.cn.jxf.mapper.student.StudentMapper;
import com.cn.jxf.service.student.StudentService;

import net.minidev.json.JSONArray;
import net.sf.json.JsonConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class StudentTest {
	@Resource
	private StudentService studentService;
	
	@Resource
	private StudentMapper studentMapper;

	@Test
	public void test() {
		
	}

	@Test
	public void cl2() {
		// 创建动态客户端
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient("http://10.189.98.170/ws/info?wsdl");
		QName q = new QName("http://trade.service.counterparty.cm.com/", "getAssetAccount");
		// 需要密码的情况需要加上用户名和密码
		// client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME,
		// PASS_WORD));
		Object[] objects = new Object[0];
		try {
			// invoke('方法名',参数1,参数2,参数3....);
			objects = client.invoke(q,"16429");
			//objects = client.invoke("findByName", "xiaofei");
			String resultJson = JSONObject.toJSONString(objects[0]);
			System.out.println("返回数据:" + resultJson);
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void fastJson(){
		Student student = new Student();
		student.setStuId(111);
		student.setName("2222");
		String string = JSON.toJSONString(student);//java bean 转json字符串
		Object object = JSON.toJSON(student);//java bean 转json对象
		JSONObject object2 = JSON.parseObject(string);//json 字符串转 json对象
		Student student2 = JSON.parseObject(string, Student.class);//json 字符串转 java bean
		System.out.println(string);
		System.out.println(object);
		System.out.println(object2);
		System.out.println(student2);
	}
	
	@Test
	public void demo() throws UnsupportedEncodingException{
		String str = new String("{'code': '200', 'data': [{'entid': 'oVX1vyEuSlA', 'faren': '\u5f20\u7389\u65b0', 'regcap': 300.0, 'esdate': '2003-07-20', 'uniscid': '420000000041184', 'name': '\u4e2d\u56fd\u56fd\u7535\u96c6\u56e2\u516c\u53f8\u534e\u4e2d\u5206\u516c\u53f8'}]}");  
		byte[] bt = str.getBytes("utf-8");    
		String ret = new String(bt, "utf-8");  
		System.out.println(ret); 
		
		String res1 = "\u4e2d\u56fd\u56fd\u7535\u96c6\u56e2\u516c\u53f8\u534e\u4e2d\u5206\u516c\u53f8";
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = res1.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = res1.substring(start + 2, res1.length());
            } else {
                charStr = res1.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(new Character(letter).toString());
            start = end;
        }
        System.out.println(buffer.toString()); 
	}
	
	//反射
	@Test
	public void demo2(){
		
	    //第一种方式获取Class对象    
        Student stu1 = new Student();//这一new 产生一个Student对象，一个Class对象。  
        Class stuClass = stu1.getClass();//获取Class对象  
        System.out.println(stuClass.getName());  
          
		
	    try {  
            Class stuClass3 = Class.forName("com.cn.jxf.domain.student.Student");//注意此字符串必须是真实路径，就是带包名的类路径，包名.类名  
            System.out.println(stuClass3 == stuClass);//判断三种方式是否获取的是同一个Class对象  
            Method[] methods = stuClass3.getMethods();
            for (Method method : methods) {
				System.out.println(method.getName());
			}
        } catch (ClassNotFoundException e) {  
            e.printStackTrace();  
        }  
	}
}
