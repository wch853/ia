package com.njfu.ia.sys.domain;

/**
 * 灌溉方案
 */
public class IrrigationPlan {

    /**
     * 方案编号
     */
    private Integer id;

    /**
     * 灌溉量
     */
    private Double planVolume;

    /**
     * 持续时长
     */
    private Integer duration;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPlanVolume() {
        return planVolume;
    }

    public void setPlanVolume(Double planVolume) {
        this.planVolume = planVolume;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
