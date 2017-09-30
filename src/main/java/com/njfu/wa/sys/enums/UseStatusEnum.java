package com.njfu.wa.sys.enums;

/**
 * 存放使用状态及其描述
 */
public enum UseStatusEnum {
    UNUSE("0", "未使用"),
    INUSE("1", "使用中"),
    ERROR("2", "故障中");

    private String code;

    private String info;

    UseStatusEnum(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
