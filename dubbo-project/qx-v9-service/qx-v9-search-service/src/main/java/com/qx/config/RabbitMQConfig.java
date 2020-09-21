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
 * DATA: 2020/8/6-21:26
 * Description:
 **/
@Configuration
public class RabbitMQConfig {

    /**
     * 1. Declare queue
     *
     * @return
     */
    @Bean
    public Queue searchQueue() {
        return new Queue(RabbitMQConstants.SEARCHQUEUENAME);
    }

    /**
     * 2.Declare Exchange
     *
     * @return
     */
    @Bean
    public TopicExchange backGroundExchange() {
        return new TopicExchange(RabbitMQConstants.TOPICEXCHANGENAME);
    }

    /**
     * queue and exchange were bound together
     *
     * @param searchQueue
     * @param backGroundExchange
     * @return
     */
    @Bean
    public Binding searchBinding(Queue searchQueue, TopicExchange backGroundExchange) {
        return BindingBuilder.bind(searchQueue).to(backGroundExchange).with("product.*");
    }
}
