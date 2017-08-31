package com.lswq.model.creater.builder.pcbuilder;

import com.lswq.model.creater.builder.pcbuilder.concreteproduct.MacBook;
import com.lswq.model.creater.builder.pcbuilder.concreteproduct.SurfacePro;

/**
 * @author zhangshaoweilq@163.com
 * <p>
 * 添加回炉方法的builder的设计模式
 */
public class Client {
    public static void main(String[] args) {
        ComputerProduct.Builder builder = new MacBook.Builder();
        Director director = new Director(builder);
        ComputerProduct macBook = director.buildPC("Inter Board", "Apple Display");

        ComputerProduct.Builder builder2 = new SurfacePro.Builder();
        director = new Director(builder2);
        ComputerProduct surfacePro = director.buildPC("AMD Board", "LG Display");

        System.out.println(builder.build().toString());
        System.out.println(builder2.build().toString());

        ComputerProduct new_apple_display = macBook.newBuilder().buildDisplay("New Apple Display").build();
        ComputerProduct new_lg_display = surfacePro.newBuilder().buildDisplay("New LG Display").build();

        System.out.println(new_apple_display.toString());
        System.out.println(new_lg_display.toString());
    }
}
