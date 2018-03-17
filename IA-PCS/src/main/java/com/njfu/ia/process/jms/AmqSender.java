package com.njfu.ia.process.jms;

import javax.annotation.Resource;
import javax.jms.Topic;

/**
 * 消息发送工具
 */
public class AmqSender {

    @Resource
    private Topic downloadTopic;
}
