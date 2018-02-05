package com.lswq.proxy.proxy.staticproxy;

import com.lswq.proxy.proxy.ForumService;

public class TestForumService {

    public static void main(String[] args) {
        ForumService forumService = new ForumServiceImpl();
        forumService.removeForum(10);
        forumService.removeTopic(1012);
    }

}
