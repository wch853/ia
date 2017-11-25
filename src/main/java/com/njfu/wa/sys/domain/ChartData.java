package com.njfu.wa.sys.domain;

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
     * 数据类型-数据列表
     */
    private Map<String, List<Double>> dataMap;

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

    @Override
    public String toString() {
        return "ChartData{" +
                "dateList=" + dateList +
                ", dataMap=" + dataMap +
                '}';
    }
}
