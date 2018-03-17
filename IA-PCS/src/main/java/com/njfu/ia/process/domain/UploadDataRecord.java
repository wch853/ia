package com.njfu.ia.process.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 上行数据记录
 */
public class UploadDataRecord {

    /**
     * 记录编号
     */
    private Integer id;

    /**
     * 来源终端编号
     */
    private Integer endDeviceId;

    /**
     * 数据类型
     */
    private Integer dataType;

    /**
     * 数据值
     */
    private Double value;

    /**
     * 上传时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date receiveTime;

    /**
     * 记录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date recordTime;

    public UploadDataRecord() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEndDeviceId() {
        return endDeviceId;
    }

    public void setEndDeviceId(Integer endDeviceId) {
        this.endDeviceId = endDeviceId;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    @Override
    public String toString() {
        return "UploadDataRecord{" +
                "id=" + id +
                ", endDeviceId=" + endDeviceId +
                ", dataType=" + dataType +
                ", value=" + value +
                ", receiveTime=" + receiveTime +
                ", recordTime=" + recordTime +
                '}';
    }
}
