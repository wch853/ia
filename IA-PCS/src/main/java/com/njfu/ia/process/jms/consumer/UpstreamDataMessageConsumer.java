package com.njfu.ia.process.jms.consumer;

import com.njfu.ia.process.service.UpstreamService;
import com.njfu.ia.process.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.annotation.JmsListeners;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * 上行数据消费者
 */
@Component
public class UpstreamDataMessageConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpstreamDataMessageConsumer.class);

    @Resource
    private UpstreamService upstreamService;

    @JmsListeners(
            value = {
                    @JmsListener(destination = Constants.QUEUE_UPSTREAM_HEART, concurrency = Constants.DEFAULT_CONCURRENCY_RANGE),
                    @JmsListener(destination = Constants.QUEUE_UPSTREAM_DATA, concurrency = Constants.DEFAULT_CONCURRENCY_RANGE),
            }
    )
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                String messageText = ((TextMessage) message).getText();
                // 处理上行数据
                upstreamService.consumeUpstreamMessage(messageText);
                LOGGER.info("UpstreamDataMessageConsumer consume message: {}", messageText);
            }
        } catch (JMSException e) {
            LOGGER.error("UpstreamDataMessageConsumer Exception", e);
        }
    }
}
