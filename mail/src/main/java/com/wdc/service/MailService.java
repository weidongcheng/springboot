package com.wdc.service;

/**
 * Created by wdc on 2021/3/25 15:58
 *
 */
public interface MailService {
    void sendSimpleMail(String to,String subject,String content);

    void sendHtmlMail(String to,String subject,String content);

    void sendAttachmentsMail(String to,String subject,String content,String filePath);

    void sendInlineResourceMail(String to,String subject,String content,String rscPath,String rscId);
}
