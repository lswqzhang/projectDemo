package com.lswq.model.behavior.chain.link;

public class ConcreteHandler2 extends Handler {

    @Override
    public void handleRequest(int request) {
        if (request > 10 && request <= 20) {                    //处理权限范围内的请求
            System.out.println("ConcreteHandler2 handle request = " + request);
        } else if (successor != null) {                            //不能处理的请求，交由权限更高的处理对象处理
            successor.handleRequest(request);
        }

    }
}
