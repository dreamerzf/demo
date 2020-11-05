package com.zf.demo.login.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zf.demo.indexmanageofes.logic.IndexAL;
import com.zf.demo.login.service.ILoginService;
import com.zf.demo.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.JedisCluster;

/**
 * Create by zengfei
 * Date 2020/4/28 14:22
 */
@RestController
@RequestMapping(value = "login")
public class LoginServiceImpl implements ILoginService {
//    @Autowired
//    private JavaMailSenderImpl mailSender;
    @Autowired
    MailUtil mailUtil;
    @Autowired
    private IndexAL indexAL;
//    @Autowired
//    JedisCluster jedisCluster;

    @Override
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView index() {
//        jedisCluster.set("aaas","五虎将之一");
//        System.out.println(jedisCluster.get("张飞"));

        return new ModelAndView("login/loginIndex");
    }
    @Override
    @RequestMapping(value = "/registerIndex",method = RequestMethod.GET)
    public ModelAndView registerIndex() {

        return new ModelAndView("login/registerIndex");
    }

    @Override
    @RequestMapping(value = "/testRest",method = RequestMethod.GET)
    public JSONObject testRest() {


        System.out.println("aaaaaa");
        try{
            mailUtil.send("3456","验证码","请不要扩散验证码，验证码是：","zengief@163.com");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("发送失败");
        }
//        String emailServiceCode = "1234";
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setSubject("注册验证码");
//        message.setText("注册验证码是：" + emailServiceCode);
//        message.setFrom("853238153@qq.com");
//        message.setTo("2530502870@qq.com");
//        mailSender.send(message);
        JSONObject jsonObject=JSONObject.parseObject("{\"myname\":\"zengfei\",\"myAge\":\"12\"}");
//        MailUtil mailUtil =new MailUtil();
//        mailUtil.test();
        return jsonObject;
    }
}
