package com.njfu.wa.sys.enums;

/**
 * 报警记录处理标志枚举
 */
public enum WarnRecordFlagEnum {

    UNHANDLE("0"),
    HANDLED("1"),
    IGNORE("2");

    private String code;

    WarnRecordFlagEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
