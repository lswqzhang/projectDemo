package com.lswq.proxy.jdkproxy;

import com.lswq.proxy.service.UserService;
import com.lswq.proxy.service.impl.UserServiceImpl;

public class Main {

    public static void main(String[] args) {

        // 被真实代理的对象
        UserService proxyObject = new UserServiceImpl();
        // 生成动态代理的对象
        UserService userService = ProxyUtil.create(UserService.class, proxyObject);
        userService.addUser();
        userService.findUserById();

    }


}
