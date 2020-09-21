package com.qx.config;

import com.qingxiu.constants.RabbitMQConstants;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author: QX_He
 * DATA: 2020/8/10-20:34
 * Description:
 **/
@Configuration
public class RabbitMQConfig {

    /**
     * Create an Email exchange
     *
     * @return
     */
    @Bean
    public TopicExchange createEmailExchange() {
        return new TopicExchange(RabbitMQConstants.TOPIC_REGISTRY_EXCHANGE);
    }
}
