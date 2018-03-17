package com.njfu.ia.process.utils;

public interface Constants {

    /**
     * 数据上行队列
     */
    String AMQ_UPLOAD_DATA = "queue.ia.upload.data";

    /**
     * 数据下行主题
     */
    String AMQ_DOWNLOAD_DATA = "topic.ia.download.data";

    /**
     * 终端设备心跳通知
     */
    String AMQ_INFORM_HERAT = "queue.ia.inform.heart";

    /**
     * 默认消费者数量范围 TODO 使用JMX控制
     */
    String DEFAULT_CONCURRENCY_RANGE = "1-3";

    /**
     * redis操作成功返回值
     */
    String REDIS_SUCCESS_MSG = "OK";

    /**
     * 更新监控数据类型时间(millisecond)
     */
    int UPDATE_DATA_TYPE_TIME = 5 * 60 * 1000;

    /**
     * 数据分隔标志
     */
    String DATA_SEPERATOR = "|";

    /**
     * 数据类型与数据值分隔标志
     */
    String DATA_TYPE_VALUE_SEPERATOR = ":";

    /**
     * 数据头索引
     */
    int DATA_TYPE_IDX = 0;

    /**
     * 数据值索引
     */
    int DATA_VALUE_IDX = 1;

    /**
     * 消息类型关键字
     */
    String RET_TYPE = "tp";

    /**
     * 消息来源设备id
     */
    String RET_ID = "id";

    /**
     * 消息来源MAC地址
     */
    String RET_MAC = "mac";

    /**
     * 命令消息关键字
     */
    String RET_ORDER_ID = "cid";

    /**
     * 使用状态-未使用
     */
    int unuse = 0;

    /**
     * 使用中
     */
    int inuse = 1;

    int ERROR = 2;
}
