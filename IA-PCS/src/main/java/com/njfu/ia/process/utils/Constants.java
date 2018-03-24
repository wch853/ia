package com.njfu.ia.process.utils;

public interface Constants {

    /**
     * 数据上行队列
     */
    String QUEUE_UPSTREAM_DATA = "queue.ia.upstream.data";

    /**
     * 命令反馈队列
     */
    String QUEUE_INFORM_COMMAND = "queue.ia.inform.command";

    /**
     * 上线通知/心跳反馈队列
     */
    String QUEUE_UPSTREAM_HEART = "queue.ia.upstream.heart";

    /**
     * 上线提示
     */
    String QUEUE_INFORM_HEART = "queue.ia.inform. heart";

    /**
     * 报警通知
     */
    String QUEUE_INFORM_ALARM = "queue.ia.inform.alarm";

    /**
     * 心跳下行主题
     */
    String TOPIC_DOWNSTREAM_HEART = "topic.ia.downstream.heart";

    /**
     * 命令下行主题
     */
    String TOPIC_DOWNSTREAM_COMMAND = "topic.ia.downstream.command";

    /**
     * 消息类型-上线
     */
    int MESSAGE_UPSTREAM_ON = 0;

    /**
     * 消息类型-分配设备编号
     */
    int MESSAGE_DOWNSTREAM_ON = 1;

    /**
     * 消息类型-上线提示
     */
    int MESSAGE_INFORM_ON = 2;

    /**
     * 消息类型-上行数据
     */
    int MESSAGE_UPSTREAM_DATA = 3;

    /**
     * 消息类型-报警通知
     */
    int MESSAGE_INFORM_ALARM = 4;

    /**
     * 消息类型-下达命令
     */
    int MESSAGE_DOWNSTREAM_COMMAND = 5;

    /**
     * 消息类型-反馈命令
     */
    int MESSAGE_INFORM_COMMAND = 6;

    /**
     * 消息类型-心跳指令
     */
    int MESSAGE_DOWNSTREAM_HEART = 7;

    /**
     * 消息类型-心跳反馈
     */
    int MESSAGE_UPSTREAM_HEART = 8;

    /**
     * 消息类型-终端失联通知
     */
    int MESSAGE_INFORM_HEART = 9;

    /**
     * 默认消费者数量范围 TODO 使用JMX控制
     */
    String DEFAULT_CONCURRENCY_RANGE = "1-3";

    /**
     * redis操作成功返回值
     */
    String REDIS_SUCCESS_MSG = "OK";

    /**
     * 更新监控数据类型间隔时间(millisecond)
     */
    int UPDATE_DATA_TYPE_TIME = 5 * 60 * 1000;

    /**
     * 心跳任务间隔时间(millisecond)
     */
    int HEART_TASK_TIME = 45 * 60 * 1000;

    /**
     * 心跳反馈限制时间(millisecond)
     */
    int HEART_BACK_LIMIT_TIME = 5 * 60 * 1000;

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
    String RET_COMMAN_ID = "cid";

    /**
     * 使用状态-未使用
     */
    int UNUSE = 0;

    /**
     * 使用中
     */
    int INUSE = 1;

    /**
     * 故障中
     */
    int ERROR = 2;
}
