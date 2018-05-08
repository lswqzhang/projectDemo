package com.lswq.enums;

public enum  DataStateEnum implements BaseCodeEnum {
    
    DELETE(0, "数据删除");

    private Integer code;
    private String desc;

    DataStateEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }


}
