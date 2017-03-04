package com.lswq.model.creater.factorymethod;

import com.lswq.model.creater.factorymethod.method.ExportOperateCreator;
import com.lswq.model.creater.factorymethod.specific.ExportDBOperateCreator;

/**
 * Created by zhangsw on 2017/2/26.
 */
public class Client {
    public static void main(String[] args) {
        //创建需要使用的Creator对象
        ExportOperateCreator operate = new ExportDBOperateCreator();
        //调用输出数据的功能方法
        operate.export("测试数据");
    }
}
