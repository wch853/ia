package com.njfu.wa.sys.domain;

import java.util.Date;

/**
 * 报警记录
 */
public class WarnRecord {

    // 记录编号
    private Integer id;

    // 来源大棚编号
    private String fieldId;

    // 报警类型
    private String warnType;

    // 报警值
    private Double warnVal;

    // 报警时间
    private Date warnTime;

    // 处理时间
    private Date handleTime;

    // 处理标志 0-未处理 1-已处理 2-已忽略
    private String flag;

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

    @Override
    public String toString() {
        return "WarnRecord{" +
                "id=" + id +
                ", fieldId='" + fieldId + '\'' +
                ", warnType='" + warnType + '\'' +
                ", warnVal=" + warnVal +
                ", warnTime=" + warnTime +
                ", handleTime=" + handleTime +
                ", flag='" + flag + '\'' +
                '}';
    }
}
