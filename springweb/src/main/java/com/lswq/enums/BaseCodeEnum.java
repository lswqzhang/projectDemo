package com.lswq.enums;

public interface BaseCodeEnum {
    /**
     * 该接口只有一个返回编码的方法，返回值将被存入数据库。
     *
     * @return
     */
    int getCode();

    /**
     * 获取描述信息
     *
     * @return
     */
    String getDesc();
}
