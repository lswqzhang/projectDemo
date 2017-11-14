package com.lswq.app.util.report;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSONArray;
import com.ifang.erp.utils.PropertiesUtils;

/**
 * 
 * 导出静态报表
 * @author zhangsw
 * @version V2.0
 */
public class PoiCreateStaticExcelUtils {
    
    private static final Logger loggerslf = Logger.getLogger(PoiCreateStaticExcelUtils.class);
    
    //  初始化静态模板
    private static Map<String, byte[]> templeMap = null;

    private static String templePath = null;

    static {
        templePath = PropertiesUtils.getString("templePath");
        templeMap = new HashMap();

        File file = new File(templePath);

        File[] fs = file.listFiles();

        for (File f : fs) {
            if (f.getName().toLowerCase().endsWith("xlsx")) {
                try {

                    InputStream input = new FileInputStream(f);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = input.read(buffer)) > -1) {
                        baos.write(buffer, 0, len);
                    }
                    baos.flush();
                    byte[] tempByte = baos.toByteArray();

                    templeMap.put(f.getName().toLowerCase(), tempByte);

                    input.close();

                    baos.flush();

                    baos.close();

                } catch (FileNotFoundException e) {
                    loggerslf.error("报表模板没有找到", e);
                } catch (IOException e) {
                    loggerslf.error("报表模板文件读取异常", e);
                }
            }
        }
    }
    
    
    private PoiCreateStaticExcelUtils() {
        
    }

    

    

    private static InputStream templateStream(String templateName) throws IOException {
        // 获取模板的input
        if (templateName == null || "".equals(templateName)) {
            throw new NullPointerException("模板名不可以为空");
        }
        byte[] tempByte = templeMap.get(templateName.toLowerCase());
        return new ByteArrayInputStream(tempByte);

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
    public static ByteArrayOutputStream createWorkBook(JSONArray array, String templateName) throws IOException {

        InputStream is = null;
        try {
            is = PoiCreateStaticExcelUtils.templateStream(templateName);
        } catch (Exception e) {
            throw e;
        } finally {
            if (is != null) {
                is.close();
            }
        }
        // 源book
        XSSFWorkbook srcWorkBook = new XSSFWorkbook(is);
        ExcelDataCreate.exportExeclData(array, srcWorkBook);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        srcWorkBook.write(out);
        out.close();
        is.close();
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
    public static ByteArrayOutputStream createWorkBookWithOutputStream(List<Map<String, Object>> dataList, String templateName) throws IOException {

        InputStream is = null;
        try {
            is = PoiCreateStaticExcelUtils.templateStream(templateName);
        } catch (Exception e) {
            throw e;
        } finally {
            if (is != null) {
                is.close();
            }
        }
        // 源book
        Workbook srcWorkBook = new XSSFWorkbook(is);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ExcelDataCreate.exportExeclData(dataList, srcWorkBook);
            srcWorkBook.write(out);
            out.close();
            is.close();
        } catch (Exception e) {
            loggerslf.error("", e);
        } finally {
            srcWorkBook.close();
        }

        
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
    public static InputStream createWorkBookWithInputStream(List<Map<String, Object>> dataList, String templateName) throws IOException {

        InputStream is = PoiCreateStaticExcelUtils.templateStream(templateName);
        // 源book  
        XSSFWorkbook srcWorkBook = new XSSFWorkbook(is); 
        
        ExcelDataCreate.exportExeclData(dataList, srcWorkBook);
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        srcWorkBook.write(out);
        out.flush();
        out.close();
        is.close();
        srcWorkBook.close();
        
        byte[] content = out.toByteArray();
        
        return new ByteArrayInputStream(content);
    }
}
