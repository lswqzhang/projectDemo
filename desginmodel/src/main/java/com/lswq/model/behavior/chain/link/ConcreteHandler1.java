package com.lswq.model.behavior.chain.link;

public class ConcreteHandler1 extends Handler {


    @Override
    public void handleRequest(int request) {
        if (request >= 0 && request <= 10) {                //在自己的权限范围内，自己处理
            System.out.println("ConcreteHandler1 handle request = " + request);
        } else if (successor != null) {                        //超过自己的权限，如果有更高权限对象，交由其处理
            successor.handleRequest(request);
        }

    }


}
