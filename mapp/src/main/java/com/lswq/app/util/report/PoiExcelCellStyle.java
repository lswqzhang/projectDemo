package com.lswq.app.util.report;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;

public class PoiExcelCellStyle {

    private PoiExcelCellStyle() {

    }

    /**
     * 
     * 创建标题样式
     *
     * @author zhangsw
     * @param wb
     * @return
     * 		CellStyle
     */
    public static CellStyle excelTitleCellStyle(Workbook wb) {

        CellStyle cellStyle = wb.createCellStyle();
        
        
        cellStyle.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());// 设置背景色
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        // 二、设置边框:

        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框

        // 三、设置居中:

        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);             // 设置水平居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  // 设置垂直居中

        // 四、设置字体:

        Font font = wb.createFont();
        font.setFontName("微软雅黑");
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
        font.setFontHeightInPoints((short) 11);// 设置字体大小

        cellStyle.setFont(font);// 选择需要用到的字体格式

        // 五、设置自动换行:

        cellStyle.setWrapText(true);// 设置自动换行

        return cellStyle;
    }

    /**
     * 
     * 创建数据样式
     *
     * @author zhangsw
     * @param wb
     * @return
     *      CellStyle
     */
    public static CellStyle excelDataCellStyle(Workbook wb) {

        CellStyle cellStyle = wb.createCellStyle();

        // 一、设置边框:

        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框

        // 二、设置居中:

        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中

        // 三、设置字体:

        Font font = wb.createFont();
        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short) 10);// 设置字体大小

        cellStyle.setFont(font);// 选择需要用到的字体格式

        // 四、设置不换行:

        cellStyle.setWrapText(false);// 设置不换行

        return cellStyle;
    }
}
