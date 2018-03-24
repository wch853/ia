package com.njfu.ia.process.jms;

import com.njfu.ia.process.utils.Constants;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 * 消息发送工具
 */
@Service
public class JmsSender {

    /**
     * 上线通知/心跳反馈
     */
    @Resource
    private Queue upstreamHeart;

    /**
     * 数据上行
     */
    @Resource
    private Queue upstreamData;

    /**
     * 下行心跳反馈
     */
    @Resource
    private Topic downstreamHeart;

    /**
     * 心跳失联通知
     */
    @Resource
    private Queue informHeart;

    /**
     * 报警通知
     */
    @Resource
    private Queue informAlarm;

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    /**
     * 推送消息
     *
     * @param msgType msgType
     * @param message message
     */
    public void sendMessage(int msgType, String message) {
        Destination destination = null;
        switch (msgType) {
            case Constants.MESSAGE_DOWNSTREAM_HEART:
                destination = downstreamHeart;
                break;
            case Constants.MESSAGE_INFORM_ON:
            case Constants.MESSAGE_INFORM_HEART:
                destination = informHeart;
                break;
            case Constants.MESSAGE_INFORM_ALARM:
                destination = informAlarm;
                break;
            default:
                break;
        }
        if (null != destination) {
            jmsMessagingTemplate.convertAndSend(destination, message);
        }
    }
}
