package com.xiaoy.util.excel.poi;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

/**
 * 系统数据导出Excel 生成器
 * 
 * @version 1.0
 * @author XiaoY
 * @date: 2015年10月5日 上午1:13:59
 */
public class PoiDBExportToExcelFile {

	/**
	 * 工作薄对象
	 */
	private SXSSFWorkbook wb;

	/**
	 * 工作表对象
	 */
	private Sheet sheet;

	/**
	 * 样式列表
	 */
	private Map<String, CellStyle> styles;

	/**
	 * 当前行号
	 */
	private int rownum;

	/**
	 * 表格标题，传“空值”，表示无标题
	 */
	private String title;

	/**
	 * 表头
	 */
	private List<String> headerList;

	/**
	 * 表数据
	 */
	private List<List<String>> dataList;

	/**
	 * 构造器
	 * 
	 * @param title
	 *            表格标题，传“空值”，表示无标题
	 * @param headerList
	 *            表头
	 * @param dataList
	 *            表数据
	 */
	public PoiDBExportToExcelFile(String title, List<String> headerList, List<List<String>> dataList) {
		this.title = title;
		this.headerList = headerList;
		this.dataList = dataList;
	}

	/**
	 * 初始化函数<br/>
	 * 创建SXSSFWorkbook
	 * 
	 * @param title
	 *            表格标题，传“空值”，表示无标题
	 * @param headerList
	 *            表头列表
	 */
	private void initialize(String title, List<String> headerList, List<List<String>> dataList) {
		this.wb = new SXSSFWorkbook(500);
		this.sheet = wb.createSheet("Export");
		this.styles = this.createStyles(wb);
		// 创建标题
		if (StringUtils.isNotBlank(title)) {
			// 创建行
			Row titleRow = sheet.createRow(rownum++);
			// 设置行高
			titleRow.setHeightInPoints(30);
			// 创建单元格
			Cell titleCell = titleRow.createCell(0);
			// 设置标题的样式
			titleCell.setCellStyle(styles.get("title"));
			// 设置标题
			titleCell.setCellValue(title);
			// 参数：起始行号，终止行号， 起始列号，终止列号
			CellRangeAddress rangeAddress = new CellRangeAddress(titleRow.getRowNum(), titleRow.getRowNum(), titleRow.getRowNum(), headerList.size() - 1);
			// 单元格合并
			sheet.addMergedRegion(rangeAddress);
		}

		// 判断表头
		if (headerList == null) {
			throw new RuntimeException("headerList not null!");
		}
		// 创建行
		Row headerRow = sheet.createRow(rownum++);
		// 设置行高
		headerRow.setHeightInPoints(16);
		// 创建表头
		for (int i = 0; i < headerList.size(); i++) {
			// 创建单元格
			Cell cell = headerRow.createCell(i);
			// 设置表头样式
			cell.setCellStyle(styles.get("header"));
			String[] ss = StringUtils.split(headerList.get(i), "**", 2);
			if (ss.length == 2) {
				cell.setCellValue(ss[0]);
				Comment comment = this.sheet.createDrawingPatriarch().createCellComment(new XSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 5, 6));
				comment.setString(new XSSFRichTextString(ss[1]));
				cell.setCellComment(comment);
			} else {
				cell.setCellValue(headerList.get(i));
			}
			// 列宽只自动适应
			sheet.autoSizeColumn(i);
		}

		System.out.println("Excel构建完成...");

		// 设置数据列的宽度
		for (int i = 0; i < headerList.size(); i++) {
			int colWidth = sheet.getColumnWidth(i) * 2;
			sheet.setColumnWidth(i, colWidth < 2000 ? 2000 : colWidth);
		}

		// 将数据插入行中
		for (int i = 0; i < dataList.size(); i++) {
			// 添加一行
			Row row = this.addRow();
			for (int j = 0; j < dataList.get(i).size(); j++) {
				// 数据插入到单元格中
				this.addCell(row, j, dataList.get(i).get(j));
			}
		}
		System.out.println("插入数据完成...");
	}

	/**
	 * 添加一行
	 * 
	 * @return 行对象
	 */
	public Row addRow() {
		return sheet.createRow(rownum++);
	}

	/**
	 * 添加一个单元格
	 * 
	 * @param row
	 *            添加的行
	 * @param column
	 *            添加列号
	 * @param val
	 *            添加值
	 * @return 单元格对象
	 */
	public Cell addCell(Row row, int column, Object val) {
		return this.addCell(row, column, val, 0, Class.class);
	}

	/**
	 * 添加一个单元格
	 * 
	 * @param row
	 *            添加的行
	 * @param column
	 *            添加列号
	 * @param val
	 *            添加值
	 * @param align
	 *            对齐方式（1：靠左；2：居中；3：靠右）
	 * @return 单元格对象
	 */
	private Cell addCell(Row row, int column, Object val, int align, Class<?> fieldType) {
		// 创建一个单元格
		Cell cell = row.createCell(column);
		// 添加单元格样式
		CellStyle style = styles.get("data" + (align >= 1 && align <= 3 ? align : ""));
		try {
			if (val == null) {
				cell.setCellValue("");
			} else if (val instanceof String) {
				cell.setCellValue((String) val);
			} else if (val instanceof Integer) {
				cell.setCellValue((Integer) val);
			} else if (val instanceof Long) {
				cell.setCellValue((Long) val);
			} else if (val instanceof Double) {
				cell.setCellValue((Double) val);
			} else if (val instanceof Float) {
				cell.setCellValue((Float) val);
			} else if (val instanceof Date) {
				DataFormat format = wb.createDataFormat();
				style.setDataFormat(format.getFormat("yyyy-MM-dd"));
				cell.setCellValue((Date) val);
			} else {
				if (fieldType != Class.class) {
					cell.setCellValue((String) fieldType.getMethod("setValue", Object.class).invoke(null, val));
				} else {
					cell.setCellValue((String) Class
							.forName(
									this.getClass().getName()
											.replaceAll(this.getClass().getSimpleName(), "fieldtype." + val.getClass().getSimpleName() + "Type"))
							.getMethod("setValue", Object.class).invoke(null, val));
				}
			}
		} catch (Exception ex) {
			cell.setCellValue(val.toString());
		}
		cell.setCellStyle(style);
		return cell;
	}

	/**
	 * 创建表格样式
	 * 
	 * @param wb
	 *            工作薄对象
	 * @return 样式列表
	 */
	private Map<String, CellStyle> createStyles(Workbook wb) {
		Map<String, CellStyle> styles = new HashMap<String, CellStyle>();

		// 设置标题样式
		CellStyle style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		Font titleFont = wb.createFont();
		titleFont.setFontName("Arial");
		titleFont.setFontHeightInPoints((short) 16);
		titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style.setFont(titleFont);
		styles.put("title", style);

		// 设置数据样式
		style = wb.createCellStyle();
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		Font dataFont = wb.createFont();
		dataFont.setFontName("Arial");
		dataFont.setFontHeightInPoints((short) 10);
		style.setFont(dataFont);
		styles.put("data", style);

		// 设置数据样式1
		style = wb.createCellStyle();
		style.cloneStyleFrom(styles.get("data"));
		style.setAlignment(CellStyle.ALIGN_LEFT);
		styles.put("data1", style);

		// 设置数据样式2
		style = wb.createCellStyle();
		style.cloneStyleFrom(styles.get("data"));
		style.setAlignment(CellStyle.ALIGN_CENTER);
		styles.put("data2", style);

		// 设置数据样式3
		style = wb.createCellStyle();
		style.cloneStyleFrom(styles.get("data"));
		style.setAlignment(CellStyle.ALIGN_RIGHT);
		styles.put("data3", style);

		// 设置表头样式
		style = wb.createCellStyle();
		style.cloneStyleFrom(styles.get("data"));
		// style.setWrapText(true);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		Font headerFont = wb.createFont();
		headerFont.setFontName("Arial");
		headerFont.setFontHeightInPoints((short) 10);
		headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		headerFont.setColor(IndexedColors.WHITE.getIndex());
		style.setFont(headerFont);
		styles.put("header", style);

		return styles;
	}

	/**
	 * 导出数据到指定Excle文件
	 * 
	 * @param os
	 * @throws IOException
	 * @date 2015年10月5日 上午1:19:42
	 */
	public void expordExcel(OutputStream os) throws IOException {
		this.initialize(title, headerList, dataList);
		try {
			wb.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				os.flush();
				os.close();
			}
		}
		System.out.println("Excel导出完成...");
	}

	/**
	 * 导出数据到客户端
	 * 
	 * @param response
	 * @throws IOException
	 * @date 2015年10月5日 上午1:18:22
	 */
	public void expordExcel(HttpServletResponse response, String fileName) throws IOException {
		// 重置输出流
		response.reset();
		// 设置导出的格式
		response.setContentType("application/octet-stream; charset=utf-8");
		// 设定输出文件头
		response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
		ServletOutputStream os = response.getOutputStream();
		this.initialize(title, headerList, dataList);
		try {
			wb.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				os.flush();
				os.close();
			}
		}
		System.out.println("Excel导出完成...");
	}

	/**
	 * 导出测试
	 */
	public static void main(String[] args) throws Throwable {

		List<String> headerList = new ArrayList<String>();
		for (int i = 1; i <= 10; i++) {
			headerList.add("表头" + i);
		}

		List<String> dataRowList = new ArrayList<String>();
		for (int i = 1; i <= headerList.size(); i++) {
			dataRowList.add("数据" + i);
		}

		List<List<String>> dataList = new ArrayList<List<String>>();
		for (int i = 1; i <= 1000000; i++) {
			dataList.add(dataRowList);
		}

		PoiDBExportToExcelFile ex = new PoiDBExportToExcelFile("title444", headerList, dataList);
		// OutputStream out = new FileOutputStream("d:\\workbook.xls");
		OutputStream out = new FileOutputStream("d:\\workbook.xlsx");
		ex.expordExcel(out);
		if (out != null) {
			out.flush();
			out.close();
		}
	}
}
