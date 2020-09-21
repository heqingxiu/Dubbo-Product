package com.qx.redis.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Author: QX_He
 * DATA: 2020/8/10-15:38
 * Description:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedisTest {


    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void RedisTestOne() {

        redisTemplate.opsForValue().set("Student", "Bob");
        String student = (String) redisTemplate.opsForValue().get("Student");
        System.out.println("The value of student is :" + student);
    }

}
