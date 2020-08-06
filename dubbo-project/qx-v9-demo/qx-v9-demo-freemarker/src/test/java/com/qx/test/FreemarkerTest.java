package com.qx.test;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: QX_He
 * DATA: 2020/8/5-15:46
 * Description:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class FreemarkerTest {

    @Autowired
    private Configuration configuration;

    @Test
    public void createHtml() throws IOException, TemplateException {
        //Equation or formula: Data+Template = Output
        //1. Get the template object
        Template template = configuration.getTemplate("freemarker.ftl");
        //2. Create data by user or external
        Map<String, Object> data = new HashMap<>();
        data.put("name", "This is a freemarker test project");
        // Combination of both
        FileWriter writer = new FileWriter(
                "F:\\JAVA\\Dubbo\\FW\\Project\\GitbubVersion\\dubbo-project\\qx-v9-demo\\qx-v9-demo-freemarker\\src\\main\\resources\\templates\\freemarkerGenerated.html"
        );
        template.process(data, writer);
        System.out.println("Create static page successfully or Static page was generated successfully");
    }

}
