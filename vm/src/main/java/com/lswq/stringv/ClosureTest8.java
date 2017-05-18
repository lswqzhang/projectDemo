package com.lswq.stringv;

/**
 * Created by zhangshaowei on 2017/5/11.
 */
public class ClosureTest8 {
    public static Supplier<Integer> testClosure() {
        int i = 1;
        return () -> i;
    }

    public interface Supplier<T> {
        T get();
    }

    public static void main(String[] args) {
        System.out.println(testClosure().get());
    }
}
