package com.njfu.ia.listener.utils;

import java.util.Collections;
import java.util.List;

public interface Constants {

    /**
     * 监听端口
     */
    int LISTEN_PORT = 9040;

    /**
     * 协议消息开始标志（S）
     */
    byte START_FLAG = 0x53;

    /**
     * 协议消息结束标志（T）
     */
    byte END_FLAG = 0x54;

    /**
     * amq broker url
     */
    String BROKER_URL = "tcp://127.0.0.1:61616";

    /**
     * 数据上行队列
     */
    String AMQ_UPLOAD_DATA = "queue.ia.upload.data";

    /**
     * 队列集合
     */
    List<String> MQ_LIST = Collections.singletonList(AMQ_UPLOAD_DATA);

    /**
     * 数据下行主题
     */
    String AMQ_DOWNLOAD_DATA = "topic.ia.download.command";

    /**
     * socket字节流异常重读次数
     */
    int DEFAULT_TRY_READ_COUNT_LIMIT = 3;
}
