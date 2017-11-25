package com.njfu.wa.sys.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据/阈值/报警类型
 */
public enum DataTypeEnum {

    TEMPERATURE,
    MOISTURE,
    SOIL_TEMPERATURE,
    SOIL_MOISTURE,
    LIGHT,
    CO2,
    PH,
    N,
    P,
    K,
    HG,
    PB;

    private static Map<String, DataTypeEnum> reflectMap = new HashMap<>();

    static {
        reflectMap.put("1", TEMPERATURE);
        reflectMap.put("2", MOISTURE);
        reflectMap.put("3", SOIL_TEMPERATURE);
        reflectMap.put("4", SOIL_MOISTURE);
        reflectMap.put("5", LIGHT);
        reflectMap.put("6", CO2);
        reflectMap.put("7", PH);
        reflectMap.put("8", N);
        reflectMap.put("9", P);
        reflectMap.put("10", K);
        reflectMap.put("11", HG);
        reflectMap.put("12", PB);
    }

    public static Map<String, DataTypeEnum> getReflectMap() {
        return reflectMap;
    }
}
