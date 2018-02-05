package com.lswq.proxy.proxy.dynamicproxy;

import com.lswq.proxy.proxy.ForumService;

public class ForumServiceImpl implements ForumService {
    public void removeTopic(int topicId) {

        //①-1开始对该方法进行性能监视  
        System.out.println("模拟删除Topic记录:" + topicId);
        try {
            Thread.currentThread().sleep(20);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void removeForum(int forumId) {
        System.out.println("模拟删除Forum记录:" + forumId);
        try {
            Thread.currentThread().sleep(40);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
