package com.zf.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Create by zengfei
 * Date 2020/11/4 15:56
 */
@Component
public class MailUtil {

    @Autowired
    JavaMailSenderImpl mailSender;

    private String emailServiceCode;

    public void send(String emailServiceCode,String title,String text,String toMail) throws Exception{
        SimpleMailMessage message = new SimpleMailMessage();
//        message.setSubject("注册验证码");
//        message.setText("注册验证码是：" + emailServiceCode);
//        message.setFrom("853238153@qq.com");
//        message.setTo("2530502870@qq.com");
        message.setSubject(title);
        message.setFrom("853238153@qq.com");
        message.setText(text+emailServiceCode);
        message.setTo(toMail);
        mailSender.send(message);
    }

}
