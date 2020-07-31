package com.qx.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * Author: QX_He
 * DATA: 2020/7/30-17:03
 * Description:
 **/

@Configuration
public class PageHelperConfig {

    /**
     * Configure the PageHelper properties.
     * @return
     */
    @Bean
    public PageHelper getPageHelper() {
        PageHelper pageHelper = new PageHelper();
        //Set Attribute
        Properties properties = new Properties();
        properties.setProperty("dialect", "mysql");
        properties.setProperty("reasonable", "true");
        // Attribute End
        pageHelper.setProperties(properties);

        return pageHelper;
    }
}
