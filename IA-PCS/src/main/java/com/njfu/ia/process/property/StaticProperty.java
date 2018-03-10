package com.njfu.ia.process.property;

public interface StaticProperty {

    /**
     * 数据上行队列
     */
    String AMQ_UPLOAD_DATA = "queue.ia.upload.data";

    /**
     * 默认消费者数量范围 TODO 使用JMX控制
     */
    String DEFAULT_CONCURRENCY_RANGE = "1-3";
}
