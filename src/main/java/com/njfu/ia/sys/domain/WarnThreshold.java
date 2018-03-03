package com.njfu.ia.sys.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 报警阈值
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WarnThreshold {

    /**
     * 阈值编号
     */
    private Integer id;

    /**
     * 阈值类型
     */
    private String thresholdType;

    /**
     * 阈值下限
     */
    private Double floor;

    /**
     * 阈值上限
     */
    private Double ceil;

    /**
     * 使用状态
     */
    private String useStatus;

    public WarnThreshold() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getThresholdType() {
        return thresholdType;
    }

    public void setThresholdType(String thresholdType) {
        this.thresholdType = thresholdType;
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

    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
    }

    @Override
    public String toString() {
        return "WarnThreshold{" +
                "id=" + id +
                ", thresholdType='" + thresholdType + '\'' +
                ", floor=" + floor +
                ", ceil=" + ceil +
                ", useStatus='" + useStatus + '\'' +
                '}';
    }
}
