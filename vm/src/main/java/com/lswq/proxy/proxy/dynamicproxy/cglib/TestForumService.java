package com.lswq.proxy.proxy.dynamicproxy.cglib;

import com.lswq.proxy.proxy.dynamicproxy.ForumServiceImpl;

public class TestForumService {

    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        ForumServiceImpl forumService = (ForumServiceImpl) proxy.getProxy(ForumServiceImpl.class);
        forumService.removeForum(10);
        forumService.removeTopic(1023);
    }

}
