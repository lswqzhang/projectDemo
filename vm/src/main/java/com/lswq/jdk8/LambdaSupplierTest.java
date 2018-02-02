package com.lswq.jdk8;

import java.util.function.Supplier;

/**
 * jdk lambda Supplier 的使用
 *
 * @author zhangshaowei
 */
public class LambdaSupplierTest {

    public static void main(String[] args) {
        //简写
        Supplier<String> supplier1 = () -> "Test supplier";
        System.out.println(supplier1.get());

        //标准格式
        Supplier<Integer> supplier2 = () -> {
            return 20;
        };
        System.out.println(supplier2.get() instanceof Integer);
    }

}
