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

    public static Map<String, String> reflectMap = new HashMap<>();

    static {
        reflectMap.put("1", "温度");
        reflectMap.put("2", "湿度");
        reflectMap.put("3", "土壤温度");
        reflectMap.put("4", "土壤湿度");
        reflectMap.put("5", "光照度");
        reflectMap.put("6", "二氧化碳");
        reflectMap.put("7", "pH值");
        reflectMap.put("8", "氮含量");
        reflectMap.put("9", "磷含量");
        reflectMap.put("10", "钾含量");
        reflectMap.put("11", "汞含量");
        reflectMap.put("12", "铅含量");
    }
}
