package com.qx.api.email;

/**
 * Author: QX_He
 * DATA: 2020/8/7-13:32
 * Description:
 **/
public interface IEmailService {

    /**
     * Single Mail
     * Only send content without attachments and templates
     *
     * @param to
     * @param subject
     * @param contents
     * @return
     */
    String sendSingleMail(String to, String subject, String contents);





}
