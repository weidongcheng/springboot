package com.wdc.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * Created by wdc on 2021/3/25 16:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServcieTest {

    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void sendSimpleMail() throws Exception{
        mailService.sendSimpleMail("18621756389@163.com","test simple mail","hello this is simple mail");
    }

    @Test
    public void sendHtmlMail() throws Exception{
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail("18621756389@163.com","test simple mail",content);
    }

    @Test
    public void sendAttachmentsMail() throws Exception{
        String filePath="C:\\Users\\18621\\Pictures\\Camera Roll\\timg.jpg";
        mailService.sendAttachmentsMail("18621756389@163.com", "主题：带附件的邮件", "有附件，请查收！", filePath);
    }

    @Test
    public void sendInlineResouceMail() throws Exception{
        String rscId = "wdc666";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "C:\\Users\\18621\\Pictures\\Camera Roll\\timg.jpg";

        mailService.sendInlineResourceMail("18621756389@163.com", "主题：这是有图片的邮件", content, imgPath, rscId);
    }

    @Test
    public void sendTemplateMail() throws Exception{
        //创建邮件正文
        Context context = new Context();
        context.setVariable("id", "006");
        String emailContent = templateEngine.process("emailTemplate", context);

        mailService.sendHtmlMail("18621756389@163.com","主题：这是模板邮件",emailContent);
    }
}
