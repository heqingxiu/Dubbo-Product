package com.qx;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Author: QX_He
 * DATA: 2020/8/4-1:21
 * Description:
 **/
@SpringBootApplication
@EnableDubbo
@MapperScan("com.qx.v9.mapper")
public class SolrApplication {
    public static void main(String[] args) {
        SpringApplication.run(SolrApplication.class);

    }
}

