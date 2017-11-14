package com.lswq.model.structural.decorator.aop.component.api;

import com.lswq.model.structural.decorator.aop.bean.SaleModel;
import com.lswq.model.structural.decorator.aop.component.GoodsSaleEbi;

/**
 * 定义基本的业务实现对象
 */
public class GoodsSaleEbo implements GoodsSaleEbi {
    @Override
    public boolean sale(String user, String customer, SaleModel saleModel) {
        System.out.println(user + "保存了" + customer + "购买 " + saleModel + " 的销售数据");
        return true;
    }
}
