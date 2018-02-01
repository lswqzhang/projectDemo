package com.lswq.proxy.proxy.dynamicproxy.jdk;

import com.lswq.proxy.proxy.ForumService;
import com.lswq.proxy.proxy.dynamicproxy.ForumServiceImpl;

public class TestForumService {
    public static void main(String[] args) {

        //①希望被代理的目标业务类  
        ForumService target = new ForumServiceImpl();

        //②将目标业务类和横切代码编织到一起
        ForumService proxy = PerformanceHandler.newInstance(target);

        //④调用代理实例  
        proxy.removeForum(10);
        proxy.removeTopic(1012);
    }
}
