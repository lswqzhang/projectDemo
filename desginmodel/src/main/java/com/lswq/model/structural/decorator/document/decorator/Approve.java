package com.lswq.model.structural.decorator.document.decorator;

import com.lswq.model.structural.decorator.document.component.Document;

public class Approve extends Decorator {
    public Approve(Document document) {
        super(document);
        System.err.println("增加审批功能");
    }

    public void approver() {
        System.err.println("审批文件！");
    }
}
