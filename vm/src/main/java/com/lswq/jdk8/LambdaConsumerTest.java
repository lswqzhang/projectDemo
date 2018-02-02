package com.lswq.jdk8;

import java.util.function.Consumer;

/**
 * jdk lambda Consumer 的使用
 *
 * @author zhangshaowei
 */
public class LambdaConsumerTest {

    public static void main(String[] args) {
        Consumer<String> consumer1 = (x) -> System.out.print(x);
        Consumer<String> consumer2 = (x) -> {
            System.out.println(" after consumer 1");
        };
        consumer1.andThen(consumer2).accept("test consumer1");
    }
}
