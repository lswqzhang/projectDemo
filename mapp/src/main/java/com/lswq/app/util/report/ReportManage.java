package com.lswq.app.util.report;

import com.lswq.app.controller.report.ReportEnum;
import com.lswq.app.util.spring.SpringContextUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * 
 * 报表表头处理工具
 * @author zhangsw
 * @version V2.0
 */
public class ReportManage {

    
    private static final String PATTERN_YYYY_MM = "yyyy-MM";
    
    private static final String PATTERN_DD = "dd";
    
    private static final String DAY_START = " 00:00:00";
    
    private static final String DAY_END = " 23:59:59";

    private static final String PATTERN_YYYY_MM_DD = PATTERN_YYYY_MM + "-" + PATTERN_DD;
    
    private static final DateTimeFormatter DAY_DTF = DateTimeFormat.forPattern(PATTERN_YYYY_MM_DD);
    
    private static final DateTimeFormatter MM_DTF = DateTimeFormat.forPattern(PATTERN_YYYY_MM);
    
    
    public static class ClientTitle {
        private String totalStart;
        private String totalEnd;
        private List<ClientDynamicModel> title;
        public String getTotalStart() {
            return totalStart;
        }
        public void setTotalStart(String totalStart) {
            this.totalStart = totalStart;
        }
        public String getTotalEnd() {
            return totalEnd;
        }
        public void setTotalEnd(String totalEnd) {
            this.totalEnd = totalEnd;
        }
        public List<ClientDynamicModel> getTitle() {
            return title;
        }
        public void setTitle(List<ClientDynamicModel> title) {
            this.title = title;
        }
        
    }
    
    
    public static class ClientDynamicModel  {
        
        //  数据返回的   
        //  列名 as titleKey
        private String titleKey;
        //  前端需要显示的列头
        private String titleDis;
        //  查询开始的时间
        private String start;
        //  查询结束的时间
        private String end;
        //  物业参数分类
        private String secoundLevel;
        // 物业参数查询条件
        private String confValue;
        //  是否是查询条件
        private boolean flag;
        //  子标题
        private List<ClientDynamicModel> childTitle;
        
        //  非查询条件的构造方法        
        public ClientDynamicModel(String titleKey, String titleDis) {
            super();
            this.titleKey = titleKey;
            this.titleDis = titleDis;
            this.flag = false;
        }
        
        
        //  查询条件构造方法
        public ClientDynamicModel(String titleKey, String titleDis, String start, String end) {
            super();
            this.titleKey = titleKey;
            this.titleDis = titleDis;
            this.start = start;
            this.end = end;
            this.flag = true;
        }

        //  物业参数作为查询条件构造方法
        public ClientDynamicModel(String titleKey, String titleDis, String start, String end,String confValue) {
            super();
            this.titleKey = titleKey;
            this.titleDis = titleDis;
            this.start = start;
            this.end = end;
            this.confValue = confValue;
            this.flag = true;
        }
        
    //  物业参数作为查询条件构造方法
        public ClientDynamicModel(String titleKey, String titleDis, String start, String end,String secondLevel, String confValue) {
            super();
            this.titleKey = titleKey;
            this.titleDis = titleDis;
            this.start = start;
            this.end = end;
            this.secoundLevel = secondLevel;
            this.confValue = confValue;
            this.flag = true;
        }

        public String getTitleKey() {
            return titleKey;
        }
        public void setTitleKey(String titleKey) {
            this.titleKey = titleKey;
        }
        public String getTitleDis() {
            return titleDis;
        }
        public void setTitleDis(String titleDis) {
            this.titleDis = titleDis;
        }
        public String getStart() {
            return start;
        }
        public void setStart(String start) {
            this.start = start;
        }
        public String getEnd() {
            return end;
        }
        public void setEnd(String end) {
            this.end = end;
        }
        public boolean isFlag() {
            return flag;
        }
        public void setFlag(boolean flag) {
            this.flag = flag;
        }
        public String getSecoundLevel() {
            return secoundLevel;
        }
        public void setSecoundLevel(String secoundLevel) {
            this.secoundLevel = secoundLevel;
        }
        public String getConfValue () {
            return confValue;
        }

        public void setConfValue ( String confValue ) {
            this.confValue = confValue;
        }


        public List<ClientDynamicModel> getChildTitle() {
            return childTitle;
        }


        public void setChildTitle(List<ClientDynamicModel> childTitle) {
            this.childTitle = childTitle;
        }
        
    }
    
    
    /**
     * 
     * 前端动态报表表头
     *
     * @author zhangsw
     * @param staticTitle       需要的固定表头 
     * @param type              日报、周报、月报
     * @param start             开始时间
     * @param span              时间跨度
     * @return
     * 		Map<String,String>
     */
    public static ClientTitle getClientDynamicTitle(Set<ReportEnum.StaticTitle> staticTitle, ReportEnum.ReportType type, Date start, int span){
        
        ClientTitle title = new ClientTitle();
        
        List<ClientDynamicModel> cList = new CopyOnWriteArrayList<>();
        
        ReportTitleManage.beforeTitle(staticTitle, cList);
        SimpleDateFormat daySdf = new SimpleDateFormat(PATTERN_YYYY_MM_DD);
        String format = daySdf.format(start);
        DateTime dateTime = DAY_DTF.parseDateTime(format);
        
        title.setTotalStart(dateTime.toString(DAY_DTF) + DAY_START);
        
        //  日报表头处理
        if (ReportEnum.ReportType.DAY_REPORT.equals(type)) {
            
            title.setTotalEnd(dateTime.plusDays(span - 1).toString(DAY_DTF) + DAY_END);
            
            dateTime = dateTime.minusDays(1);
            for (int i = 0; i < span; i++) {
                dateTime = dateTime.plusDays(1);
                String forMat = dateTime.toString(DAY_DTF);
                cList.add(new ClientDynamicModel(forMat.replaceAll("-", ""), forMat, forMat + DAY_START, forMat + DAY_END));
            }
            
        }
        
        //  周报表头处理
        if (ReportEnum.ReportType.WEEK_REPORT.equals(type)) {
            
            
            
            // 7为一周的天数
            StringBuilder time = new StringBuilder();
            
            StringBuilder startTime = new StringBuilder();
            
            startTime.append(dateTime.toString(DAY_DTF));
            
            time.append(dateTime.toString("MMdd"));
            int endWeek = 7 - dateTime.getDayOfWeek();
            //  先添加至周末
            //  已经包含当前周，因此只需要添加span-1个周就可以了
            title.setTotalEnd(dateTime.plusDays(endWeek).plusWeeks(span - 1).toString(DAY_DTF) + DAY_END);
            dateTime = dateTime.plusDays(endWeek);
            String endTime = dateTime.toString(DAY_DTF);
            time.append("-");
            time.append(dateTime.toString("dd"));
            String timeDis = time.toString();
            cList.add(new ClientDynamicModel(timeDis.replaceAll("-", ""), timeDis, startTime + DAY_START, endTime + DAY_END));
            
            
            for (int i = 0; i < span - 1; i++) {
                
                time = new StringBuilder();
                startTime = new StringBuilder();
                
                time.append(dateTime.plusDays(1).toString("MMdd"));

                startTime.append(dateTime.plusDays(1).toString(DAY_DTF));
                dateTime = dateTime.plusWeeks(1);
                time.append("-");
                
                endTime = dateTime.toString(DAY_DTF);
                time.append(dateTime.toString("dd"));
                timeDis = time.toString();
                
                cList.add(new ClientDynamicModel(timeDis.replaceAll("-", ""), timeDis, startTime + DAY_START, endTime + DAY_END));
                
            }
            
        }

        if (ReportEnum.ReportType.MONTH_REPORT.equals(type)) {
            //  包含当前月，因此只需要增加span-1个月就可以了
            title.setTotalEnd(dateTime.plusMonths(span - 1).dayOfMonth().withMaximumValue().toString(DAY_DTF) + DAY_END);
            
            String timeDis = dateTime.toString(MM_DTF);
            String startTime;
            DateTime endOfMonth = dateTime.dayOfMonth().withMaximumValue();
            
            cList.add(new ClientDynamicModel(timeDis.replaceAll("-", ""), timeDis, dateTime.toString(DAY_DTF) + DAY_START, endOfMonth.toString(PATTERN_YYYY_MM_DD) + DAY_END));
            
            for (int i = 0; i < span - 1; i++) {
                dateTime = dateTime.plusMonths(1);
                startTime = dateTime.toString(MM_DTF);
                endOfMonth = dateTime.dayOfMonth().withMaximumValue();
                timeDis = dateTime.toString(MM_DTF);
                cList.add(new ClientDynamicModel(timeDis.replaceAll("-", ""), timeDis, startTime + "-01" + DAY_START, endOfMonth.toString(PATTERN_YYYY_MM_DD) + DAY_END));
            }
        }
        
        
        ReportTitleManage.afterTitle(staticTitle, cList);
        
        title.setTitle(cList);
        
        return title;
    }
    
    
    


    
    
    
    /**
     * 
     * 后台动态表头,根据物业参数生成动态表头
     *
     * @author zhangsw
     * @param reportTypes       需要的固定表头
     * @param secondLevels      统计维度,使用物业参数的枚举，顺序要和界面保持一致
     * @return
     * 		Map<String,String>
     */
    public static ClientTitle getServerDynamicTitle(Set<ReportEnum.StaticTitle> staticTitle, List<SecondLevelClassification> secondLevels ,String startDate,String endDate){
        
        ClientTitle title = new ClientTitle();
        title.setTotalStart(startDate + DAY_START);
        title.setTotalEnd(endDate + DAY_END);
         
        List<ClientDynamicModel> cList = new CopyOnWriteArrayList<>();
        
        ReportTitleManage.beforeTitle(staticTitle, cList);
        
        IConfParamService iConf = (IConfParamService) SpringContextUtil.getBean("confParamService");
        
        Map<String, List<ConfParamVal>> confParam = iConf.getConfParamsList(secondLevels);
        
        Set<Entry<String,List<ConfParamVal>>> entrySet = confParam.entrySet();
        Iterator<Entry<String, List<ConfParamVal>>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Entry<String,List<ConfParamVal>> entry = iterator.next();
            List<ConfParamVal> confParams = entry.getValue();
            for (ConfParamVal conf : confParams) {
                cList.add(new ClientDynamicModel(conf.getValue() + "_" + conf.getKey(), conf.getValue(), startDate + DAY_START, endDate + DAY_END, entry.getKey(), conf.getKey()));
            }
        }
        
        ReportTitleManage.afterTitle(staticTitle, cList);
        
        title.setTitle(cList);
        
        return title;
    }
    
    
    
    
    /**
     * 
     * 后台动态表头,根据物业参数生成动态表头
     *
     * @author zhangsw
     * @param secondLevel       需要的固定表头
     * @param secondLevel      统计维度,使用物业参数的枚举
     * @param paramKey 动态连接传参key
     * @return
     *      Map<String,String>
     */
    public static ClientTitle getServerDynamicTitle(Set<ReportEnum.StaticTitle> staticTitle,
            SecondLevelClassification secondLevel, String startDate, String endDate, String paramKey) {
        
        ClientTitle title = new ClientTitle();
        title.setTotalStart(startDate + DAY_START);
        title.setTotalEnd(endDate + DAY_END);
         
        List<ClientDynamicModel> cList = new CopyOnWriteArrayList<>();
        
        ReportTitleManage.beforeTitle(staticTitle, cList);
        
        IConfParamService iConf = (IConfParamService) SpringContextUtil.getBean("confParamService");
        
        List<ConfParamVal> confParams = iConf.getConfParams(secondLevel);
        
        for (ConfParamVal conf : confParams) {
            cList.add(new ClientDynamicModel(
                    StringUtils.isBlank(paramKey) ? conf.getValue() + "_" + conf.getKey() : paramKey + "_" + conf.getKey(), conf.getValue(),
                    startDate + DAY_START, endDate + DAY_END, conf.getKey()));
        }
        
        ReportTitleManage.afterTitle(staticTitle, cList);
        
        title.setTitle(cList);
        
        return title;
    }
    
public static ClientTitle getServerDynamicTitle(Set<ReportEnum.StaticTitle> staticTitle,String startDate,String endDate){
        
        ClientTitle title = new ClientTitle();
        title.setTotalStart(startDate + DAY_START);
        title.setTotalEnd(endDate + DAY_END);
         
        List<ClientDynamicModel> cList = new CopyOnWriteArrayList<>();
        
        ReportTitleManage.beforeTitle(staticTitle, cList);
        
        ReportTitleManage.afterTitle(staticTitle, cList);
        
        title.setTitle(cList);
        
        return title;
    }
    
    
    
    /**
     * 
     * 后台动态表头，非物业参数生成表头功能处理
     *
     * @author zhangsw
     * @param secondLevel       需要的固定表头
     * @param secondLevel      统计维度,使用物业参数的枚举
     * @return
     *      Map<String,String>
     */
    public static ClientTitle getDynamicTitle(Set<ReportEnum.StaticTitle> staticTitle, List<ErpRole> roles ,String startDate,String endDate){
        
        ClientTitle title = new ClientTitle();
        title.setTotalStart(startDate + DAY_START);
        title.setTotalEnd(endDate + DAY_END);
         
        List<ClientDynamicModel> cList = new CopyOnWriteArrayList<>();
        
        ReportTitleManage.beforeTitle(staticTitle, cList);
        
        for (ErpRole dynamic : roles) {
            cList.add(new ClientDynamicModel(dynamic.getRoleName() + "_" + dynamic.getId(), dynamic.getRoleName(), startDate + DAY_START, endDate + DAY_END, String.valueOf(dynamic.getId())));
        }
        
        
        ReportTitleManage.afterTitle(staticTitle, cList);
        
        title.setTitle(cList);
        
        return title;
    }

    /**
     * 动态获取物业参数列头
     * @param staticTitle
     * @param type
     * @return
     */
    @Deprecated
    public static ClientTitle getClientConfParamTitle (Set<ReportEnum.StaticTitle> staticTitle, ReportEnum.ReportType type ,String startDate,String endDate) {
        ClientTitle title = new ClientTitle();
        title.setTotalStart(startDate+" 00:00:00");
        title.setTotalEnd(endDate+" 23:59:59");
        List<ClientDynamicModel> cList = new CopyOnWriteArrayList<>();
        ReportTitleManage.beforeTitle(staticTitle, cList);// 固定列头(部门/员工)
        String[] roomerOptions = { SecondLevelClassification.getOption(type.getName()).toString() };
        Map<String, List<ConfParamVal>> confMap = OptionMapUtils.getRoomerOptionMap(roomerOptions, false);// 获取动态物业参数
        for (Entry<String, List<ConfParamVal>> entry : confMap.entrySet()) {
            if (entry.getValue() != null && entry.getValue() instanceof List) {
                for (Iterator<ConfParamVal> iterator = entry.getValue().iterator(); iterator.hasNext(); ) {
                    ConfParamVal conf = iterator.next();
                    cList.add(new ClientDynamicModel(conf.getValue() + "_" + conf.getKey(), conf.getValue(),startDate + " " + "00:00:00", endDate + "" + " 23:59:59",conf.getKey()));
                }
            }
        }
        ReportTitleManage.afterTitle(staticTitle, cList);// 固定列头(总计/名次)
        title.setTitle(cList);
        return title;
    }
    
    
}
