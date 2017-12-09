package com.njfu.wa.sys.domain;

/**
 * 传感器
 */
public class Sensor {

    private String sensorId;

    private String sensorFunc;

    private String sensorType;

    private Field field;

    private String terminalId;

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

    public String getSensorFunc() {
        return sensorFunc;
    }

    public void setSensorFunc(String sensorFunc) {
        this.sensorFunc = sensorFunc;
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

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
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
                ", sensorFunc='" + sensorFunc + '\'' +
                ", sensorType='" + sensorType + '\'' +
                ", field=" + field +
                ", terminalId='" + terminalId + '\'' +
                ", useStatus='" + useStatus + '\'' +
                ", sensorPs='" + sensorPs + '\'' +
                '}';
    }
}
