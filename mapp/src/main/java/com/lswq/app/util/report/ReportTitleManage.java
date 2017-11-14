package com.lswq.app.util.report;

import com.ifang.erp.constant.report.ReportEnum;
import com.ifang.erp.utils.report.ReportManage.ClientDynamicModel;

import java.util.List;
import java.util.Set;

public class ReportTitleManage {

    private ReportTitleManage() {
        
    }
    
    /**
     * 
     * 前部分的表头处理
     *
     * @author zhangsw
     * @param staticTitle
     * @param title
     * 		void
     */
    protected static void afterTitle(Set<ReportEnum.StaticTitle> staticTitle, List<ClientDynamicModel> cList) {
        if (staticTitle.contains(ReportEnum.StaticTitle.TOTAL_CALCUL)) {
            cList.add(new ClientDynamicModel(ReportEnum.StaticTitle.TOTAL_CALCUL.getName(), "合计"));
        }
        
        if (staticTitle.contains(ReportEnum.StaticTitle.RANKING_CALCUL)) {
            cList.add(new ClientDynamicModel(ReportEnum.StaticTitle.RANKING_CALCUL.getName(), "名次"));
        }
        
        if (staticTitle.contains(ReportEnum.StaticTitle.RANKING)) {
            cList.add(new ClientDynamicModel(ReportEnum.StaticTitle.RANKING.getName(), "排名"));
        }
        //**********业绩分成说明*************//
        if (staticTitle.contains(ReportEnum.StaticTitle.EMPLOYEE_STATUS)) {
            cList.add(new ClientDynamicModel(ReportEnum.StaticTitle.EMPLOYEE_STATUS.getName(), "员工状态"));
        }
    }


    /**
     * 
     * 后部分的表头处理
     *
     * @author zhangsw
     * @param staticTitle
     * @param title
     * 		void
     */
    protected static void beforeTitle(Set<ReportEnum.StaticTitle> staticTitle, List<ClientDynamicModel> cList) {
        
        if (staticTitle.contains(ReportEnum.StaticTitle.DEPT_TITLE)) {
            cList.add(new ClientDynamicModel(ReportEnum.StaticTitle.DEPT_TITLE.getName(), "部门"));
        }
        
        if (staticTitle.contains(ReportEnum.StaticTitle.EMPLOY_TITLE)) {
            cList.add(new ClientDynamicModel(ReportEnum.StaticTitle.EMPLOY_TITLE.getName(), "员工"));
        }

        if (staticTitle.contains(ReportEnum.StaticTitle.AREA_TITLE)) {
            cList.add(new ClientDynamicModel(ReportEnum.StaticTitle.AREA_TITLE.getName(), "城区"));
        }
        
        if (staticTitle.contains(ReportEnum.StaticTitle.VALID_SELL)) {
            cList.add(new ClientDynamicModel(ReportEnum.StaticTitle.VALID_SELL.getName(), "有效（售）"));
        }
        
        if (staticTitle.contains(ReportEnum.StaticTitle.VALID_RENT)) {
            cList.add(new ClientDynamicModel(ReportEnum.StaticTitle.VALID_RENT.getName(), "有效（租）"));
        }
        
        /******其他报表图片上传*******/
        if (staticTitle.contains(ReportEnum.StaticTitle.ALL_PHOTO)) {
            cList.add(new ClientDynamicModel(ReportEnum.StaticTitle.ALL_PHOTO.getName(), "房源总照片数",null,null));
        }
        if (staticTitle.contains(ReportEnum.StaticTitle.LOAD_PHOTO)) {
            cList.add(new ClientDynamicModel(ReportEnum.StaticTitle.LOAD_PHOTO.getName(), "已上传照片的房源数",null,null));
        }
        if (staticTitle.contains(ReportEnum.StaticTitle.NO_PHOTO)) {
            cList.add(new ClientDynamicModel(ReportEnum.StaticTitle.NO_PHOTO.getName(), "未上传照片的房源数",null,null));
        }
        
        /////////*******************合同********************/////////////
        /******业绩统计*******/
        if (staticTitle.contains(ReportEnum.StaticTitle.SELL_SHEET)) {
            cList.add(new ClientDynamicModel(ReportEnum.StaticTitle.SELL_SHEET.getName(), "售单",null,null));
        }
        if (staticTitle.contains(ReportEnum.StaticTitle.RENT_SHEET)) {
            cList.add(new ClientDynamicModel(ReportEnum.StaticTitle.RENT_SHEET.getName(), "租单",null,null));
        }
        if (staticTitle.contains(ReportEnum.StaticTitle.ALL_SHEET)) {
            cList.add(new ClientDynamicModel(ReportEnum.StaticTitle.ALL_SHEET.getName(), "总单",null,null));
        }
        if (staticTitle.contains(ReportEnum.StaticTitle.SHOULD_RECEIVE_PERFORMANCE)) {
            cList.add(new ClientDynamicModel(ReportEnum.StaticTitle.SHOULD_RECEIVE_PERFORMANCE.getName(), "应收业绩"));
        }
        
        /******业务综合*******/
        if (staticTitle.contains(ReportEnum.StaticTitle.RENT_HOUSE_NUM)) {
            cList.add(new ClientDynamicModel(ReportEnum.StaticTitle.RENT_HOUSE_NUM.getName(), "出租房源",null,null));
        }
        if (staticTitle.contains(ReportEnum.StaticTitle.SELL_HOUSE_NUM)) {
            cList.add(new ClientDynamicModel(ReportEnum.StaticTitle.SELL_HOUSE_NUM.getName(), "出售房源",null,null));
        }
        if (staticTitle.contains(ReportEnum.StaticTitle.ALL_HOUSE_NUM)) {
            cList.add(new ClientDynamicModel(ReportEnum.StaticTitle.ALL_HOUSE_NUM.getName(), "房源总数",null,null));
        }
        if (staticTitle.contains(ReportEnum.StaticTitle.FOLLOWUP_HOUSE_NUM)) {
            cList.add(new ClientDynamicModel(ReportEnum.StaticTitle.FOLLOWUP_HOUSE_NUM.getName(), "房源跟进",null,null));
        }
        if (staticTitle.contains(ReportEnum.StaticTitle.FOLLOWUP_LOOK_HOUSE_NUM)) {
            cList.add(new ClientDynamicModel(ReportEnum.StaticTitle.FOLLOWUP_LOOK_HOUSE_NUM.getName(), "房源带看",null,null));
        }
        if (staticTitle.contains(ReportEnum.StaticTitle.RENT_ROOMER_NUM)) {
            cList.add(new ClientDynamicModel(ReportEnum.StaticTitle.RENT_ROOMER_NUM.getName(), "求租客户",null,null));
        }
        if (staticTitle.contains(ReportEnum.StaticTitle.BUY_ROOMER_NUM)) {
            cList.add(new ClientDynamicModel(ReportEnum.StaticTitle.BUY_ROOMER_NUM.getName(), "求购客户",null,null));
        }
        if (staticTitle.contains(ReportEnum.StaticTitle.ALL_ROOMER_NUM)) {
            cList.add(new ClientDynamicModel(ReportEnum.StaticTitle.ALL_ROOMER_NUM.getName(), "客户总数",null,null));
        }
        if (staticTitle.contains(ReportEnum.StaticTitle.FOLLOWUP_ROOMER_NUM)) {
            cList.add(new ClientDynamicModel(ReportEnum.StaticTitle.FOLLOWUP_ROOMER_NUM.getName(), "客源跟进",null,null));
        }
        if (staticTitle.contains(ReportEnum.StaticTitle.FOLLOWUP_LOOK_ROOMER_NUM)) {
            cList.add(new ClientDynamicModel(ReportEnum.StaticTitle.FOLLOWUP_LOOK_ROOMER_NUM.getName(), "客源带看",null,null));
        }
        
        //**********业绩分成说明*************//
        if (staticTitle.contains(ReportEnum.StaticTitle.ALL_NUM)) {
            cList.add(new ClientDynamicModel(ReportEnum.StaticTitle.ALL_NUM.getName(), "总数"));
        }
        
        //**********成交金额*************//
        if (staticTitle.contains(ReportEnum.StaticTitle.SELL_SHEET_AMOUNT)) {
            cList.add(new ClientDynamicModel(ReportEnum.StaticTitle.SELL_SHEET_AMOUNT.getName(), "售单金额",null,null));
        }
        if (staticTitle.contains(ReportEnum.StaticTitle.RENT_SHEET_AMOUNT)) {
            cList.add(new ClientDynamicModel(ReportEnum.StaticTitle.RENT_SHEET_AMOUNT.getName(), "租单金额",null,null));
        }
        if (staticTitle.contains(ReportEnum.StaticTitle.ALL_SHEET_AMOUNT)) {
            cList.add(new ClientDynamicModel(ReportEnum.StaticTitle.ALL_SHEET_AMOUNT.getName(), "合计金额",null,null));
        }
    }
    
}
