package com.cn.jxf.dom4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.cn.jxf.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class Dom4j {

	@Test
	public void dom4j() {

		String xmlStr = "<response><meta><service>saic_fuzzy</service><code>100</code><message>OK</message></meta><data><item>小米科技有限责任公司</item><item>广东小米科技有限责任公司</item><item>小米科技有限责任公司厦门分公司</item><item>小米科技有限责任公司成都分公司</item><item>小米科技有限责任公司郑州分公司</item><item>小米科技有限责任公司武汉分公司</item><item>小米科技有限责任公司广州分公司</item><item>小米科技有限责任公司重庆分公司</item><item>小米科技有限责任公司杭州分公司</item><item>小米科技有限责任公司济南分公司</item></data></response>";
		List<HashMap<String, String>> personList = new ArrayList<HashMap<String, String>>();
		try {
			Document document = DocumentHelper.parseText(xmlStr);

			Element rootElement = document.getRootElement();// 获取根节点
			for (Iterator<?> iterator = rootElement.elementIterator(); iterator.hasNext();) {
				Element element = (Element) iterator.next();

				HashMap<String, String> personMap = new HashMap<String, String>();

				Attribute genderAttr = element.attribute("item");// 获取元素的gender属性对象
				personMap.put("item", StringUtils.trim(genderAttr.getValue()));// 获取属性对象的值并插入map中，键名为gender

				for (Iterator<?> iterator2 = element.elementIterator(); iterator2.hasNext();) {
					Element element2 = (Element) iterator2.next();

					personMap.put(element2.getName(), element2.getTextTrim());// 将子节点的名称和值分别插入map中
				}
				personList.add(personMap);// 将map插入list中
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void dom4jDemo() {
		try {
			// File inputFile = new File("input.txt");
			// SAXReader reader = new SAXReader();
			// Document document = reader.read( inputFile );
			String xmlStr = "<response><meta><service>saic_fuzzy</service><code>100</code><message>OK</message></meta><data><item>小米科技有限责任公司</item><item>广东小米科技有限责任公司</item><item>小米科技有限责任公司厦门分公司</item><item>小米科技有限责任公司成都分公司</item><item>小米科技有限责任公司郑州分公司</item><item>小米科技有限责任公司武汉分公司</item><item>小米科技有限责任公司广州分公司</item><item>小米科技有限责任公司重庆分公司</item><item>小米科技有限责任公司杭州分公司</item><item>小米科技有限责任公司济南分公司</item></data></response>";
			Document document = DocumentHelper.parseText(xmlStr);

			System.out.println("Root element :" + document.getRootElement().getName());

			//Element classElement = document.getRootElement();

			@SuppressWarnings("unchecked")
			List<Node> nodes = document.selectNodes("/response/data");
			System.out.println("----------------------------");
			for (Node node : nodes) {
				System.out.println("\nCurrent Element :" + node.getName());
			
				System.out.println("item : " + node.selectSingleNode("item").getText());
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
