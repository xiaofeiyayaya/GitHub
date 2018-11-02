package com.cn.jxf.util.excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcel {

	private static final String EXCEL_XLS = "xls";
	private static final String EXCEL_XLSX = "xlsx";

	/**
	 * 判断Excel的版本,获取Workbook
	 * 
	 * @param in
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static Workbook getWorkbok(String filePath){
		Workbook book = null;
		if (filePath.endsWith(EXCEL_XLS)) { // Excel&nbsp;2003
			book = new HSSFWorkbook();
		} else if (filePath.endsWith(EXCEL_XLSX)) { // Excel 2007/2010
			book = new XSSFWorkbook();
		}
		return book;
	}

	public static void write(String filePath,String headerStr) {
		
		//判断Excel的版本
		Workbook book = getWorkbok(filePath);
		// 添加一个sheet
		Sheet sheet = book.createSheet("Sheet2");
		// 定义要导出的列名集合
		Set<String> columns = new HashSet<String>();

		// 设置单元格背景色
		CellStyle cellStyle = book.createCellStyle();
		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cellStyle.setFillForegroundColor(new HSSFColor.YELLOW().getIndex());

		// 生成表头
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellStyle(cellStyle);
		//cell.setCellValue("序号");
		// 列号从1开始
		int n = 0;
		// 解析头字符串
		/*for (String str : header) {
			String[] arr = str.split(":");
			columns.add(n + "," + arr[0]);// 添加要导出的字段名并且与列号n绑定
			cell = row.createCell(n);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(arr[1]);
			n++;
		}*/
		String[] arr = headerStr.split(",");
		for (String string : arr) {
			columns.add(n + "," + string);// 添加要导出的字段名并且与列号n绑定
			cell = row.createCell(n);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(string);
			n++;
		}
		try  
        {  
            FileOutputStream fout = new FileOutputStream(filePath);  
            book.write(fout);  
            fout.close();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
	}

}
