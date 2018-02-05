package com.lswq.proxy.proxy;

/**
 * 1）目标类的所有方法都添加了性能监视横切逻辑，而有时，这并不是我们所期望的，我们可能只希望对业务类中的某些特定方法添加横切逻辑；
 * 2）我们通过硬编码的方式指定了织入横切逻辑的织入点，即在目标类业务方法的开始和结束前织入代码；
 * 3）我们手工编写代理实例的创建过程，为不同类创建代理时，需要分别编写相应的创建代码，无法做到通用。
 */
public interface ForumService {


    void removeTopic(int topicId);


    void removeForum(int forumId);
}
