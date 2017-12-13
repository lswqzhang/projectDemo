package com.lswq.proxy.googleutilproxy;

import com.lswq.proxy.service.UserService;
import com.lswq.proxy.service.impl.UserServiceImpl;

public class Main {

    public static void main(String[] args) {
        UserService userService = GoogleProxyUtil.create(UserService.class, new UserServiceImpl());
        userService.addUser();
        userService.findUserById();
    }
    
}
