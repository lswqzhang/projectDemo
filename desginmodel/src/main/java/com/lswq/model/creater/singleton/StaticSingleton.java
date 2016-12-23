package com.lswq.model.creater.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhangsw on 2016/12/1.
 *
 * 这是一个比较经典的单例模式，以后的正式开发中可以使用此单例
 */
public class StaticSingleton {

    private static final Logger logger = LoggerFactory.getLogger(StaticSingleton.class);

    /**
     * 单例，防止用户进行实例化此类
     */
    private StaticSingleton() {
        logger.info("StaticSingleton is create");
    }


    /**
     * 单例的保有静态内部类
     */
    private static class SingletonHolder {
        private static StaticSingleton instance = new StaticSingleton();
    }

    /**
     * 提供单例方法
     * @return
     */
    public static StaticSingleton getInstance() {
        return SingletonHolder.instance;
    }

}
