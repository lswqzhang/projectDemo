package com.lswq.javaio.rpcdemo.api;

public interface DemoI {
    String withReturn(String name);

    void noReturn(String name);

    String noArgument();
}
