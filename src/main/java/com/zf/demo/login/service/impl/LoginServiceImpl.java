package com.zf.demo.login.service.impl;

import com.zf.demo.indexmanageofes.logic.IndexAL;
import com.zf.demo.login.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private IndexAL indexAL;
    @Autowired
    JedisCluster jedisCluster;

    @Override
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView index() {
        jedisCluster.set("aaas","五虎将之一");
        System.out.println(jedisCluster.get("张飞"));

        return new ModelAndView("login/loginIndex");
    }
    @Override
    @RequestMapping(value = "/registerIndex",method = RequestMethod.GET)
    public ModelAndView registerIndex() {

        return new ModelAndView("login/registerIndex");
    }
}
