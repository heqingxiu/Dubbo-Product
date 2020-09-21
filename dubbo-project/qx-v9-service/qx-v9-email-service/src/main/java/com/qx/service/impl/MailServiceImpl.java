package com.qx.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qx.api.email.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: QX_He
 * DATA: 2020/8/7-15:07
 * Description:
 **/
@Component
@Service
public class MailServiceImpl implements IEmailService {

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${mail.name}")
    private String from;


    /**
     * Single Mail
     * Only send content without attachments and templates
     *
     * @param to
     * @param subject
     * @param content
     * @return
     */
    @Override
    public String sendSingleMail(String to, String subject, String content) {

        try {
            Context context = new Context();
            context.setVariable("code", content);
            String contents = templateEngine.process("mail", context);
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(contents, true);
            FileSystemResource resource = new FileSystemResource("H:\\MI8里边的图片\\dog.jpeg");
            helper.addAttachment("dog.jpg", resource);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
            return "Email sent failed";
        }

        return "Email sent successful";
    }
}

