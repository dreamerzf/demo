package com.zf.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

/**
 * Create by zengfei
 * Date 2020/11/4 16:52
 */

@Configuration
@Component
public class MailConfig {
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.default-encoding}")
    private String encodeing;
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;


    @Bean
    public JavaMailSenderImpl JavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost("smtp.qq.com");
//        mailSender.setUsername("853238153@qq.com");
//        mailSender.setPassword("xcyqprzwrwxxbcif");
        System.out.println(host);
        System.out.println(host);
        System.out.println(host);
        System.out.println(host);
        System.out.println(host);
        mailSender.setHost(host);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
//        mailSender.setDefaultEncoding(encodeing);
        return mailSender;
    }
}