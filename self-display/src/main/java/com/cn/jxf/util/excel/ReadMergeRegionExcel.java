package com.cn.jxf.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadMergeRegionExcel {

	/**
	 * poi获取合并单元格时，如果是遍历获取合并单元格的所有子单元格的值，office的excel都会有值，wps的excel只会第一个子单元格有值，其他子单元格都没值，
	 * 即cell=null。故意凡是获取合并单元格的值，都获取第一个子单元格的值即可
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		readExcelToObj("C:\\Users\\Administrator\\Desktop\\项目资料\\需求文档\\投资者基本信息（脱敏版）\\投资者基本信息表（新版产品版）.xlsx");
	}

	/**
	 * 读取excel数据
	 * 
	 * @param path
	 * @throws Exception
	 */
	private static void readExcelToObj(String path) {
		File file = new File(path);
		if (file.isFile() && file.exists()) { // 判断文件是否存在

			String[] split = file.getName().split("\\."); // .是特殊字符，需要转义！！！！！
			Workbook wb;
			FileInputStream fis;
			try {
				// 文件流对象
				fis = new FileInputStream(file);
				// 根据文件后缀（xls/xlsx）进行判断
				if ("xls".equals(split[1])) {
					wb = new HSSFWorkbook(fis);
				} else if ("xlsx".equals(split[1])) {
					wb = new XSSFWorkbook(fis);
				} else {
					System.out.println("文件类型错误!");
					return;
				}
				readExcel(wb, 0, 0, 5);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 读取excel文件
	 * 
	 * @param wb
	 * @param sheetIndex
	 *            sheet页下标：从0开始
	 * @param startReadLine
	 *            开始读取的行:从0开始
	 * @param tailLine
	 *            去除最后读取的行
	 */
	private static void readExcel(Workbook wb, int sheetIndex, int startReadLine, int tailLine) {
		Sheet sheet = wb.getSheetAt(sheetIndex);
		Row row = null;
		
		Comment cellComment = sheet.getCellComment(2, 1);
		System.out.println(cellComment);
		
		for (int i = startReadLine; i < sheet.getLastRowNum() - tailLine + 1; i++) {
			row = sheet.getRow(i);
			if (row != null) {
				int firstCellIndex = row.getFirstCellNum();
				int lastCellIndex = row.getLastCellNum();
				for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) { // 遍历列
					Cell cell = row.getCell(cIndex);
					System.out.println(cell.toString());
				}
			}
			/*
			 * for (Cell c : row) { boolean isMerge = isMergedRegion(sheet, i,
			 * c.getColumnIndex()); // 判断是否具有合并单元格 if (isMerge) { String rs =
			 * getMergedRegionValue(sheet, row.getRowNum(), c.getColumnIndex());
			 * System.out.print(rs + " "); } else {
			 * System.out.print(c.getRichStringCellValue() + " "); } }
			 */
			System.out.println();

		}

	}

	/**
	 * 获取合并单元格的值
	 * 
	 * @param sheet
	 * @param row
	 * @param column
	 * @return
	 */
	public static String getMergedRegionValue(Sheet sheet, int row, int column) {
		int sheetMergeCount = sheet.getNumMergedRegions();

		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress ca = sheet.getMergedRegion(i);
			int firstColumn = ca.getFirstColumn();
			int lastColumn = ca.getLastColumn();
			int firstRow = ca.getFirstRow();
			int lastRow = ca.getLastRow();

			if (row >= firstRow && row <= lastRow) {

				if (column >= firstColumn && column <= lastColumn) {
					Row fRow = sheet.getRow(firstRow);
					Cell fCell = fRow.getCell(firstColumn);
					return getCellValue(fCell);
				}
			}
		}

		return null;
	}

	/**
	 * 判断合并了行
	 * 
	 * @param sheet
	 * @param row
	 * @param column
	 * @return
	 */
	private boolean isMergedRow(Sheet sheet, int row, int column) {
		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress range = sheet.getMergedRegion(i);
			int firstColumn = range.getFirstColumn();
			int lastColumn = range.getLastColumn();
			int firstRow = range.getFirstRow();
			int lastRow = range.getLastRow();
			if (row == firstRow && row == lastRow) {
				if (column >= firstColumn && column <= lastColumn) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断指定的单元格是否是合并单元格
	 * 
	 * @param sheet
	 * @param row
	 *            行下标
	 * @param column
	 *            列下标
	 * @return
	 */
	private static boolean isMergedRegion(Sheet sheet, int row, int column) {
		// 得到所有的合并单元格
		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress range = sheet.getMergedRegion(i);
			int firstColumn = range.getFirstColumn();
			int lastColumn = range.getLastColumn();
			int firstRow = range.getFirstRow();
			int lastRow = range.getLastRow();
			if (row >= firstRow && row <= lastRow) {
				if (column >= firstColumn && column <= lastColumn) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断sheet页中是否含有合并单元格
	 * 
	 * @param sheet
	 * @return
	 */
	private boolean hasMerged(Sheet sheet) {
		return sheet.getNumMergedRegions() > 0 ? true : false;
	}

	/**
	 * 合并单元格
	 * 
	 * @param sheet
	 * @param firstRow
	 *            开始行
	 * @param lastRow
	 *            结束行
	 * @param firstCol
	 *            开始列
	 * @param lastCol
	 *            结束列
	 */
	private void mergeRegion(Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol) {
		sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
	}

	/**
	 * 获取单元格的值
	 * 
	 * @param cell
	 * @return
	 */
	public static String getCellValue(Cell cell) {

		if (cell == null)
			return "";

		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {

			return cell.getStringCellValue();

		} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {

			return String.valueOf(cell.getBooleanCellValue());

		} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {

			return cell.getCellFormula();

		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {

			return String.valueOf(cell.getNumericCellValue());

		}
		return "";
	}

	private void decodeMergeCell(HSSFSheet templateSheet) {
		int numMerge = templateSheet.getNumMergedRegions();
		CellRangeAddress region = null;
		while (numMerge > 0) {
			region = templateSheet.getMergedRegion(0);
			if (region.getFirstColumn() == 0 || region.getFirstColumn() == 1) {
				HSSFRow row = templateSheet.getRow(region.getFirstRow());
				HSSFCell cell = row.getCell(region.getFirstColumn());
				String value = cell.getStringCellValue();
				templateSheet.removeMergedRegion(0);
				for (int j = region.getFirstRow(); j <= region.getLastRow(); j++) {
					cell = templateSheet.getRow(j).getCell(region.getFirstColumn());
					cell.setCellValue(value);
				}
			}
			numMerge = templateSheet.getNumMergedRegions();
		}
	}
}
