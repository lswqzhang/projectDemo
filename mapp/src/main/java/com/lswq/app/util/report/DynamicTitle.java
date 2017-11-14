package com.lswq.app.util.report;

/**
 * 
 * 非常规报表表头处理
 * @author zhangsw
 * @version V2.0
 */
public class DynamicTitle {
    //  报表分类维度
    private String type;
    //  报表显示的title与
    private String titleKey;
    //  报表显示的值
    private String titleDis;
    //  报表查询的值
    private String confValue;
    
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
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
    public String getConfValue() {
        return confValue;
    }
    public void setConfValue(String confValue) {
        this.confValue = confValue;
    }
}
