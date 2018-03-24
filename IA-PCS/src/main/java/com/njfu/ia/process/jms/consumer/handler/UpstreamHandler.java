package com.njfu.ia.process.jms.consumer.handler;

import java.util.Date;
import java.util.Map;

/**
 * 上行数据抽象处理器
 */
public abstract class UpstreamHandler {

    /**
     * 数据接收时间
     */
    private Date receiveTime;

    /**
     * 接收数据内容
     */
    private Map<String, Object> retMap;

    public UpstreamHandler(Date receiveTime, Map<String, Object> retMap) {
        this.receiveTime = receiveTime;
        this.retMap = retMap;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public Map<String, Object> getRetMap() {
        return retMap;
    }

    /**
     * 处理上行数据
     */
    public abstract void handleUpstream();
}
