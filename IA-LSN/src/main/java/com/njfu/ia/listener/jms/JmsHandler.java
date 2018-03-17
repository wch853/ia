package com.njfu.ia.listener.jms;

import com.njfu.ia.listener.connection.Connections;
import com.njfu.ia.listener.utils.Constants;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
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
    private static Map<String, MessageProducer> AMQ_MAP;

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
        for (String mqName : Constants.MQ_LIST) {
            Queue destination = session.createQueue(mqName);
            MessageProducer producer = session.createProducer(destination);
            AMQ_MAP.put(mqName, producer);
        }
    }

    /**
     * 启动下行数据主题消费者
     *
     * @param session session
     * @throws JMSException JMSException
     */
    private static void establishConsumer(Session session) throws JMSException {
        Topic destination = session.createTopic(Constants.AMQ_DOWNLOAD_DATA);
        MessageConsumer consumer = session.createConsumer(destination);
        consumer.setMessageListener(new MessageListener() {

            @Override
            public void onMessage(Message message) {
                if (message instanceof TextMessage) {
                    try {
                        String order = ((TextMessage) message).getText();
                        LOGGER.info("socket service consumer message: {}", order);
                        Connections.broadcast(order.getBytes());
                    } catch (Exception e) {
                        LOGGER.error("socket service download Exception", e);
                    }
                }
            }
        });
    }

    /**
     * 推送消息
     *
     * @param mqName  mqName
     * @param message message
     */
    public static void send(String mqName, String message) {
        try {
            AMQ_MAP.get(mqName).send(session.createTextMessage(message));
        } catch (Exception e) {
            LOGGER.error("socket service upload Exception", e);
        }
    }
}
