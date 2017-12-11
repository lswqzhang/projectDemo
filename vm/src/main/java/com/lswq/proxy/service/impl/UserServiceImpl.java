package com.lswq.proxy.service.impl;

import com.lswq.proxy.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public void addUser() {
        System.out.println("start insert user into database");
    }

    @Override
    public String findUserById() {
        System.out.println("start find user by userId");
        return "user";
    }
}
