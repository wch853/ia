package com.njfu.wa.sys.enums;

/**
 * 数据/阈值/报警类型
 */
public enum DataTypeEnum {

    TEMPERATURE("1", "temperature"),
    MOISTURE("2", "moisture"),
    SOILTEMPERATURE("3", "soilTemperature"),
    SOILMOISTURE("4", "soilMoisture"),
    LIGHT("5", "light"),
    CO2("6", "co2"),
    PH("7", "ph"),
    N("8", "n"),
    P("9", "p"),
    K("10", "k"),
    HG("11", "hg"),
    PB("12", "pb");

    private String code;

    private String type;

    DataTypeEnum(String code, String type) {
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
