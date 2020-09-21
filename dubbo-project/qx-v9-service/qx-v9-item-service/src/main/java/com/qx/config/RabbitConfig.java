package com.qx.config;

import com.qingxiu.constants.RabbitMQConstants;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Author: QX_He
 * DATA: 2020/8/6-20:24
 * Description:
 **/
@Configuration
public class RabbitConfig {

    /**
     * 1.Declare queue
     *
     * @return
     */
    @Bean
    public Queue itemQueue() {
        return new Queue(RabbitMQConstants.ITEMQUEUE);
    }

    /**
     * 2.Declare  exchange
     *
     * @return
     */
    @Bean
    public TopicExchange backGroundTopicExchange() {
        return new TopicExchange(RabbitMQConstants.TOPICEXCHANGENAME);
    }

    /**
     * 3. Queue and exchange were bound together.
     *
     * @param itemQueue
     * @param backGroundTopicExchange
     * @return
     */
    @Bean
    public Binding itemBinding(Queue itemQueue, TopicExchange backGroundTopicExchange) {
        return BindingBuilder.bind(itemQueue).to(backGroundTopicExchange).with("product.*");
    }

}
