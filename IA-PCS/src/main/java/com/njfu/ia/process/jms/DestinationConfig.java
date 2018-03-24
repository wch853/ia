package com.njfu.ia.process.jms;

import com.njfu.ia.process.utils.Constants;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;
import javax.jms.Topic;

@Configuration
public class DestinationConfig {

    @Bean
    public Queue upstreamHeart() {
        return new ActiveMQQueue(Constants.QUEUE_UPSTREAM_HEART);
    }

    @Bean
    public Queue upstreamData() {
        return new ActiveMQQueue(Constants.QUEUE_UPSTREAM_DATA);
    }

    @Bean
    public Topic downstreamHeart() {
        return new ActiveMQTopic(Constants.TOPIC_DOWNSTREAM_HEART);
    }

    @Bean
    public Queue informHeart() {
        return new ActiveMQQueue(Constants.QUEUE_INFORM_HEART);
    }

    @Bean
    public Queue informAlarm() {
        return new ActiveMQQueue(Constants.QUEUE_INFORM_ALARM);
    }
}
