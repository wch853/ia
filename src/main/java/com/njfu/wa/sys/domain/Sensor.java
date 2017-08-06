package com.njfu.wa.sys.domain;

/**
 * 传感器
 */
public class Sensor {

    private String sensorId;

    private String sensorFun;

    private String sensorType;

    private Field field;

    private String useStatus;

    private String sensorPs;

    public Sensor() {
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public String getSensorFun() {
        return sensorFun;
    }

    public void setSensorFun(String sensorFun) {
        this.sensorFun = sensorFun;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
    }

    public String getSensorPs() {
        return sensorPs;
    }

    public void setSensorPs(String sensorPs) {
        this.sensorPs = sensorPs;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "sensorId='" + sensorId + '\'' +
                ", sensorFun='" + sensorFun + '\'' +
                ", sensorType='" + sensorType + '\'' +
                ", field=" + field +
                ", useStatus='" + useStatus + '\'' +
                ", sensorPs='" + sensorPs + '\'' +
                '}';
    }
}
