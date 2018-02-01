package com.lswq.jdk8;

import java.util.function.Function;

/**
 * jdk lambda function 的使用
 *
 * @author zhangshaowei
 */
public class LambdaFunctionTest {

    public static void main(String[] args) {
        //简单的,只有一行
        Function<Integer, String> function1 = (x) -> "test result: " + x;

        //标准的,有花括号, return, 分号.
        Function<String, String> function2 = (x) -> {
            return "after function1";
        };

        System.out.println(function1.apply(6));
        System.out.println(function1.andThen(function2).apply(6));
    }

}
