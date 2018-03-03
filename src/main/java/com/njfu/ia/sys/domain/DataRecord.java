package com.njfu.ia.sys.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

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
    private String sensorId;

    /**
     * 所属大棚编号
     */
    private String fieldId;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date recordTime;

    public DataRecord() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
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
                ", sensorId='" + sensorId + '\'' +
                ", fieldId='" + fieldId + '\'' +
                ", dataType='" + dataType + '\'' +
                ", val=" + val +
                ", recordTime=" + recordTime +
                '}';
    }
}
