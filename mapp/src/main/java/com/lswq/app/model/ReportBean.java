package com.lswq.app.model;

import com.lswq.app.util.report.ReportManage;

import java.util.List;
import java.util.Map;


/**
 * 
 * 报表处理类
 * @author zhangsw
 * @version V2.0
 */
public class ReportBean {
    
    //  判断是否是静态表头,默认是动态表头
    private boolean isStatic = false;
    //  生成报表的文件名
    private String fileName;
    //  静态报表的模板名称，如果是动态报表，此值为null或空字符串
    //  模板文件格式要统一处理
    private String templateName;
    //  动态报表生成的表头，如果是静态报表，此值为null
    private ReportManage.ClientTitle title;
    //  生成报表的基础数据
    private List<Map<String, Object>> data;
    
    public boolean isStatic() {
        return isStatic;
    }
    public void setStatic(boolean isStatic) {
        this.isStatic = isStatic;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getTemplateName() {
        return templateName;
    }
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
    public ReportManage.ClientTitle getTitle() {
        return title;
    }
    public void setTitle(ReportManage.ClientTitle title) {
        this.title = title;
    }
    public List<Map<String, Object>> getData() {
        return data;
    }
    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }
}
