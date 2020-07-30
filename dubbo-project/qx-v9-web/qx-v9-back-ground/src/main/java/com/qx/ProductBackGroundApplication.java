package com.qx;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Author: QX_He
 * DATA: 2020/7/30-0:41
 * Description:
 **/
@SpringBootApplication
@EnableDubbo

public class ProductBackGroundApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductBackGroundApplication.class);
    }
}
