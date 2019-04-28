/**
 * 2017年2月21日 下午2:43:50
 */
package com.wangzhixuan.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @author Esion
 *
 */
public class ExcelUtil {

	/** 输出文件路径 */
	// private static final String STATIC_PATH = "src/main/resources/download/";
	/** 输出文件后缀 */
	public static final String FILE_SUFF = ".xlsx";
	/** 字符源 */
	// private static final String BASE_WORD =
	// "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月");

	private static String sui_fang_tj;

	/**
	 * 导出Excel
	 * 
	 * @param title
	 * @param headers
	 * @param dataset
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	/*@SuppressWarnings("resource")
	public static void exportExcel(Long userId, Date date, List<SuiFangTongJiVo> sftjVoList,
			HttpServletResponse response) throws FileNotFoundException, IOException {
		sui_fang_tj = dateFormat.format(date) + "-出院随访统计表";*/
		// StringBuilder sb = new StringBuilder();
		// String hexTime =
		// Long.toHexString(DateUtility.getCurrentTimestamp().getTime()).toUpperCase();
		// String hexTime = Long.toHexString(new
		// Date().getTime()).toUpperCase();
		// sb.append(hexTime);
		// Random random = new Random();
		// for (int i = 0; i < 10; i++) {
		// int number = random.nextInt(BASE_WORD.length());
		// sb.append(BASE_WORD.charAt(number));
		// }
		// String filePath = sb.toString();
		// String outputFilePath = userId + "_" + filePath + FILE_SUFF;
	/*	Workbook book = null;
		// FileOutputStream fout = null;
		OutputStream out = response.getOutputStream();

		try {
			book = new SXSSFWorkbook();
			// 字体设置
			Font haederFont = book.createFont();
			haederFont.setFontName("宋体");
			haederFont.setFontHeightInPoints((short) 18);
			Font font = book.createFont();
			font.setFontName("微软黑体字");
			font.setFontHeightInPoints((short) 12);

			// 标题样式
			CellStyle style_header = book.createCellStyle();
			setBorder(style_header, BorderStyle.THIN);
			style_header.setAlignment(HorizontalAlignment.CENTER);
			style_header.setVerticalAlignment(VerticalAlignment.CENTER);
			style_header.setFont(haederFont);

			// 小标题样式
			CellStyle style_title = book.createCellStyle();
			setBorder(style_title, BorderStyle.THIN);
			style_title.setVerticalAlignment(VerticalAlignment.CENTER);
			style_title.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
			style_title.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			style_title.setFont(font);
			// 文字样式
			CellStyle style_string = book.createCellStyle();
			setBorder(style_string, BorderStyle.THIN);
			style_string.setVerticalAlignment(VerticalAlignment.CENTER);
			// 自动换行
			style_string.setWrapText(true);
			style_string.setFont(font);

			Row row;
			Cell cell;

			if (sftjVoList.size() > 0) {// 制作表格
				// 制作表格
				Sheet sheet = book.createSheet();
				// 设置标题(合并单元格)
				sheet.addMergedRegion(new CellRangeAddress(0, 2, 0, 9));
				row = sheet.createRow(0);// 第一行
				cell = row.createCell(0);

				cell.setCellValue(sui_fang_tj);// TODO设置标题
				cell.setCellStyle(style_header);// 设置样式
				if (sheet instanceof SXSSFSheet) {
					((SXSSFSheet) sheet).trackAllColumnsForAutoSizing();
				}
				// 名称的设定
				book.setSheetName(0, "随访统计");

				// 数据第一行列
				int sfRowNumber = 3;
				int sfColNumber = 0;
				row = sheet.createRow(sfRowNumber);

				cell = row.createCell(sfColNumber++);
				cell.setCellStyle(style_title);
				cell.setCellType(CellType.NUMERIC);
				cell.setCellValue("NO.");

				cell = row.createCell(sfColNumber++);
				cell.setCellStyle(style_title);
				cell.setCellType(CellType.STRING);
				cell.setCellValue("科室");

				cell = row.createCell(sfColNumber++);
				cell.setCellStyle(style_title);
				cell.setCellType(CellType.STRING);
				cell.setCellValue("出院人数");

				cell = row.createCell(sfColNumber++);
				cell.setCellStyle(style_title);
				cell.setCellType(CellType.STRING);
				cell.setCellValue("死亡人数");

				cell = row.createCell(sfColNumber++);
				cell.setCellStyle(style_title);
				cell.setCellType(CellType.STRING);
				cell.setCellValue("已随访");

				cell = row.createCell(sfColNumber++);
				cell.setCellStyle(style_title);
				cell.setCellType(CellType.STRING);
				cell.setCellValue("未随访");

				cell = row.createCell(sfColNumber++);
				cell.setCellStyle(style_title);
				cell.setCellType(CellType.STRING);
				cell.setCellValue("拒绝随访");

				cell = row.createCell(sfColNumber++);
				cell.setCellStyle(style_title);
				cell.setCellType(CellType.STRING);
				cell.setCellValue("所有随访");

				cell = row.createCell(sfColNumber++);
				cell.setCellStyle(style_title);
				cell.setCellType(CellType.STRING);
				cell.setCellValue("随访率");

				cell = row.createCell(sfColNumber);
				cell.setCellStyle(style_title);
				cell.setCellType(CellType.STRING);
				cell.setCellValue("成功随访率");

				// 窗口冻结固定
				sheet.createFreezePane(10, 4);
				// 表头行自动过滤器的设定
				// sheet.setAutoFilter(new CellRangeAddress(3, 3, 0,
				// sfColNumber));

				// 列幅自动调整
				for (int j = 0; j <= sfColNumber; j++) {
					sheet.autoSizeColumn(j, true);
				}
				int j = 0;
				for (SuiFangTongJiVo sftjVo : sftjVoList) {
					j++;
					sfRowNumber++;
					sfColNumber = 0;
					row = sheet.createRow(sfRowNumber);

					cell = row.createCell(sfColNumber++);
					cell.setCellStyle(style_string);
					cell.setCellType(CellType.NUMERIC);
					cell.setCellValue(j);

					cell = row.createCell(sfColNumber++);
					cell.setCellStyle(style_string);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(sftjVo.getDeptName());

					cell = row.createCell(sfColNumber++);
					cell.setCellStyle(style_string);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(sftjVo.getLeaveCount());

					cell = row.createCell(sfColNumber++);
					cell.setCellStyle(style_string);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(sftjVo.getDeadCount());

					cell = row.createCell(sfColNumber++);
					cell.setCellStyle(style_string);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(sftjVo.getSuccessSf());

					cell = row.createCell(sfColNumber++);
					cell.setCellStyle(style_string);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(sftjVo.getWaitSf());

					cell = row.createCell(sfColNumber++);
					cell.setCellStyle(style_string);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(sftjVo.getRefuseSf());

					cell = row.createCell(sfColNumber++);
					cell.setCellStyle(style_string);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(sftjVo.getAllSf());

					cell = row.createCell(sfColNumber++);
					cell.setCellStyle(style_string);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(sftjVo.getSfPercent());

					cell = row.createCell(sfColNumber);
					cell.setCellStyle(style_string);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(sftjVo.getSuccessPercent());

					// 列幅自动调整
					for (int k = 0; k <= sfColNumber; k++) {
						sheet.autoSizeColumn(k, true);
					}
				}
			}

			// if (sensorList.size() > 0) {
			// // 制作表格
			// Sheet sheet = book.createSheet();
			// // 设置标题
			// sheet.addMergedRegion(new CellRangeAddress(0, 2, 0, 3));
			// row = sheet.createRow(0);// 第一行
			// cell = row.createCell(0);
			// cell.setCellValue(title + "-传感器列表");
			// cell.setCellStyle(style_header);// 设置样式
			//
			// if (sheet instanceof SXSSFSheet) {
			// ((SXSSFSheet) sheet).trackAllColumnsForAutoSizing();
			// }
			// // 名称的设定
			// book.setSheetName(1, "传感器列表");
			//
			// // 数据第一行列
			// int sensorRowNumber = 3;
			// int sensorColNumber = 0;
			// row = sheet.createRow(sensorRowNumber);
			//
			// cell = row.createCell(sensorColNumber++);
			// cell.setCellStyle(style_title);
			// cell.setCellType(CellType.STRING);
			// cell.setCellValue("No.");
			//
			// cell = row.createCell(sensorColNumber++);
			// cell.setCellStyle(style_title);
			// cell.setCellType(CellType.STRING);
			// cell.setCellValue("传感器ID");
			//
			// cell = row.createCell(sensorColNumber++);
			// cell.setCellStyle(style_title);
			// cell.setCellType(CellType.STRING);
			// cell.setCellValue("传感器名称");
			//
			// cell = row.createCell(sensorColNumber);
			// cell.setCellStyle(style_title);
			// cell.setCellType(CellType.STRING);
			// cell.setCellValue("传感器类别名");
			//
			// // 窗口固定
			// sheet.createFreezePane(4, 4);
			//
			// // 表头行自动过滤器的设定
			// // sheet.setAutoFilter(new CellRangeAddress(3, 3, 0,
			// // sensorColNumber));
			//
			// // 列幅自动调整
			// for (int i = 0; i <= sensorColNumber; i++) {
			// sheet.autoSizeColumn(i, true);
			// }
			// int m = 0;
			// for (SensorValue sensorValue : sensorList) {
			// m++;
			// sensorRowNumber++;
			// sensorColNumber = 0;
			// row = sheet.createRow(sensorRowNumber);
			//
			// cell = row.createCell(sensorColNumber++);
			// cell.setCellStyle(style_string);
			// cell.setCellType(CellType.NUMERIC);
			// cell.setCellValue(m);
			//
			// cell = row.createCell(sensorColNumber++);
			// cell.setCellStyle(style_string);
			// cell.setCellType(CellType.STRING);
			// cell.setCellValue(sensorValue.getSensorId());
			//
			// cell = row.createCell(sensorColNumber++);
			// cell.setCellStyle(style_string);
			// cell.setCellType(CellType.STRING);
			// cell.setCellValue(sensorValue.getSensorName());
			//
			// cell = row.createCell(sensorColNumber);
			// cell.setCellStyle(style_string);
			// cell.setCellType(CellType.STRING);
			// cell.setCellValue(sensorValue.getSensorTypeName());
			//
			// // 列幅自动调整
			// for (int k = 0; k <= sensorColNumber; k++) {
			// sheet.autoSizeColumn(k, true);
			// }
			// }
			// }

			// 文件输出
			// fout = new FileOutputStream(STATIC_PATH + outputFilePath);
			// fout = new FileOutputStream("D:/aaaaaaaa.xlsx");
			response.reset();
			response.setHeader("Content-disposition",
					"attachment; filename=" + URLEncoder.encode(sui_fang_tj, "UTF-8") + FILE_SUFF);
			response.setContentType("Content-Type:application/vnd.ms-excel");
			book.write(out);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					throw new SysException("文件下载失败");
				}
			}
			if (book != null) {
				try {
					*//*
					 * SXSSFWorkbook内存空间是节约代替临时文件大量生成， 了不需要的阶段dispose了临时文件删除有必要
					 *//*
					((SXSSFWorkbook) book).dispose();
				} catch (Exception e) {
					throw new SysException("文件下载失败");
				}
			}
		}
	}
*/
	/**
	 * 
	 * @param style
	 * @param border
	 */
//	private static void setBorder(CellStyle style, BorderStyle border) {
//		style.setBorderBottom(border);
//		style.setBorderTop(border);
//		style.setBorderLeft(border);
//		style.setBorderRight(border);
//	}

	/**
	 * 
	 * @param userId
	 * @param filePath
	 * @return
	 */
	// public static String getFullFilePath(String userId, String filePath) {
	// return STATIC_PATH + userId + "_" + filePath + ExcelUtil.FILE_SUFF;
	// }

}
