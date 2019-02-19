package com.cn.jxf.util.excel;

import java.io.File;  
import java.text.DecimalFormat;  
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.ss.usermodel.Sheet;  
import org.apache.poi.ss.usermodel.Workbook;  
import org.apache.poi.ss.usermodel.WorkbookFactory;  
public class ExcelUtil {
	public static Workbook getWb(String path) {
		try {
			return WorkbookFactory.create(new File(path));
		} catch (Exception e) {
			throw new RuntimeException("读取EXCEL文件出错", e);
		}
	}

	public static Sheet getSheet(Workbook wb, int sheetIndex) {
		if (wb == null) {
			throw new RuntimeException("工作簿对象为空");
		}
		int sheetSize = wb.getNumberOfSheets();
		if (sheetIndex < 0 || sheetIndex > sheetSize - 1) {
			throw new RuntimeException("工作表获取错误");
		}
		return wb.getSheetAt(sheetIndex);
	}

	public static List<List<String>> getExcelRows(Sheet sheet, int startLine, int endLine) {
		List<List<String>> list = new ArrayList<List<String>>();
		// 如果开始行号和结束行号都是-1的话，则全表读取
		if (startLine == -1)
			startLine = 0;
		if (endLine == -1) {
			endLine = sheet.getLastRowNum() + 1;
		} else {
			endLine += 1;
		}
		List<String> keyList = new ArrayList<String>();
		List<String> valueList = new ArrayList<String>();
		for (int i = startLine; i < endLine; i++) {
			Row row = sheet.getRow(i);
			if (row == null) { // 该行为空，直接跳过
				continue;
			}
			int rowSize = row.getLastCellNum();
			List<String> rowList = new ArrayList<String>();
			Map<String, String> map = new HashMap<>();
			for (int j = 1; j < rowSize; j++) {
				Cell cell = row.getCell(j);
				if(j==1||j==5){
					keyList.add(cell.toString());	
				}
				if(j==2||j==6){
					valueList.add(cell.toString());
				}
			}
		}
		for (String key : keyList) {
			System.out.println("key:"+key);
		}
		
		for (String value : valueList) {
			System.out.println("value:"+value);
		}
		return list;
	}

	public static void main(String a[]) {
		String path = "C:\\Users\\Administrator\\Desktop\\项目资料\\需求文档\\投资者基本信息（脱敏版）\\投资者基本信息表（新版产品版）.xlsx";
		Workbook wb = getWb(path);
		List<List<String>> list = getExcelRows(getSheet(wb, 0), 2, 9);
		/*for (int i = 0; i < list.size(); i++) {
			List<String> row = list.get(i);
			for (int j = 0; j < row.size(); j++) {
				System.out.print(row.get(j) + "\t");
			}
			System.out.println();
		}*/
	}
}
