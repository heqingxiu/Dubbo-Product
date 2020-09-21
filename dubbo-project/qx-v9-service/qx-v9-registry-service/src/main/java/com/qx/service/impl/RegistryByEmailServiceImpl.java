package com.qx.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.qingxiu.constants.RedisConstants;
import com.qingxiu.utills.random.RandomUtil;
import com.qx.api.email.IEmailService;
import com.qx.api.registry.api.RegistryByEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Author: QX_He
 * DATA: 2020/8/10-16:30
 * Description:
 **/
@Component
@Service
public class RegistryByEmailServiceImpl implements RegistryByEmailService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Reference
    private IEmailService iEmailService;

    /**
     * Create a verification code by phone number
     *
     * @param phoneNumber
     * @return
     */
    @Override
    public String createVerificationCode(String phoneNumber) {
        // 1. Determine whether the phone number is valid
        if (phoneNumber.length() > 11) {
            return null;
        }
        // 2.Check whether the phone request again within  1 minute
        // Can use other redis-String to do that
        // 3. Create a verification code by phone number with random tools
        String verificationCode = RandomUtil.getRandomStringWithLength(4);
        // 4. Push the verification code to redis and send to user by note
        StringBuilder key = new StringBuilder(RedisConstants.REGISTRYCODE).append(phoneNumber);
        redisTemplate.opsForValue().set(key, verificationCode);

        /**
         *  We should use RabbitMQ to send an email to user ,otherwise will waste long time for get a response from email service，Because here is single thread
         *  Test: send verification code to user by email
         */
        iEmailService.sendSingleMail("497606056@qq.com", "VerificationCode", verificationCode);

        // 5. Set expire time ,about 1 minute
        redisTemplate.expire(key, 5, TimeUnit.MINUTES);
        // 6. return the  result
        return verificationCode;
    }

    /**
     * Check verifications code
     *
     * @param phoneNumber
     * @param code
     * @return
     */
    @Override
    public Boolean checkUserMessagesByCode(String phoneNumber, String code) {
        //1. Determine whether the phone number is valid
        if (phoneNumber.length() > 11) {
            return false;
        }
        //2. Get verification code from redis by phone
        StringBuilder key = new StringBuilder(RedisConstants.REGISTRYCODE).append(phoneNumber);
        String getCode = (String) redisTemplate.opsForValue().get(key);
        //3. Check the code between redis and user
        if (code.equals(getCode)) {
            //4. return the result
            return true;
        }
        return false;
    }
}
