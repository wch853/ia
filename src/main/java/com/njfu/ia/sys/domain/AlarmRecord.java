package com.njfu.ia.sys.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.njfu.ia.sys.utils.Constants;

import java.util.Date;

/**
 * 报警记录
 */
public class AlarmRecord {

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
     * 数据阈值下限
     */
    private Double floor;

    /**
     * 数据阈值上限
     */
    private Double ceil;

    /**
     * 报警时间
     */
    @JsonFormat(pattern = Constants.DATE_SECOND_FORMAT, timezone = Constants.DEFAULT_GMT)
    private Date alarmTime;

    /**
     * 处理时间
     */
    @JsonFormat(pattern = Constants.DATE_SECOND_FORMAT, timezone = Constants.DEFAULT_GMT)
    private Date handleTime;

    /**
     * 处理状态 0-未处理 1-已处理 2-已忽略
     */
    private Integer handleFlag;

    public AlarmRecord() {
    }

    public AlarmRecord(UpstreamDataRecord record) {
        this.deviceId = record.getDeviceId();
        this.dataType = record.getDataType();
        this.value = record.getValue();
        this.alarmTime = record.getReceiveTime();
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

    public Double getFloor() {
        return floor;
    }

    public void setFloor(Double floor) {
        this.floor = floor;
    }

    public Double getCeil() {
        return ceil;
    }

    public void setCeil(Double ceil) {
        this.ceil = ceil;
    }

    public Date getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(Date alarmTime) {
        this.alarmTime = alarmTime;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public Integer getHandleFlag() {
        return handleFlag;
    }

    public void setHandleFlag(Integer handleFlag) {
        this.handleFlag = handleFlag;
    }
}
