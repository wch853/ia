package com.njfu.ia.process.jms;

import com.njfu.ia.process.utils.Constants;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Topic;

@Configuration
public class DestinationConfig {

    @Bean
    public Topic downloadTopic() {
        return new ActiveMQTopic(Constants.AMQ_DOWNLOAD_DATA);
    }
}
