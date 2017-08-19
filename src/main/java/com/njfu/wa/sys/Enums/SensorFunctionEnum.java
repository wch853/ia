package com.njfu.wa.sys.Enums;

/**
 * 传感器作用
 */
public enum SensorFunctionEnum {
    TEMPERATURE(1, "温度传感器"),
    MOISTURE(2, "湿度传感器");

    private Integer code;

    private String type;

    SensorFunctionEnum(Integer code, String type) {
        this.code = code;
        this.type = type;
    }

    public Integer getCode() {
        return code;
    }

    public String getType() {
        return type;
    }
}
