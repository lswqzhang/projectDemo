package com.lswq.model.structural.decorator.aop.decorator;

import com.lswq.model.structural.decorator.aop.bean.SaleModel;
import com.lswq.model.structural.decorator.aop.component.GoodsSaleEbi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日志
 */
public class LogDecorator extends Decorator {

    /**
     * 
     * @param ebi
     */
    public LogDecorator(GoodsSaleEbi ebi) {
        super(ebi);
    }

    @Override
    public boolean sale(String user, String customer, SaleModel saleModel) {
        //执行业务功能  
        boolean f = this.ebi.sale(user, customer, saleModel);
        //在执行业务功能过后，记录日志  
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        System.out.println("日志记录：" + user + "于" + df.format(new Date()) + "时保存了一条销售记录，客户是" + customer + ",购买记录是" + saleModel);
        return f;
    }
}
