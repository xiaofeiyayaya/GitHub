package com.cn.jxf;

import javax.xml.namespace.QName;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import net.sf.json.JSONObject;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class Demo {

	@Test
	public void aaa() {
		Integer a = 128;
		Integer b = 128;
		System.out.println("---------------------");
		System.out.print(a == b); // false

		Integer c = 127;
		Integer d = 127;
		System.out.print(c == d); // true
	}
	
	@Test
	public void cl2() {
		// 创建动态客户端
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		// url为调用webService的wsdl地址
		Client client = dcf.createClient("http://10.176.168.171/ws/gs?wsdl");
		// namespace是命名空间，methodName是方法名
		QName q = new QName("http://gs.service.gs.cm.com/", "getTradeName");
		Object[] objects;
		try {
			// paramvalue为参数值
			objects = client.invoke(q,"911101080628135175");
			String resultJson = JSONObject.fromObject(objects[0]).toString();
			System.out.println("返回数据:" + resultJson);
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void cl3() {
		// 创建动态客户端
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		// url为调用webService的wsdl地址
		Client client = dcf.createClient("http://10.189.98.170/ws/xp?wsdl");
		// namespace是命名空间，methodName是方法名
		QName q = new QName("http://trade.service.counterparty.cm.com/", "addCounterparty");
		Object[] objects;
		String name ="Wells Fargo & Company";
		try {
			// paramvalue为参数值
			objects = client.invoke(q,"<![CDATA["+name+"]]>","i","PBLD0EJDB5FWOLXP3B76");
			String resultJson = JSONObject.fromObject(objects[0]).toString();
			System.out.println("返回数据:" + resultJson);
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}
	}
	

}
