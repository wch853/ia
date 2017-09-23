package com.njfu.wa.sys.enums;

/**
 * 报警记录处理标志枚举
 */
public enum WarnRecordFlagEnum {

    UNHANDLE("0", "未处理"),
    HANDLED("1", "已处理"),
    IGNORE("2", "已忽略");

    private String code;

    private String info;

    WarnRecordFlagEnum(String code, String info) {
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
