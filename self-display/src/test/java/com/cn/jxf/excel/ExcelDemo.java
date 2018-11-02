package com.cn.jxf.excel;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.cn.jxf.Application;
import com.cn.jxf.util.excel.ReadExcel;
import com.cn.jxf.util.excel.WriteExcel;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class ExcelDemo {
	
	@Test
	public void write(){
		String filePath = "D:/Demo.xlsx";
		//String[] header = new String[] { "createTime:日期", "name:名称", "sex:性别", "remark:备注" };
		String headerStr = "书名,作者,男主,女主";
		WriteExcel.write(filePath, headerStr);
	}
	
	@Test
	public void read() {
		// 此处为我创建Excel路径：E:/zhanhj/studysrc/jxl下
		File file = new File("D:/Demo.xlsx");
		ReadExcel.read(file);
	}
}
