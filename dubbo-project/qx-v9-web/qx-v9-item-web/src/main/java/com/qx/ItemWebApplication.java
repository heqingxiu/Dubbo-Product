package com.qx;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Author: QX_He
 * DATA: 2020/8/5-19:06
 * Description:
 **/
@SpringBootApplication
@EnableDubbo
public class ItemWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItemWebApplication.class);
    }
}
