package com.lswq.model.creater.builder.pcbuilder;

import com.lswq.model.creater.builder.pcbuilder.concreteproduct.MacBook;
import com.lswq.model.creater.builder.pcbuilder.concreteproduct.SurfacePro;

public class Client {
    public static void main(String[] args) {
        ComputerProduct.Builder builder = new MacBook.Builder();
        Director director = new Director(builder);
        director.buildPC("Inter Board", "Apple Display");

        ComputerProduct.Builder builder2 = new SurfacePro.Builder();
        director = new Director(builder2);
        director.buildPC("AMD Board", "LG Display");

        System.out.println(builder.build().toString());
        System.out.println(builder2.build().toString());
    }
}
