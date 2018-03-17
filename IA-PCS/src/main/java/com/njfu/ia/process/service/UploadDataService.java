package com.njfu.ia.process.service;

/**
 * 数据上行服务
 */
public interface UploadDataService {

    /**
     * 消费上行数据
     *
     * @param message messageText
     */
    void consumeUploadData(String message);
}
