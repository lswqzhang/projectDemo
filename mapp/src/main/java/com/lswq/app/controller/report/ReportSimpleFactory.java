package com.lswq.app.controller.report;

import com.lswq.app.service.ExportReportI;
import com.lswq.app.util.PropertiesUtils;
import com.lswq.app.util.spring.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ReportSimpleFactory {
    
    
    private static final Logger logger = LoggerFactory.getLogger(ReportSimpleFactory.class.toString());
    
    private ReportSimpleFactory () {
        
    }

    
    /**
     * 
     * 简单工厂，获取报表spring的实现类
     * 
     * 可配置的简单工厂，通过spring获取简单工厂
     *
     * @author zhangsw
     * @param oper          此参数需要在src/main/resources/params/factory.properties文件中配置自己的实现类
     * @return
     * 		ExportReportI
     */
    public static ExportReportI exportOpreateFactory(String oper){
        String operBean = PropertiesUtils.getString(oper).trim();
        logger.info("获取的实例化类型为：" + operBean);
        return SpringContextUtil.getBean(operBean);
    }
    
}
