package com.lswq.app.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangsw on 2016/12/29.
 */
public class ResultMessage {
    
    
    
    /**
     * @param resultCode    返回状态的码 0:成功，10001：系统异常，其他的状态码可以根据情况来设计
     * @param resultMessage 提示语言
     * @param resultInfo    传递的对象，可以为null，对象
     * @return Map 返回类型
     * @Title: getAppMap
     * @Description: TODO(返回APP接口使用使用的返回数据)
     */
    public static Map<String, Object> getAppMap(Integer resultCode, String resultMessage, Object resultInfo) {
        Map<String, Object> map = new HashMap();
        map.put("resultCode", resultCode);
        map.put("resultMessage", resultMessage);
        map.put("resultInfo", resultInfo);
        return map;
    }


    /**
     * @param resultCode    返回状态的码 0:成功，10001：系统异常，其他的状态码可以根据情况来设计
     * @param resultMessage 提示语言
     * @return Map 返回类型
     * @Title: getAppMap
     * @Description: TODO(返回APP接口使用使用的返回数据)
     */
    public static Map<String, Object> getAppMap(Integer resultCode, String resultMessage) {
        return getAppMap(resultCode, resultMessage, "");
    }
}
