package com.qx.service.text;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;


/**
 * Author: QX_He
 * DATA: 2020/8/7-14:15
 * Description:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailApplication {

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${mail.name}")
    private String from;

    @Test
    public void sendWithTemplate() throws MessagingException {
        Map<String, Object> map = new HashMap<>();
        map.put("username", "he qing xiu");
        map.put("url", "www.baidu.com");
        Context context = new Context();
        context.setVariable("user", map);
        String contents = templateEngine.process("mail", context);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(from);
        helper.setTo("497606056@qq.com");
        helper.setSubject("This is only a test email");
        helper.setText(contents, true);
        FileSystemResource resource = new FileSystemResource("H:\\MI8里边的图片\\dog.jpeg");
        helper.addAttachment("dog.jpg", resource);
        javaMailSender.send(mimeMessage);
    }
}
