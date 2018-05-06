package com.njfu.ia.sys.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 通知报文
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InformRet {

    /**
     * 通知消息类型
     */
    private int msgType;

    /**
     * 终端设备编号
     */
    private Integer deviceId;

    /**
     * 数据记录
     */
    private AlarmRecord record;

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public AlarmRecord getRecord() {
        return record;
    }

    public void setRecord(AlarmRecord record) {
        this.record = record;
    }
}
