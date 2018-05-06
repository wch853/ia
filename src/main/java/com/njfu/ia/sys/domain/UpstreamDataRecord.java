package com.njfu.ia.sys.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.njfu.ia.sys.utils.Constants;

import java.util.Date;

/**
 * 上行数据记录
 */
public class UpstreamDataRecord {

    /**
     * 记录编号
     */
    private Integer id;

    /**
     * 来源终端设备编号
     */
    private Integer deviceId;

    /**
     * 来源区块编号
     */
    private Integer sectionId;

    /**
     * 数据类型
     */
    private Integer dataType;

    /**
     * 数据类型名称
     */
    private String dataTypeName;

    /**
     * 数据值
     */
    private Double value;

    /**
     * 上传时间
     */
    @JsonFormat(pattern = Constants.DATE_SECOND_FORMAT, timezone = Constants.DEFAULT_GMT)
    private Date receiveTime;

    /**
     * 记录时间
     */
    @JsonFormat(pattern = Constants.DATE_SECOND_FORMAT, timezone = Constants.DEFAULT_GMT)
    private Date recordTime;

    public UpstreamDataRecord() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public String getDataTypeName() {
        return dataTypeName;
    }

    public void setDataTypeName(String dataTypeName) {
        this.dataTypeName = dataTypeName;
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
}
