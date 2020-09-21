package com.qx.config;

import com.qingxiu.constants.RabbitMQConstants;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author: QX_He
 * DATA: 2020/8/6-20:05
 * Description:
 **/
@Configuration
public class RabbitConfig {

    @Bean
    public TopicExchange backGroundTopicExchange() {
        return new TopicExchange(RabbitMQConstants.TOPICEXCHANGENAME);
    }

}
