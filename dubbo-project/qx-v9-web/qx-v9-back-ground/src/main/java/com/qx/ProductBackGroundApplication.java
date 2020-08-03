package com.qx;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Author: QX_He
 * DATA: 2020/7/30-0:41
 * Description:
 **/
@SpringBootApplication
@EnableDubbo
@Import(FdfsClientConfig.class)
public class ProductBackGroundApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductBackGroundApplication.class);
    }
}
