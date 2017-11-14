package com.lswq.app.util.report;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.alibaba.fastjson.JSONArray;
import com.ifang.erp.utils.report.ReportManage.ClientDynamicModel;

/**
 * 
 * 导出静态报表
 * @author zhangsw
 * @version V2.0
 */
public class PoiCreateTwoLevelDynamicExcelUtils {

    private PoiCreateTwoLevelDynamicExcelUtils() {

    }

    private static SXSSFWorkbook getTitleWorkBook(List<ReportManage.ClientDynamicModel> dynamicTitle) throws IOException {
        //  创建一个excle文件
        SXSSFWorkbook wb = new SXSSFWorkbook(100);
        //  创建一个sheet文件
        SXSSFSheet sheet = wb.createSheet();
        // 创建标题样式
        CellStyle cellStyle = PoiExcelCellStyle.excelTitleCellStyle(wb);

        //  创建标题行数据
        int currPost = 0;
        //  设置对应数据的列号
        int totalCol = 0;

        // 生成第一行
        Row firstRow = sheet.createRow(0);
        Row secoundRow = sheet.createRow(1);
        Cell titleCell = null;
        
        for (int i = 0; i < dynamicTitle.size(); i++) {
            ClientDynamicModel model = dynamicTitle.get(i);
            List<ClientDynamicModel> childTitle = model.getChildTitle();
            if (childTitle != null && !childTitle.isEmpty()) {
                int start = currPost;
                int last = currPost + childTitle.size() - 1;
                // 单元格范围 参数（int firstRow, int lastRow, int firstCol, int lastCol)
                CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, start, last);
                //  给每一个表格设置样式
                for (int j = start; j < last + 1; j++) {
                    titleCell = firstRow.createCell(j);
                    titleCell.setCellStyle(cellStyle);
                }
                // 在sheet里增加合并单元格
                sheet.addMergedRegion(cellRangeAddress);
                titleCell = firstRow.createCell(start);
                titleCell.setCellValue(model.getTitleDis());
                titleCell.setCellStyle(cellStyle);
                currPost = last + 1;
                for (int j = 0; j < childTitle.size(); j++) {
                    ClientDynamicModel child = childTitle.get(j);
                    titleCell = secoundRow.createCell(totalCol);
                    titleCell.setCellValue(child.getTitleDis());
                    titleCell.setCellStyle(cellStyle);
                    totalCol++;
                }
            } else {
                // 单元格范围 参数（int firstRow, int lastRow, int firstCol, int lastCol)
                CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 1, currPost, currPost);
                // 在sheet里增加合并单元格
                sheet.addMergedRegion(cellRangeAddress);
                titleCell = firstRow.createCell(totalCol);
                titleCell.setCellValue(model.getTitleDis());
                titleCell.setCellStyle(cellStyle);
                Cell secound = secoundRow.createCell(totalCol);
                secound.setCellStyle(cellStyle);
                currPost++;
                totalCol++;
            }
        }

        //  设置数据替换操作
        totalCol = 0;
        Row threadRow = sheet.createRow(2);
        
        Cell replaceCell = null;
        Cell select = null;
        for (int i = 0; i < dynamicTitle.size(); i++) {
            ClientDynamicModel model = dynamicTitle.get(i);
            List<ClientDynamicModel> childTitle = model.getChildTitle();
            
            if (childTitle != null && !childTitle.isEmpty()) {
                for (int j = 0; j < childTitle.size(); j++) {
                    ClientDynamicModel child = childTitle.get(j);
                    replaceCell = threadRow.createCell(totalCol);
                    replaceCell.setCellValue("${" +  child.getTitleKey() + "}");
                    totalCol++;
                }
            } else {
                replaceCell = threadRow.createCell(totalCol);
                replaceCell.setCellValue("${" +  model.getTitleKey() + "}");
                totalCol++;
            }
            
            if (i == 0) {
                select = replaceCell;
            }
        }
        
        CellAddress activeCell = new CellAddress(select);
        sheet.setActiveCell(activeCell);
        
        return wb;
    }
    
    
    /**
     * 
     * 设置报表的列宽
     *
     * @author zhangsw
     * @param srcWorkBook
     * @param dynamicTitle
     *      void
     */
    private static void setColumnWidth(SXSSFWorkbook srcWorkBook, List<ReportManage.ClientDynamicModel> dynamicTitle) {
        
        Iterator<Sheet> sheetIterator = srcWorkBook.sheetIterator();
        
        while (sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();
            //  设置对应数据的列号
            int totalCol = 0;
            for (int i = 0; i < dynamicTitle.size(); i++) {
                ClientDynamicModel model = dynamicTitle.get(i);
                List<ClientDynamicModel> childTitle = model.getChildTitle();
                if (childTitle != null && !childTitle.isEmpty()) {
                    for (int j = 0; j < childTitle.size(); j++) {
                        ClientDynamicModel child = childTitle.get(j);
                        sheet.setColumnWidth(totalCol, (int) (child.getTitleDis().getBytes().length * 1.5 * 256));
                        totalCol++;
                    }
                } else {
                    sheet.setColumnWidth(totalCol, (int) (model.getTitleDis().getBytes().length *1.5 *256));
                    totalCol++;
                }
            }
        }
    }

    /**
     * 
     * 创建报表
     *
     * @author zhangsw
     * @param array         数据格式为[{"aaa":"111","bbb":"222"},{"aaa":"333","bbb":"444"}]
     * @param templateName
     * @return
     * @throws IOException
     *      ByteArrayOutputStream
     */
    public static ByteArrayOutputStream createWorkBook(Map<String, String> total, Map<String, String> proportion, JSONArray array, List<ReportManage.ClientDynamicModel> dynamicTitle) throws IOException {
        // 源book
        SXSSFWorkbook srcWorkBook = getTitleWorkBook(dynamicTitle);
        //  获取报表表头
        List<String> titles = ExcelDataCreate.getTitles(srcWorkBook.getSheetAt(0));
        //  处理数据
        ExcelDataCreate.exportExeclData(array, srcWorkBook);
        //  处理统计数据
        ExcelDataCreate.exportStatisticsData(titles, total, proportion, srcWorkBook, array.size() + 2 - 1);
        //  设置报表宽度
        setColumnWidth(srcWorkBook, dynamicTitle);
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        srcWorkBook.write(out);
        out.close();
        srcWorkBook.close();
        return out;
    }

    
    
    /**
     * 
     * 创建报表
     *
     * @author zhangsw
     * @param dataList      数据列表    数据格式为[{"aaa":"111","bbb":"222"},{"aaa":"333","bbb":"444"}]
     * @param templateName  模板名称
     * @return
     * @throws IOException
     *      ByteArrayOutputStream
     */
    public static ByteArrayOutputStream createWorkBookWithOutputStream(Map<String, String> total, Map<String, String> proportion, List<Map<String, Object>> dataList, List<ReportManage.ClientDynamicModel> dynamicTitle) throws IOException {

        
        SXSSFWorkbook srcWorkBook = getTitleWorkBook(dynamicTitle);
        //  获取报表表头
        List<String> titles = ExcelDataCreate.getTitles(srcWorkBook.getSheetAt(0));
        //  处理数据
        ExcelDataCreate.exportExeclData(dataList, srcWorkBook);
        //  处理统计数据
        ExcelDataCreate.exportStatisticsData(titles, total, proportion, srcWorkBook, dataList.size() + 2 - 1);
        //  设置报表宽度
        setColumnWidth(srcWorkBook, dynamicTitle);
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        srcWorkBook.write(out);
        out.close();
        srcWorkBook.close();
        return out;
    }
    
    
    
    /**
     * 
     * 创建报表,使用后需要关闭InputStream
     *
     * @author zhangsw
     * @param dataList      数据列表    数据格式为[{"aaa":"111","bbb":"222"},{"aaa":"333","bbb":"444"}]
     * @param templateName  模板名称
     * @return
     * @throws IOException
     *      ByteArrayOutputStream
     */
    public static InputStream createWorkBookWithInputStream(Map<String, String> total, Map<String, String> proportion, List<Map<String, Object>> dataList, List<ReportManage.ClientDynamicModel> dynamicTitle) throws IOException {
        
        SXSSFWorkbook srcWorkBook = getTitleWorkBook(dynamicTitle);
        //  获取报表表头
        List<String> titles = ExcelDataCreate.getTitles(srcWorkBook.getSheetAt(0));
        
        //  处理数据
        ExcelDataCreate.exportExeclData(dataList, srcWorkBook);
        //  处理统计数据
        ExcelDataCreate.exportStatisticsData(titles, total, proportion, srcWorkBook, dataList.size() + 2 - 1);
        //  设置报表宽度
        setColumnWidth(srcWorkBook, dynamicTitle);
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        srcWorkBook.write(out);
        out.flush();
        srcWorkBook.close();
        
        return new ByteArrayInputStream(out.toByteArray());
    }

    

}
