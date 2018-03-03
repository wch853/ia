package com.njfu.ia.sys.enums;

/**
 * 传感器作用
 */
public enum SensorFunctionEnum {

    TEMPERATURE("1"),
    MOISTURE("2");

    private String code;

    SensorFunctionEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
