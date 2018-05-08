package com.lswq.enums;

public enum PersonStatusEnum implements BaseCodeEnum {

    INIT(1, "人员初始化");

    private int code;
    private String desc;

    PersonStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }


}
