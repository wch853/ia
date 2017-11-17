package com.njfu.wa.sys.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 报警记录
 */
public class WarnRecord {

    /**
     * 记录编号
     */
    private Integer id;

    /**
     * 来源大棚编号
     */
    private String fieldId;

    /**
     * 报警类型
     */
    private String warnType;

    /**
     * 报警类型
     */
    private Double warnVal;

    // 最近报警时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date warnTime;

    /**
     * 报警计数
     */
    private Integer warnCount;

    /**
     * 处理时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date handleTime;

    /**
     * 处理标志 0-未处理 1-已处理 2-已忽略
     */
    private String flag;

    /**
     * 对应阈值信息
     */
    private WarnThreshold warnThreshold;

    public WarnRecord() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getWarnType() {
        return warnType;
    }

    public void setWarnType(String warnType) {
        this.warnType = warnType;
    }

    public Double getWarnVal() {
        return warnVal;
    }

    public void setWarnVal(Double warnVal) {
        this.warnVal = warnVal;
    }

    public Date getWarnTime() {
        return warnTime;
    }

    public void setWarnTime(Date warnTime) {
        this.warnTime = warnTime;
    }

    public Integer getWarnCount() {
        return warnCount;
    }

    public void setWarnCount(Integer warnCount) {
        this.warnCount = warnCount;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public WarnThreshold getWarnThreshold() {
        return warnThreshold;
    }

    public void setWarnThreshold(WarnThreshold warnThreshold) {
        this.warnThreshold = warnThreshold;
    }

    @Override
    public String toString() {
        return "WarnRecord{" +
                "id=" + id +
                ", fieldId='" + fieldId + '\'' +
                ", warnType='" + warnType + '\'' +
                ", warnVal=" + warnVal +
                ", warnTime=" + warnTime +
                ", warnCount=" + warnCount +
                ", handleTime=" + handleTime +
                ", flag='" + flag + '\'' +
                ", warnThreshold=" + warnThreshold +
                '}';
    }
}
