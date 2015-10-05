package com.xiaoy.util.excel.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

/**
 * poi Excel导入数据库
 * 
 * @author XiaoY
 * @date: 2015年10月5日 下午10:24:42
 */
public class PoiExcelFileExportToDB {
	/**
	 * 工作薄对象
	 */
	private Workbook wb;

	/**
	 * 工作表对象
	 */
	private Sheet sheet;

	/**
	 * 标题行号
	 */
	private int headerNum;

	/**
	 * 构造函数
	 * 
	 * @param path
	 *            导入文件，读取第一个工作表
	 * @param headerNum
	 *            标题行号，数据行号=标题行号+1
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	public PoiExcelFileExportToDB(String fileName, int headerNum) throws InvalidFormatException, IOException {
		this(new File(fileName), headerNum);
	}

	/**
	 * 构造函数
	 * 
	 * @param path
	 *            导入文件对象，读取第一个工作表
	 * @param headerNum
	 *            标题行号，数据行号=标题行号+1
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	public PoiExcelFileExportToDB(File file, int headerNum) throws InvalidFormatException, IOException {
		this(file, headerNum, 0);
	}

	/**
	 * 构造函数
	 * 
	 * @param path
	 *            导入文件
	 * @param headerNum
	 *            标题行号，数据行号=标题行号+1
	 * @param sheetIndex
	 *            工作表编号
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	public PoiExcelFileExportToDB(String fileName, int headerNum, int sheetIndex) throws InvalidFormatException, IOException {
		this(new File(fileName), headerNum, sheetIndex);
	}

	/**
	 * 构造函数
	 * 
	 * @param path
	 *            导入文件对象
	 * @param headerNum
	 *            标题行号，数据行号=标题行号+1
	 * @param sheetIndex
	 *            工作表编号
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	public PoiExcelFileExportToDB(File file, int headerNum, int sheetIndex) throws InvalidFormatException, IOException {
		this(file.getName(), new FileInputStream(file), headerNum, sheetIndex);
	}

	/**
	 * 构造函数
	 * 
	 * @param file
	 *            导入文件对象
	 * @param headerNum
	 *            标题行号，数据行号=标题行号+1
	 * @param sheetIndex
	 *            工作表编号
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	public PoiExcelFileExportToDB(MultipartFile multipartFile, int headerNum, int sheetIndex) throws InvalidFormatException, IOException {
		this(multipartFile.getOriginalFilename(), multipartFile.getInputStream(), headerNum, sheetIndex);
	}

	/**
	 * 构造函数
	 * 
	 * @param path
	 *            导入文件对象
	 * @param headerNum
	 *            标题行号，数据行号=标题行号+1
	 * @param sheetIndex
	 *            工作表编号
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	public PoiExcelFileExportToDB(String fileName, InputStream is, int headerNum, int sheetIndex) throws InvalidFormatException, IOException {
		if (StringUtils.isBlank(fileName)) {
			throw new RuntimeException("导入文档为空!");
		} else if (fileName.toLowerCase().endsWith("xls")) {
			this.wb = new HSSFWorkbook(is);
		} else if (fileName.toLowerCase().endsWith("xlsx")) {
			this.wb = new XSSFWorkbook(is);
		} else {
			throw new RuntimeException("文档格式不正确!");
		}
		if (this.wb.getNumberOfSheets() < sheetIndex) {
			throw new RuntimeException("文档中没有工作表!");
		}
		this.sheet = this.wb.getSheetAt(sheetIndex);
		this.headerNum = headerNum;
	}

	/**
	 * 获取导入数据列表
	 * 
	 * @param cls
	 *            导入对象类型
	 * @param groups
	 *            导入分组
	 */
	public List<Object[]> getDataList() throws InstantiationException, IllegalAccessException {
		List<Object[]> dataList = new ArrayList<Object[]>();
		// 获取数据行号
		int rowNum = this.getDataRowNum();
		System.out.println("数据行号:" + rowNum);
		// 获取最后一个数据行号
		int lastDataRowNum = this.getLastDataRowNum();
		System.out.println("最后一个数据行号:" + lastDataRowNum);
		// 获取最后一个列号
		int lastCellNum = this.getLastCellNum();
		System.out.println("获取最后一个列号:" + lastCellNum);
		for (int i = rowNum; i < lastDataRowNum; i++) {
			Row row = this.getRow(i);
			Object[] obj = new Object[lastCellNum];
			for (int j = 0; j < lastCellNum; j++) {
				Object val = this.getCellValue(row, j);
				obj[j] = val;
			}
			dataList.add(obj);
		}
		System.out.println("获取导入数据列表集");
		return dataList;
	}

	/**
	 * 获取单元格值
	 * 
	 * @param row
	 *            获取的行
	 * @param column
	 *            获取单元格列号
	 * @return 单元格值
	 */
	private Object getCellValue(Row row, int column) {
		Object val = "";
		try {
			Cell cell = row.getCell(column);
			if (cell != null) {
				if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					val = cell.getNumericCellValue();
				} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
					val = cell.getStringCellValue();
				} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
					val = cell.getCellFormula();
				} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
					val = cell.getBooleanCellValue();
				} else if (cell.getCellType() == Cell.CELL_TYPE_ERROR) {
					val = cell.getErrorCellValue();
				}
			}
		} catch (Exception e) {
			return val;
		}
		return val;
	}

	/**
	 * 获取行对象
	 * 
	 * @param rownum
	 * @return
	 */
	private Row getRow(int rownum) {
		return this.sheet.getRow(rownum);
	}

	/**
	 * 获取数据行号
	 * 
	 * @return
	 */
	private int getDataRowNum() {
		return headerNum + 1;
	}

	/**
	 * 获取最后一个数据行号
	 * 
	 * @return
	 */
	private int getLastDataRowNum() {
		return this.sheet.getLastRowNum() + headerNum;
	}

	/**
	 * 获取最后一个列号
	 * 
	 * @return
	 */
	private int getLastCellNum() {
		return this.getRow(headerNum).getLastCellNum();
	}

	// /**
	// * 导入测试
	// */
	// public static void main(String[] args) throws Throwable {
	// PoiExcelFileExportToDB ei = new PoiExcelFileExportToDB("D://全国省市信息.xlsx", 1);
	// List<Object[]> dataList = ei.getDataList();
	// for (Object[] obj : dataList) {
	// for (Object o : obj) {
	// System.out.print(o + ",");
	// }
	// System.out.print("\n");
	// }
	// }
}
