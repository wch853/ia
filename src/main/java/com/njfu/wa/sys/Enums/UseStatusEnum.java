package com.njfu.wa.sys.Enums;

/**
 * 存放使用状态及其描述
 */
public enum UseStatusEnum {
    UNUSE(0, "未使用"),
    INUSE(1, "使用中"),
    WRONG(2, "出现故障");

    private Integer code;

    private String info;

    UseStatusEnum(Integer code, String info) {
        this.code = code;
        this.info = info;
    }

    public Integer getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
