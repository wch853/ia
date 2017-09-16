package com.njfu.wa.sys.enums;

/**
 * 存放结果代码及其类型描述
 */
public enum ResultEnum {
    SUCCESS(100, "success"),
    FAIL(200, "fail"),
    DATA(101, "data");

    // 结果代码
    private Integer code;

    // 结果类型
    private String info;

    ResultEnum(Integer code, String info) {
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
