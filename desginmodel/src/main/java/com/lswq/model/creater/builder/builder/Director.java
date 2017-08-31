package com.lswq.model.creater.builder.builder;

public class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public Product construct() {
        builder.builderPartA();
        builder.builderPartB();
        builder.builderPartC();
        return builder.getResult();
    }
}
