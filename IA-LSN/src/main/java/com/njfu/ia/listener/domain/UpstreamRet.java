package com.njfu.ia.listener.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 上行数据报文
 */
public class UpstreamRet {

    /**
     * 数据接收时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date receiveTime;

    /**
     * 数据内容
     */
    private String data;

    public UpstreamRet(Date receiveTime, String data) {
        this.receiveTime = receiveTime;
        this.data = data;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
