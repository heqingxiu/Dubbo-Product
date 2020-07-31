package com.qx;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Author: QX_He
 * DATA: 2020/7/31-22:52
 * Description:
 **/
@EnableDubbo
@SpringBootApplication
@Import(FdfsClientConfig.class)
public class FileApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileApplication.class);
    }
}
