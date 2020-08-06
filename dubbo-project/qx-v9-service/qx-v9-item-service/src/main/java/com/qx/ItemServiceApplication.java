package com.qx;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Author: QX_He
 * DATA: 2020/8/5-19:04
 * Description:
 **/
@SpringBootApplication()
@EnableDubbo
@MapperScan("com.qx.v9.mapper")
public class ItemServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItemServiceApplication.class);
    }
}
