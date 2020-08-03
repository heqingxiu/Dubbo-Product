package com.qx;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Author: QX_He
 * DATA: 2020/8/2-14:21
 * Description:
 **/
@SpringBootApplication
@EnableDubbo
public class IndexApplication {
    public static void main(String[] args) {
        SpringApplication.run(IndexApplication.class);
    }
}
