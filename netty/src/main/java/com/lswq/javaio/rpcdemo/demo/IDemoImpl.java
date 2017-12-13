package com.lswq.javaio.rpcdemo.demo;

import com.lswq.javaio.rpcdemo.api.DemoI;

public class IDemoImpl implements DemoI {
    @Override
    public String withReturn(String name) {
        System.out.println("withReturn " + name);
        return "hello " + name;
    }

    @Override
    public void noReturn(String name) {
        System.out.println("noReturn " + name);
    }

    @Override
    public String noArgument() {
        System.out.println("noArgument");
        return "noArgument";
    }
}
