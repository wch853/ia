package com.njfu.ia.process.service;

/**
 * 数据上行服务
 */
public interface UpstreamService {

    /**
     * 消费上行消息
     *
     * @param message messageText
     */
    void consumeUpstreamMessage(String message);
}
