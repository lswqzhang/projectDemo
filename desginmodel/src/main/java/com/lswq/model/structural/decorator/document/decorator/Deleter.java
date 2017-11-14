package com.lswq.model.structural.decorator.document.decorator;

import com.lswq.model.structural.decorator.document.component.Document;

public class Deleter extends Decorator {
    public Deleter(Document document) {
        super(document);
        System.err.println("增加删除功能");
    }

    public void deleter() {
        System.err.println("删除文件！");
    }
}
