package com.lswq.model.creater.prototype.order;

import com.lswq.model.creater.prototype.order.biz.OrderBusiness;
import com.lswq.model.creater.prototype.order.concrete.EnterpriseOrder;
import com.lswq.model.creater.prototype.order.concrete.PersonalOrder;
import com.lswq.model.creater.prototype.order.manager.PrototypeManager;

public class Client {
    public static void main(String[] args) {


        //先创建原型实例
        PersonalOrder personalOrder = new PersonalOrder();

        personalOrder.setOrderProductNum(10001);
        personalOrder.setCustomerName("liuqian");
        personalOrder.setProductId("123");
        PrototypeManager.setPrototype("personalOrder", personalOrder);

        OrderBusiness business = new OrderBusiness();
        business.saveOrder("personalOrder");


        EnterpriseOrder enterpriseOrder = new EnterpriseOrder();
        enterpriseOrder.setEnterpriseName("liubiz");
        enterpriseOrder.setOrderProductNum(11111);
        enterpriseOrder.setProductId("1234");
        PrototypeManager.setPrototype("enterpriseOrder", enterpriseOrder);
        business.saveOrder("enterpriseOrder");
    }
}
