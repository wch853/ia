package com.njfu.ia.listener.mq;

import com.njfu.ia.listener.common.StaticProperty;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import java.util.Map;

/**
 * 消息生产者
 */
public class AmqProducer {

    /**
     * 队列消息会话
     */
    private static Session session;

    /**
     * 队列生产者集合
     */
    private static Map<String, MessageProducer> amqMap;

    private static final Logger LOGGER = LoggerFactory.getLogger(AmqProducer.class);

    /**
     * 项目启动时即加载所有注册的MQ生产者
     */
    static {
        try {
            ConnectionFactory factory = new ActiveMQConnectionFactory(StaticProperty.BROKER_URL);
            Connection connection = factory.createConnection();
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

            for (String mqName : StaticProperty.MQ_LIST) {
                Queue destination = session.createQueue(mqName);
                MessageProducer producer = session.createProducer(destination);
                amqMap.put(mqName, producer);
            }
        } catch (JMSException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * 推送消息
     *
     * @param mqName  mqName
     * @param message message
     */
    public static void send(String mqName, String message) {
        try {
            amqMap.get(mqName).send(session.createMessage());
        } catch (JMSException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
