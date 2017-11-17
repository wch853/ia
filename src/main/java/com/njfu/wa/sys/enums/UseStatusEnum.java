package com.njfu.wa.sys.enums;

/**
 * 存放使用状态及其描述
 */
public enum UseStatusEnum {
    UNUSE("0"),
    INUSE("1"),
    ERROR("2");

    private String code;

    UseStatusEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
