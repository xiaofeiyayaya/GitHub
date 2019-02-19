package com.cn.jxf.javaCSV;

import java.io.IOException;
import java.nio.charset.Charset;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.cn.jxf.Application;
import com.cn.jxf.domain.student.Student;
import com.csvreader.CsvWriter;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class JavaCSV {

	@Test
	public void write() {
		Student stu = new Student();
		stu.setStuId(111);
		String ShortName = null;
		if(stu.getId() == null){
			ShortName = "";
		}else{
			ShortName = stu.getId().toString();
		}
		String filePath = "d:/上传/test4.csv";
		try {
			// 创建CSV写对象
			CsvWriter csvWriter = new CsvWriter(filePath, ',', Charset.forName("UTF-8"));
			// CsvWriter csvWriter = new CsvWriter(filePath,',',
			// Charset.forName("UTF-8"));
			// CsvWriter csvWriter = new CsvWriter(filePath);

			// 写表头
			String[] headers = { "Action", "LongName", "ShortName" , "Parent", "Country", "ExternalReference",
					"HolidayCode", "Financial", "Status", "Roles.Role", "TripartySubstitution", 
					"Attribute.Role", "Attribute.ProcessingOrg", "Attribute.AttributeName", "Attribute.AttributeValue",
					"Attribute.Role", "Attribute.ProcessingOrg", "Attribute.AttributeName", "Attribute.AttributeValue",
					"Attribute.Role", "Attribute.ProcessingOrg", "Attribute.AttributeName", "Attribute.AttributeValue"};
			String[] content = { "1", stu.getName(), ShortName,"4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
			String[] content2 = { "1", "2", "3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
			csvWriter.writeRecord(headers);
			csvWriter.writeRecord(content);
			csvWriter.writeRecord(content2);
			csvWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
