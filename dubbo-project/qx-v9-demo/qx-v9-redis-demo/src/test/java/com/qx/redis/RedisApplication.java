package com.qx.redis;

import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * Author: QX_He
 * DATA: 2020/8/9-14:58
 * Description:
 **/
public class RedisApplication {


    @Test
    public void connectTest() {
        Jedis jedis = new Jedis("192.168.140.151", 6379);
        jedis.auth("123456");
        jedis.set("target", "redis");
        System.out.println(jedis.get("target"));

    }
}
