package com.lswq.model.creater.simplefactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhangsw on 2016/12/2.
 */
public class Client {

    private static final Logger LOGGER = LoggerFactory.getLogger(Client.class); 
    
    public static void main(String[] args) {
        //通过简单工厂来获取接口对象
        LOGGER.info("静态工厂或简单工厂，其主要功能是选择实现，即重要的为选择");
        Api api = Factory.createApi();
        api.operation("正在使用简单工厂");
    }
}
