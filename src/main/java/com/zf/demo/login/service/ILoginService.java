package com.zf.demo.login.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.servlet.ModelAndView;

/**
 * Create by zengfei
 * Date 2020/4/28 14:21
 */
public interface ILoginService {

    public ModelAndView index();
    public ModelAndView registerIndex();


    public JSONObject testRest();
}
