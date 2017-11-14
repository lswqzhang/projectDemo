package com.lswq.app.service;


import com.lswq.app.model.ReportBean;

public interface ExportReportI {

    /**
     * 生成报表的统一接口
     *
     * @param queryJson 报表查询条件，与正常的前端发起的查询条件相同，但是没有分页数据
     * @return ReportBean
     * @author zhangsw
     */
    ReportBean getReportBean(String queryJson);


}
