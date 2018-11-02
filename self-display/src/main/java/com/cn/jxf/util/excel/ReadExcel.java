package com.cn.jxf.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.cn.jxf.domain.excel.Excel;
import com.cn.jxf.mapper.excel.ExcelMapper;

import net.sf.json.JSONObject;
@Component
public class ReadExcel {

	@Resource
	private ExcelMapper excelMapper;

	private static ReadExcel readExcel;

	@PostConstruct
	public void init() {
		readExcel = this;
		readExcel.excelMapper = this.excelMapper;
	}

	public static void read(File excel) {
		// excel文件路径
		// String excelPath = file.getAbsolutePath();
		Excel obj = readExcel.excelMapper.selectById(1);

		JSONObject jasonObject = JSONObject.fromObject(obj);
		Map<String, String> map = (Map<String, String>) jasonObject;
		try {
			// String encoding = "GBK";
			// File excel = new File(excelPath);
			if (excel.isFile() && excel.exists()) { // 判断文件是否存在

				String[] split = excel.getName().split("\\."); // .是特殊字符，需要转义！！！！！
				Workbook wb;
				FileInputStream fis = new FileInputStream(excel); // 文件流对象
				// 根据文件后缀（xls/xlsx）进行判断
				if ("xls".equals(split[1])) {
					wb = new HSSFWorkbook(fis);
				} else if ("xlsx".equals(split[1])) {
					wb = new XSSFWorkbook(fis);
				} else {
					System.out.println("文件类型错误!");
					return;
				}

				// 开始解析
				Sheet sheet = wb.getSheetAt(0); // 读取sheet 0
				int firstRowIndex = sheet.getFirstRowNum() + 1; // 第一行是列名，所以不读
				// 校验表头
				Row row2 = sheet.getRow(0);
				int firstCellIndex = row2.getFirstCellNum();
				int lastCellIndex = row2.getLastCellNum();
				int flag = 0;
				if (row2 != null) {
					firstCellIndex = row2.getFirstCellNum();
					lastCellIndex = row2.getLastCellNum();
					for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) { // 遍历列
						Cell cell = row2.getCell(cIndex);
						Field[] field = obj.getClass().getDeclaredFields(); // 获取实体类的所有属性，返回Field数组

						for (int j = 1; j < field.length; j++) { // 遍历所有属性
							String name = field[j].getName(); // 获取属性的名字
							String value = map.get(name).toString();
							if (cell.toString().equals(value)) {
								flag++;
							}
						}
					}
					if (flag != lastCellIndex) {
						System.out.println("模板错误!");
						return;
					}
				}
				
				//遍历数据
				int lastRowIndex = sheet.getLastRowNum();
				System.out.println("firstRowIndex: " + firstRowIndex);
				System.out.println("lastRowIndex: " + lastRowIndex);

				for (int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) { // 遍历行
					System.out.println("rIndex: " + rIndex);
					Row row = sheet.getRow(rIndex);
					if (row != null) {
						firstCellIndex = row.getFirstCellNum();
						lastCellIndex = row.getLastCellNum();
						for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) { // 遍历列
							Cell cell = row.getCell(cIndex);
							if (cell != null) {
								System.out.println(cell.toString());
							}
						}
					}
				}
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}