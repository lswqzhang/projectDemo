package com.lswq.app.controller.report;

import com.lswq.app.controller.BaseController;
import com.lswq.app.model.ReportBean;
import com.lswq.app.service.ExportReportI;
import com.lswq.app.util.MapUtil;
import com.lswq.app.util.report.ExportUtils;
import com.lswq.app.util.report.PoiCreateDynamicExcelUtils;
import com.lswq.app.util.report.PoiCreateStaticExcelUtils;
import com.lswq.app.util.report.ReportManage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("report")
public class ReportController extends BaseController<ReportController> {
    
    /**
     * 
     * 报表导出接口
     *
     * @author zhangsw
     * @param response
     * 		void
     */
    @RequestMapping(value = "export")
    public void exportHouseDataExclesFile(HttpServletResponse response)  {

        long time = System.nanoTime();
        
        String message = ",请求的时间标识为： " ;
        
        String oper = request.getParameter("oper");
        String queryJson = request.getParameter("queryJson");

        logger.error("获取的操作方法为：" + oper + message + time  + " 。");
        logger.error("获取的报表查询数据为：" + queryJson + message + time  + " 。");
        
        
        ExportReportI export = ReportSimpleFactory.exportOpreateFactory(oper);
        
        if (export == null) {
            logger.error("未获取相应的实现类,获取的操作方法为：" + oper + message + time  + " 。");
        } else {
            ReportBean reportBean = export.getReportBean(queryJson);
            InputStream is = null;
            try {
                if (reportBean.isStatic()) {
                    logger.error("动态报表导出。" + message + time  + " 。");
                    is = PoiCreateDynamicExcelUtils.createWorkBookWithInputStream(reportBean.getData(), reportBean.getTitle());
                } else {
                    logger.error("静态报表导出。" + message + time  + " 。");
                    is = PoiCreateStaticExcelUtils.createWorkBookWithInputStream(reportBean.getData(), reportBean.getTemplateName());
                }
                ExportUtils.exportExcelFile(response, is, reportBean.getFileName());
            } catch (IOException e) {
                logger.error("报表处理异常" + message + time, e);
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (Exception e) {
                        logger.error("文件流关闭异常" + message + time, e);
                    }
                }
            }
        }
    }
    
    
    @ResponseBody
    @RequestMapping(value = "test", method = RequestMethod.GET)
    public Object testWithRequest() throws IOException {
        
        
        
        ReportManage.ClientDynamicModel contractTitle = new ReportManage.ClientDynamicModel("contractNo", "合同编号");
        ReportManage.ClientDynamicModel areaTitle = new ReportManage.ClientDynamicModel("areaCode", "城区");
        ReportManage.ClientDynamicModel busiTitle = new ReportManage.ClientDynamicModel("busiCode", "片区");
        ReportManage.ClientDynamicModel houseFrom = new ReportManage.ClientDynamicModel("houseFrom", "房源方");
        ReportManage.ClientDynamicModel tradeInfo = new ReportManage.ClientDynamicModel("", "成交信息");
        List<ReportManage.ClientDynamicModel> tradeInfoList = new ArrayList<>();
        ReportManage.ClientDynamicModel estateName = new ReportManage.ClientDynamicModel("estateName", "楼盘名称");
        tradeInfoList.add(estateName);
        ReportManage.ClientDynamicModel tradePrice = new ReportManage.ClientDynamicModel("tradePrice", "成交价（万元）");
        tradeInfoList.add(tradePrice);
        ReportManage.ClientDynamicModel tradeArea = new ReportManage.ClientDynamicModel("tradeArea", "面积（㎡）");
        tradeInfoList.add(tradeArea);
        tradeInfo.setChildTitle(tradeInfoList);
        ReportManage.ClientDynamicModel houseController = new ReportManage.ClientDynamicModel("", "房源掌控");
        List<ReportManage.ClientDynamicModel> houseControllerList = new ArrayList<>();
        ReportManage.ClientDynamicModel singleHouse = new ReportManage.ClientDynamicModel("singleHouse", "独家");
        ReportManage.ClientDynamicModel keyHouse = new ReportManage.ClientDynamicModel("keyHouse", "钥匙");
        houseControllerList.add(singleHouse);
        houseControllerList.add(keyHouse);
        houseController.setChildTitle(houseControllerList);
        
        Set<ReportEnum.StaticTitle> staticTitle = new HashSet<>();
        ReportManage.ClientTitle serverDynamicTitle = ReportManage.getServerDynamicTitle(staticTitle, SecondLevelClassification.HOUSE_RESOURCES_SOURCE, "2016-10-01", "2016-10-31", null);
        ReportManage.ClientDynamicModel houseSouce = new ReportManage.ClientDynamicModel("", " 房源来源");
        houseSouce.setChildTitle(serverDynamicTitle.getTitle());
        
        List<ReportManage.ClientDynamicModel> contrace = new ArrayList<>();
        contrace.add(contractTitle);
        contrace.add(areaTitle);
        contrace.add(busiTitle);
        contrace.add(houseFrom);
        contrace.add(tradeInfo);
        contrace.add(houseController);
        contrace.add(houseSouce);
        
        
        return MapUtil.getAppMap(1, "获取成功",contrace);
    }
}
