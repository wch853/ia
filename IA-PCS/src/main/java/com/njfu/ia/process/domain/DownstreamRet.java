package com.njfu.ia.process.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

/**
 * 下行报文
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DownstreamRet {

    /**
     * 消息类型
     */
    private Integer msgType;

    /**
     * 终端设备编号
     */
    private Integer deviceId;

    /**
     * 终端设备mac地址
     */
    private String mac;

    /**
     * 命令
     */
    private Map<String, Object> command;

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Map<String, Object> getCommand() {
        return command;
    }

    public void setCommand(Map<String, Object> command) {
        this.command = command;
    }
}
