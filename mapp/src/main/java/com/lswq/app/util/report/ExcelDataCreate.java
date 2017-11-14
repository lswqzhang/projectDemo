package com.lswq.app.util.report;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.alibaba.fastjson.JSONArray;

public class ExcelDataCreate {

    private static final Logger loggerslf = Logger.getLogger(PoiCreateStaticExcelUtils.class);

    private ExcelDataCreate() {

    }

    // 单个sheet页报表模板,获取标题的row
    /**
     * 获取表单的title
     * @param sheet
     * @return
     */
    public static List<String> getTitles(Sheet sheet) {
        // 获取被选中的单元格
        CellAddress activeCell = sheet.getActiveCell();

        Row row = null;
        // 如果为空，则表示没有选中的单元格
        if (activeCell == null) {
            loggerslf.info("Column:" + 0);
            loggerslf.info("Row:" + 0);
            // 最后一个有内容的行,以0开始
            int lastRowNum = sheet.getLastRowNum();
            loggerslf.info("lastRowNum:" + (lastRowNum + 1));
            row = sheet.getRow(lastRowNum);
        } else {
            loggerslf.info("Column:" + activeCell.getColumn());
            loggerslf.info("Row:" + activeCell.getRow());
            row = sheet.getRow(activeCell.getRow());
        }
        Iterator<Cell> cells = row.iterator();

        List<String> titles = new ArrayList<String>();

        while (cells.hasNext()) {
            Cell cell = cells.next();
            cell.setCellType(Cell.CELL_TYPE_STRING);
            titles.add(cell.getStringCellValue());
        }
        return titles;
    }

    private static List<CellStyle> getCellStyle(Sheet sheet) {
        List<CellStyle> styles = new ArrayList<CellStyle>();
        // 获取被选中的单元格
        CellAddress activeCell = sheet.getActiveCell();
        int rowNum = activeCell.getRow();
        Row row = sheet.getRow(rowNum);
        Iterator<Cell> it = row.iterator();
        while (it.hasNext()) {
            Cell cell = it.next();
            CellStyle style = cell.getCellStyle();
            styles.add(style);
        }
        return styles;
    }

    public static void exportExeclData(JSONArray array, Workbook srcWorkBook) throws IOException {
        CellStyle dataCellStyle = PoiExcelCellStyle.excelDataCellStyle(srcWorkBook);
        Iterator<Sheet> sheets = srcWorkBook.sheetIterator();
        while (sheets.hasNext()) {
            Sheet sheet = sheets.next();
            // 标题
            List<String> titles = ExcelDataCreate.getTitles(sheet);
            // 单元格样式
            List<CellStyle> styles = ExcelDataCreate.getCellStyle(sheet);

            Iterator<Object> mapIt = array.iterator();

            int startRowNum = sheet.getActiveCell().getRow();
            int startCellNum = sheet.getActiveCell().getColumn();

            Pattern regex = Pattern.compile("(?<=\\$\\{).*(?=\\})");

            while (mapIt.hasNext()) {
                @SuppressWarnings("unchecked")
                Map<String, Object> dataMap = (Map<String, Object>) mapIt.next();
                Row row = sheet.createRow(startRowNum);
                for (int i = 0; i < titles.size(); i++) {
                    String title = titles.get(i);
                    try {
                        Matcher regexMatcher = regex.matcher(title);
                        if (regexMatcher.find()) {
                            String t = regexMatcher.group();
                            Cell c = row.createCell(startCellNum + i);
                            c.setCellStyle(styles.get(i));
                            if (StringUtils.isEmpty(String.valueOf(dataMap.get(t))) || "null".equals(String.valueOf(dataMap.get(t)))) {
                                c.setCellValue("--");
                            } else {
                                c.setCellValue(String.valueOf(dataMap.get(t)));
                            }
                            c.setCellStyle(dataCellStyle);
                        }
                    } catch (PatternSyntaxException ex) {
                        throw ex;
                    }
                }
                startRowNum++;
            }
        }
    }

    public static void exportExeclData(List<Map<String, Object>> dataList, Workbook srcWorkBook) throws IOException {

        CellStyle dataCellStyle = PoiExcelCellStyle.excelDataCellStyle(srcWorkBook);

        if (dataList != null) {
            Iterator<Sheet> sheets = srcWorkBook.sheetIterator();
            while (sheets.hasNext()) {
                Sheet sheet = sheets.next();
                // 标题
                List<String> titles = ExcelDataCreate.getTitles(sheet);
                // 单元格样式
                List<CellStyle> styles = ExcelDataCreate.getCellStyle(sheet);

                Iterator<Map<String, Object>> mapIt = dataList.iterator();

                int startRowNum = sheet.getActiveCell().getRow();
                int startCellNum = sheet.getActiveCell().getColumn();

                Pattern regex = Pattern.compile("(?<=\\$\\{).*(?=\\})");

                while (mapIt.hasNext()) {
                    Map<String, Object> dataMap = mapIt.next();
                    Row row = sheet.createRow(startRowNum);
                    for (int i = 0; i < titles.size(); i++) {
                        String title = titles.get(i);
                        try {
                            Matcher regexMatcher = regex.matcher(title);
                            if (regexMatcher.find()) {
                                String t = regexMatcher.group();
                                Cell c = row.createCell(startCellNum + i);
                                c.setCellStyle(styles.get(i));
                                if (StringUtils.isEmpty(String.valueOf(dataMap.get(t))) || "null".equals(String.valueOf(dataMap.get(t)))) {
                                    c.setCellValue("--");
                                } else {
                                    c.setCellValue(String.valueOf(dataMap.get(t)));
                                }
                                c.setCellStyle(dataCellStyle);
                            }
                        } catch (PatternSyntaxException ex) {
                            throw ex;
                        }
                    }
                    startRowNum++;
                }
            }
        }
    }

    /**
     * 
     * 给报表添加统计数据
     *
     * @author zhangsw
     * @param titles
     * @param statistics
     * @param srcWorkBook
     * @param i
     *      void
     */
    
    
    /**
     * 
     * 给报表添加统计数据
     *
     * @author zhangsw
     * @param titles        报表表头的key
     * @param total         报表统计数据
     * @param proportion    报表占比数据
     * @param srcWorkBook   创建的报表数据
     * @param startRow      报表开始的行
     * 		void
     */
    public static void exportStatisticsData(List<String> titles, Map<String, String> total, Map<String, String> proportion, SXSSFWorkbook srcWorkBook, int startRow) {
        // TODO Auto-generated method stub
        Iterator<Sheet> sheets = srcWorkBook.sheetIterator();

        Pattern regex = Pattern.compile("(?<=\\$\\{).*(?=\\})");

        while (sheets.hasNext()) {
            Sheet sheet = sheets.next();
            // 合计数据处理
            int rowNum = startRow + 1;
            Row row = sheet.createRow(rowNum);
            int startCol = 2;
            CellRangeAddress totalRange = new CellRangeAddress(rowNum, rowNum, 0, startCol);
            sheet.addMergedRegion(totalRange);
            Cell cell = row.createCell(0);
            cell.setCellValue(total.get("TotalMessage"));

            for (int i = 0; i < titles.size(); i++) {
                String title = titles.get(i);
                try {
                    Matcher regexMatcher = regex.matcher(title);
                    if (regexMatcher.find()) {
                        String t = regexMatcher.group();
                        Cell c = row.createCell(2 + i);
                        if (StringUtils.isEmpty(String.valueOf(total.get(t))) || "null".equals(String.valueOf(total.get(t)))) {
                            c.setCellValue("--");
                        } else {
                            c.setCellValue(String.valueOf(total.get(t)));
                        }
                    }
                } catch (PatternSyntaxException ex) {
                    throw ex;
                }
            }

            // 占比数据处理
            rowNum = startRow + 2;
            row = sheet.createRow(rowNum);
            CellRangeAddress cellRangeAddress = new CellRangeAddress(rowNum, rowNum, 0, startCol);
            sheet.addMergedRegion(cellRangeAddress);
            cell = row.createCell(0);
            cell.setCellValue(total.get("ProportionMessage"));
            
            for (int i = 0; i < titles.size(); i++) {
                String title = titles.get(i);
                try {
                    Matcher regexMatcher = regex.matcher(title);
                    if (regexMatcher.find()) {
                        String t = regexMatcher.group();
                        Cell c = row.createCell(2 + i);
                        if (StringUtils.isEmpty(String.valueOf(proportion.get(t))) || "null".equals(String.valueOf(proportion.get(t)))) {
                            c.setCellValue("");
                        } else {
                            c.setCellValue(String.valueOf(proportion.get(t)));
                        }
                    }
                } catch (PatternSyntaxException ex) {
                    throw ex;
                }
            }
        }
    }

}
