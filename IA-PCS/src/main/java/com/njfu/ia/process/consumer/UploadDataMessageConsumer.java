package com.njfu.ia.process.consumer;

import com.njfu.ia.process.utils.Constants;
import com.njfu.ia.process.service.UploadDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * 上行数据消费者
 */
@Component
public class UploadDataMessageConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadDataMessageConsumer.class);

    @Resource
    private UploadDataService uploadDataService;

    @JmsListener(destination = Constants.AMQ_UPLOAD_DATA, concurrency = Constants.DEFAULT_CONCURRENCY_RANGE)
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                String messageText = ((TextMessage) message).getText();
                // 处理上行数据
                uploadDataService.consumeUploadData(messageText);
                LOGGER.info("UploadDataMessageConsumer consume message: {}", messageText);
            }
        } catch (JMSException e) {
            LOGGER.error("UploadDataMessageConsumer Exception", e);
        }
    }
}
