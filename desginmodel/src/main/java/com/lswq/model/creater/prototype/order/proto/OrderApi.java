package com.lswq.model.creater.prototype.order.proto;

/**
 * 订单的接口，声明了可以克隆自身的方法
 */
public interface OrderApi {
    /**
     * 获取订单总数
     *
     * @return
     */
    int getOrderProductNum();

    /**
     * 设置订单总数
     *
     * @param num
     */
    void setOrderProductNum(int num);

    /**
     * 克隆方法
     *
     * @return 订单原型的实例
     */
    OrderApi cloneOrder();
}
