package com.lswq.model.behavior.chain.link;

public class ConcreteHandler3 extends Handler {

    @Override
    public void handleRequest(int request) {
        if (request > 20 && request <= 30) {
            System.out.println("ConcreteHandler3 handle request = " + request);
        } else if (successor != null) {
            successor.handleRequest(request);
        }

    }


}
