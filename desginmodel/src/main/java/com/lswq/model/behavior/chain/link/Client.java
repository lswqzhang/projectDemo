package com.lswq.model.behavior.chain.link;

public class Client {

    public static void main(String args[]) {
        //生成三个权限的处理者对象
        Handler h1 = new ConcreteHandler1();
        Handler h2 = new ConcreteHandler2();
        Handler h3 = new ConcreteHandler3();

        //设置职责链后继者
        h1.setSuccesor(h2);
        h2.setSuccesor(h3);

        //生成1-30的数字，作为请求，交由handler处理
        int requests[] = new int[30];

        //初始化reqeusts数组
        for (int i = 0; i < requests.length; i++) {
            requests[i] = i;
        }

        for (int i = 0; i < requests.length; i++) {
            h1.handleRequest(requests[i]);
        }

    }

}
