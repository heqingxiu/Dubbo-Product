package com.qx.listener;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qingxiu.constants.RabbitMQConstants;
import com.qx.api.registry.api.RegistryByEmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListeners;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Author: QX_He
 * DATA: 2020/8/10-21:00
 * Description:
 **/
@Component
public class RabbitMQListener {

    @Autowired
    private RegistryByEmailService registryByEmailService;

    /**
     * Non- specification (Don`t suggest do that)
     *
     * @param data
     */
    @RabbitListener(queues = RabbitMQConstants.TOPIC_EMAIL_QUEUE)
    public void getMessages(String data) {

        String code = registryByEmailService.createVerificationCode(data);
        System.out.println("Auth Code :" + code);
    }

    @RabbitListener(queues = RabbitMQConstants.TOPIC_EMAIL_QUEUE)
    public void getMessagesTwo(Map map) {
        System.out.println("The map contents is " + map);
        Boolean code = registryByEmailService.checkUserMessagesByCode((String) map.get("phone"), (String) map.get("code"));
        System.out.println("Auth Code :" + code);
    }
}
