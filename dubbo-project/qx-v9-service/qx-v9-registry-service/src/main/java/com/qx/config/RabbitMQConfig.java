package com.qx.config;

import com.qingxiu.constants.RabbitMQConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author: QX_He
 * DATA: 2020/8/10-20:54
 * Description:
 **/
@Configuration
public class RabbitMQConfig {
    @Bean
    public TopicExchange createEmailExchange() {
        return new TopicExchange(RabbitMQConstants.TOPIC_REGISTRY_EXCHANGE);
    }

    @Bean
    public Queue createEmailQueue() {
        return new Queue(RabbitMQConstants.TOPIC_EMAIL_QUEUE);
    }


    @Bean
    public Binding bindingQueueAndExchange(Queue createEmailQueue, TopicExchange createEmailExchange) {
        return BindingBuilder.bind(createEmailQueue).to(createEmailExchange).with("registry.#");
    }

}



