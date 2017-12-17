package com.lswq.model.behavior.chain.link;

public abstract class Handler {

    public Handler successor;                            //后继者，或权限更高的对象

    public abstract void handleRequest(int request);    //处理客户端请求

    public void setSuccesor(Handler successor) {            //设置后继者，或者更高权限的对象
        this.successor = successor;
    }

}
