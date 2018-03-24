package com.njfu.ia.listener.jms;

import com.njfu.ia.listener.utils.Constants;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 消息生产者
 */
public class JmsHandler {

    /**
     * 队列消息会话
     */
    private static Session session;

    /**
     * 队列生产者集合
     */
    private static Map<String, MessageProducer> PRODUCERS;

    private static final Logger LOGGER = LoggerFactory.getLogger(JmsHandler.class);

    /*
      项目启动时即注册所有MQ生产者、启动消费者
     */
    static {
        try {
            // 创建ConnectionFactory
            ConnectionFactory factory = new ActiveMQConnectionFactory(Constants.BROKER_URL);
            // 获取连接
            Connection connection = factory.createConnection();
            // 启动连接
            connection.start();
            // 创建会话
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

            // 注册MQ生产者
            registerProducer(session);

            // 启动消费者
            establishConsumer(session);
        } catch (JMSException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * 注册MQ生产者
     *
     * @param session session
     * @throws JMSException JMSException
     */
    private static void registerProducer(Session session) throws JMSException {
        PRODUCERS = new HashMap<>(Constants.LSN_PRODUCER_DESTINATIONS.size());
        for (String mqName : Constants.LSN_PRODUCER_DESTINATIONS) {
            Queue destination = session.createQueue(mqName);
            MessageProducer producer = session.createProducer(destination);
            PRODUCERS.put(mqName, producer);
        }
    }

    /**
     * 启动下行命令主题/下行心跳主题消费者
     *
     * @param session session
     * @throws JMSException JMSException
     */
    private static void establishConsumer(Session session) throws JMSException {
        // 下行命令主题地址
        Topic downstreamCommandTopic = session.createTopic(Constants.TOPIC_DOWNSTREAM_COMMAND);
        // 下行心跳主题地址
        Topic downstreamHeartTopic = session.createTopic(Constants.TOPIC_DOWNSTREAM_HEART);

        // 下行命令主题消费者
        MessageConsumer downstreamCommandConsumer = session.createConsumer(downstreamCommandTopic);
        downstreamCommandConsumer.setMessageListener(new DownstreamMessageListener());

        // 下行心跳主题消费者
        MessageConsumer downstreamHeartConsumer = session.createConsumer(downstreamHeartTopic);
        downstreamHeartConsumer.setMessageListener(new DownstreamMessageListener());
    }

    /**
     * 推送消息
     *
     * @param msgType msgType
     * @param message     message
     */
    public static void send(int msgType, String message) {
        String queueName = null;
        switch (msgType) {
            case Constants.MESSAGE_UPSTREAM_DATA:
                queueName = Constants.QUEUE_UPSTREAM_DATA;
                break;
            case Constants.MESSAGE_INFORM_COMMAND:
                queueName = Constants.QUEUE_INFORM_COMMAND;
                break;
            case Constants.MESSAGE_UPSTREAM_ON:
            case Constants.MESSAGE_UPSTREAM_HEART:
                queueName = Constants.QUEUE_UPSTREAM_HEART;
                break;
            default:
                break;
        }
        try {
            PRODUCERS.get(queueName).send(session.createTextMessage(message));
        } catch (Exception e) {
            LOGGER.error("socket service upstream Exception", e);
        }
    }
}
