package com.njfu.wa.sys.enums;

/**
 * 数据/阈值/报警类型
 */
public enum DataTypeEnum {

    TEMPERATURE("1"),
    MOISTURE("2"),
    SOILTEMPERATURE("3"),
    SOILMOISTURE("4"),
    LIGHT("5"),
    CO2("6"),
    PH("7"),
    N("8"),
    P("9"),
    K("10"),
    HG("11"),
    PB("12");

    private String code;

    DataTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
