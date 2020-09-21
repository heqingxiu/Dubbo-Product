package com.qx;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Author: QX_He
 * DATA: 2020/8/10-17:08
 * Description:
 **/

@EnableDubbo
@SpringBootApplication
public class RegistryWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegistryWebApplication.class);
    }

}
