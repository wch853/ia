package com.njfu.wa.sys.enums;

/**
 * 存放结果代码
 */
public enum ResultEnum {

    SUCCESS(200),
    DATA(201),
    FAIL(400),
    WARN(301);

    /**
     * 结果代码
     */
    private Integer code;

    ResultEnum(Integer code) {
        this.code = code;
    }

    public Integer code() {
        return code;
    }
}
