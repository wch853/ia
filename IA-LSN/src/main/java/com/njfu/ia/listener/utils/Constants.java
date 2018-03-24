package com.njfu.ia.listener.utils;

import java.util.Arrays;
import java.util.List;

public interface Constants {

    /**
     * 监听端口
     */
    int LISTEN_PORT = 9040;

    /**
     * 协议消息开始标志（{）
     */
    byte START_FLAG = 0x7B;

    /**
     * 协议消息结束标志（}）
     */
    byte END_FLAG = 0x7D;

    /**
     * amq broker url
     */
    String BROKER_URL = "tcp://127.0.0.1:61616";

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
     * LSN生产者队列集合
     */
    List<String> LSN_PRODUCER_DESTINATIONS = Arrays.asList(QUEUE_UPSTREAM_DATA, QUEUE_INFORM_COMMAND, QUEUE_UPSTREAM_HEART);

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
     * 侦听模块上传消息类型
     */
    List<Integer> MESSAGE_TYPES = Arrays.asList(MESSAGE_UPSTREAM_ON, MESSAGE_UPSTREAM_DATA, MESSAGE_INFORM_COMMAND,
            MESSAGE_UPSTREAM_HEART);

    /**
     * socket字节流异常重读次数
     */
    int DEFAULT_TRY_READ_COUNT_LIMIT = 3;

    /**
     * 有效消息体消息类型索引
     */
    int MESSAGE_TYPE_IDX = 3;
}
