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
public class PoiCreateDynamicExcelUtils {
    
    private PoiCreateDynamicExcelUtils() {
        
    }

    public static SXSSFWorkbook getTitleWorkBook(ReportManage.ClientTitle dynamicTitle) throws IOException {
        //  创建一个excle文件
        SXSSFWorkbook wb = new SXSSFWorkbook(100);
        //  创建一个sheet文件
        SXSSFSheet sheet = wb.createSheet();
        //  创建标题样式
        CellStyle cellStyle = PoiExcelCellStyle.excelTitleCellStyle(wb);
        
        List<ClientDynamicModel> dynamicModels = dynamicTitle.getTitle();
        
        //  创建标题行数据
        Row title = sheet.createRow(0);
        for(int cellnum = 0; cellnum < dynamicModels.size(); cellnum++){
            ClientDynamicModel model = dynamicModels.get(cellnum);
            Cell cell = title.createCell(cellnum);
            cell.setCellValue(model.getTitleDis());
            cell.setCellStyle(cellStyle);
        }
        
      
        
        //  创建替换行数据
        Row replace = sheet.createRow(1);
        Cell select = null;
        for(int cellnum = 0; cellnum < dynamicModels.size(); cellnum++){
            ClientDynamicModel model = dynamicModels.get(cellnum);
            Cell cell = replace.createCell(cellnum);
            if (cellnum == 0) {
                select = cell;
            }
            cell.setCellValue("${" + model.getTitleKey() + "}");
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
    private static void setColumnWidth(SXSSFWorkbook srcWorkBook, ReportManage.ClientTitle dynamicTitle) {
        
        List<ClientDynamicModel> dynamicModels = dynamicTitle.getTitle();
        
        Iterator<Sheet> sheetIterator = srcWorkBook.sheetIterator();
        
        while (sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();
            for (int i = 0; i < dynamicModels.size(); i++) {
                ClientDynamicModel model = dynamicModels.get(i);
                sheet.setColumnWidth(i, (model.getTitleDis().getBytes().length * 2 ) * 256);  // 6 characters wide
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
     * 		ByteArrayOutputStream
     */
    public static ByteArrayOutputStream createWorkBook(JSONArray array, ReportManage.ClientTitle dynamicTitle) throws IOException {
        // 源book
        SXSSFWorkbook srcWorkBook = getTitleWorkBook(dynamicTitle);

        ExcelDataCreate.exportExeclData(array, srcWorkBook);
        
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
     * 		ByteArrayOutputStream
     */
    public static ByteArrayOutputStream createWorkBookWithOutputStream(List<Map<String, Object>> dataList, ReportManage.ClientTitle dynamicTitle) throws IOException {

        
        SXSSFWorkbook srcWorkBook = getTitleWorkBook(dynamicTitle);

        ExcelDataCreate.exportExeclData(dataList, srcWorkBook);

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
    public static InputStream createWorkBookWithInputStream(List<Map<String, Object>> dataList, ReportManage.ClientTitle dynamicTitle) throws IOException {
        
        SXSSFWorkbook srcWorkBook = getTitleWorkBook(dynamicTitle);

        ExcelDataCreate.exportExeclData(dataList, srcWorkBook);
        
        setColumnWidth(srcWorkBook, dynamicTitle);
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        srcWorkBook.write(out);
        out.flush();
        srcWorkBook.close();
        
        return new ByteArrayInputStream(out.toByteArray());
    }
    
}
