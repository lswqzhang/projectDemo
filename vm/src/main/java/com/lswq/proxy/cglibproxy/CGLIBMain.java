package com.lswq.proxy.cglibproxy;

import com.lswq.proxy.service.impl.UserServiceImpl;

public class CGLIBMain {
    /**
     * @param args
     */
    public static void main(String[] args) {
        //生成子类，创建代理类  
        UserServiceImpl proxy = CGLIBProxy.getProxy(UserServiceImpl.class);
        proxy.addUser();
        proxy.findUserById();
    }
}
