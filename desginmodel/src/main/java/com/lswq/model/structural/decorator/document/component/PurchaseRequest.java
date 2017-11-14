package com.lswq.model.structural.decorator.document.component;

public class PurchaseRequest implements Document {
    @Override
    public void display() {
        System.err.println("显示采购单！");
    }
}
