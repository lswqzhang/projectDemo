package com.lswq.model.structural.decorator.document;

import com.lswq.model.structural.decorator.document.component.Document;
import com.lswq.model.structural.decorator.document.component.PurchaseRequest;
import com.lswq.model.structural.decorator.document.decorator.Approve;

/**
 * 半透明的装饰模式，组件实现类尽量是轻类
 */
public class Client {
    public static void main(String[] args) {
        Document doc = new PurchaseRequest();
        Approve appDoc = new Approve(doc);
        appDoc.display();
        appDoc.approver();
    }
}
