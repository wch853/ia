package com.njfu.wa.sys.domain;

import java.util.Date;

/**
 * 传感器数据记录
 */
public class DataRecord {

    /**
     * 记录编号
     */
    private Integer id;

    /**
     * 来源传感器编号
     */
    private String sensor_id;

    /**
     * 数据类型
     * 1-温度temperature 2-湿度moisture 3-土壤温度 soil_temperature
     * 4-土壤水分soil_moisture 5-光照 light 6-二氧化碳 co2 ' 7-pH ph
     * 8-氮含量n 9-磷含量p 10-钾含量k 11-汞含量hg 12-铅含量pb
     */
    private String dataType;

    /**
     * 数据值
     */
    private Double val;

    /**
     * 记录时间
     */
    private Date recordTime;

    public DataRecord() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSensor_id() {
        return sensor_id;
    }

    public void setSensor_id(String sensor_id) {
        this.sensor_id = sensor_id;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Double getVal() {
        return val;
    }

    public void setVal(Double val) {
        this.val = val;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    @Override
    public String toString() {
        return "DataRecord{" +
                "id=" + id +
                ", sensor_id='" + sensor_id + '\'' +
                ", dataType='" + dataType + '\'' +
                ", val=" + val +
                ", recordTime=" + recordTime +
                '}';
    }
}
