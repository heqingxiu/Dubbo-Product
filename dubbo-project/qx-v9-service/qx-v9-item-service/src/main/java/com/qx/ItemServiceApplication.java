package com.qx;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Author: QX_He
 * DATA: 2020/8/5-19:04
 * Description:
 **/

/**
 * 因为spring主通过主类来扫描注解扫描的，所以如果想要被扫描到，简单的办法就是文件跟主类同级或者下级。
 * 或者可以通过spring factories 来指定路径
 * 或者主动添加一个import类做一个注解被扫描到。
 *
 *
 */

@SpringBootApplication()
@EnableDubbo
@MapperScan("com.qx.v9.mapper")
public class ItemServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItemServiceApplication.class);
    }
}

