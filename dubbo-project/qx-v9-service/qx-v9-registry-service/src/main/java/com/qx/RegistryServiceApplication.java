package com.qx;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Author: QX_He
 * DATA: 2020/8/10-16:24
 * Description:
 **/
@EnableDubbo
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class RegistryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegistryServiceApplication.class);
    }
}
