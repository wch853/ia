package com.njfu.wa.sys.domain;

/**
 * 报警阈值
 */
public class WarnThreshold {

    // 阈值编号
    private Integer id;

    // 阈值类型
    private String thresholdType;

    // 阈值上限
    private Double ceil;

    // 阈值下限
    private Double floor;

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

    public Double getCeil() {
        return ceil;
    }

    public void setCeil(Double ceil) {
        this.ceil = ceil;
    }

    public Double getFloor() {
        return floor;
    }

    public void setFloor(Double floor) {
        this.floor = floor;
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
                ", ceil=" + ceil +
                ", floor=" + floor +
                ", useStatus='" + useStatus + '\'' +
                '}';
    }
}
