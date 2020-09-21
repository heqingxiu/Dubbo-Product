package com.qx.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qingxiu.constants.RabbitMQConstants;
import com.qingxiu.utills.webresult.Result;
import com.qingxiu.utills.webresult.StatusCode;
import com.qx.api.registry.api.RegistryByEmailService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: QX_He
 * DATA: 2020/8/10-17:09
 * Description:
 **/
@Controller
@RequestMapping("/registry")
public class RegistryByEmailController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Reference
    private RegistryByEmailService RegistryByEmailService;

    @RequestMapping("/phone")
    @ResponseBody
    public Result<String> getVerificationCode(@RequestParam("ip") String phone) {
        try {
            rabbitTemplate.convertAndSend(RabbitMQConstants.TOPIC_REGISTRY_EXCHANGE, "registry.create.verificationCode", phone);
            return new Result<String>(true, StatusCode.OK, "Messages already send", "success");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<String>(false, StatusCode.ERROR, "Fail to send messages!", "Error");
        }
    }

    @RequestMapping("/check")
    @ResponseBody
    public Result<String> checkVerificationCode(@RequestParam("ip") String phone, @RequestParam("code") String code) {
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("code", code);
        try {
            rabbitTemplate.convertAndSend(RabbitMQConstants.TOPIC_REGISTRY_EXCHANGE, "registry.check.verificationCode", map);
            return new Result<String>(true, StatusCode.OK, "Messages already send", "success");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<String>(false, StatusCode.ERROR, "Fail to send messages!", "Error");
        }
    }

}
