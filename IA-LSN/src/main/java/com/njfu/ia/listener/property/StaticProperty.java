package com.njfu.ia.listener.property;

import java.util.Collections;
import java.util.List;

public interface StaticProperty {

    /**
     * 监听端口
     */
    int LISTEN_PORT = 9040;

    /**
     * 协议消息开始标志
     */
    byte START_FLAG = 0x53;

    /**
     * 协议消息结束标志
     */
    byte END_FLAG = 0x45;

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
     * socket字节流异常重读次数
     */
    int DEFAULT_TRY_READ_COUNT_LIMIT = 3;
}
