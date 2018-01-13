package com.njfu.wa.sys.enums;

/**
 * 报警记录处理标志枚举
 */
public enum WarnRecordFlagEnum {

    /**
     * 未处理
     */
    UNHANDLE("0"),
    /**
     * 已处理
     */
    HANDLED("1"),
    /**
     * 已忽略
     */
    IGNORE("2");

    private String code;

    WarnRecordFlagEnum(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }
}