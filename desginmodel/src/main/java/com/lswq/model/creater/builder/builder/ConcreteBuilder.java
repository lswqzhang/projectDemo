package com.lswq.model.creater.builder.builder;

public class ConcreteBuilder extends Builder {
    
    
    @Override
    public void builderPartA() {
        product.setPartA("A");
    }

    @Override
    public void builderPartB() {
        product.setPartB("B");
    }

    @Override
    public void builderPartC() {
        product.setPartC("C");
    }
}
