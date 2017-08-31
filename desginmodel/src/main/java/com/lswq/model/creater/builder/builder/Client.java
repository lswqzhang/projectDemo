package com.lswq.model.creater.builder.builder;

public class Client {
    public static void main(String[] args) {
        Builder builder = new ConcreteBuilder();
        Director director = new Director(builder);
        Product construct = director.construct();
        System.err.println("product = " + construct);
    }
}


