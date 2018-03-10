package com.njfu.ia.process.consumer;

import com.njfu.ia.process.property.StaticProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * 监听上行数据
 */
@Component
public class UploadDataMessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadDataMessageListener.class);

    @JmsListener(destination = StaticProperty.AMQ_UPLOAD_DATA, concurrency = StaticProperty.DEFAULT_CONCURRENCY_RANGE)
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            // TODO consume logic
            LOGGER.info("consume message: {}", textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
