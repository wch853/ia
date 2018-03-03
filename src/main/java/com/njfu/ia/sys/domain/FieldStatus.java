package com.njfu.ia.sys.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * 大棚状态
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FieldStatus {

    /**
     * 大棚编号
     */
    private String fieldId;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 温度
     */
    private Double temperature;

    /**
     * 湿度
     */
    private Double moisture;

    /**
     * 土壤温度
     */
    private Double soilTemperature;

    /**
     * 土壤湿度
     */
    private Double soilMoisture;

    /**
     * 光照度
     */
    private Double light;

    /**
     * co2浓度
     */
    private Double co2;

    /**
     * pH
     */
    private Double ph;

    /**
     * 氮含量
     */
    private Double n;

    /**
     * 磷含量
     */
    private Double p;

    /**
     * 钾含量
     */
    private Double k;

    /**
     * 汞含量
     */
    private Double hg;

    /**
     * 铅含量
     */
    private Double pb;

    public FieldStatus() {
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getMoisture() {
        return moisture;
    }

    public void setMoisture(Double moisture) {
        this.moisture = moisture;
    }

    public Double getSoilTemperature() {
        return soilTemperature;
    }

    public void setSoilTemperature(Double soilTemperature) {
        this.soilTemperature = soilTemperature;
    }

    public Double getSoilMoisture() {
        return soilMoisture;
    }

    public void setSoilMoisture(Double soilMoisture) {
        this.soilMoisture = soilMoisture;
    }

    public Double getLight() {
        return light;
    }

    public void setLight(Double light) {
        this.light = light;
    }

    public Double getCo2() {
        return co2;
    }

    public void setCo2(Double co2) {
        this.co2 = co2;
    }

    public Double getPh() {
        return ph;
    }

    public void setPh(Double ph) {
        this.ph = ph;
    }

    public Double getN() {
        return n;
    }

    public void setN(Double n) {
        this.n = n;
    }

    public Double getP() {
        return p;
    }

    public void setP(Double p) {
        this.p = p;
    }

    public Double getK() {
        return k;
    }

    public void setK(Double k) {
        this.k = k;
    }

    public Double getHg() {
        return hg;
    }

    public void setHg(Double hg) {
        this.hg = hg;
    }

    public Double getPb() {
        return pb;
    }

    public void setPb(Double pb) {
        this.pb = pb;
    }

    @Override
    public String toString() {
        return "FieldStatus{" +
                "fieldId='" + fieldId + '\'' +
                ", updateTime=" + updateTime +
                ", temperature=" + temperature +
                ", moisture=" + moisture +
                ", soilTemperature=" + soilTemperature +
                ", soilMoisture=" + soilMoisture +
                ", light=" + light +
                ", co2=" + co2 +
                ", ph=" + ph +
                ", n=" + n +
                ", p=" + p +
                ", k=" + k +
                ", hg=" + hg +
                ", pb=" + pb +
                '}';
    }
}
