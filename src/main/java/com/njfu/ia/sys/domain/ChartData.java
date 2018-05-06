package com.njfu.ia.sys.domain;

import java.util.List;
import java.util.Map;

/**
 * 生成图标所用的数据
 */
public class ChartData {

    /**
     * x轴数据
     */
    private List<String> dateList;

    /**
     * 数据类型名称-数据列表
     */
    private Map<String, List<Double>> dataMap;

    public ChartData(List<String> dateList, Map<String, List<Double>> dataMap) {
        this.dateList = dateList;
        this.dataMap = dataMap;
    }

    public List<String> getDateList() {
        return dateList;
    }

    public void setDateList(List<String> dateList) {
        this.dateList = dateList;
    }

    public Map<String, List<Double>> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, List<Double>> dataMap) {
        this.dataMap = dataMap;
    }
}
