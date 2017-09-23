package com.njfu.wa.sys.enums;

/**
 * 传感器作用
 */
public enum SensorFunctionEnum {

    TEMPERATURE("1", "温度传感器"),
    MOISTURE("2", "湿度传感器");

    private String code;

    private String type;

    SensorFunctionEnum(String code, String type) {
        this.code = code;
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }
}
