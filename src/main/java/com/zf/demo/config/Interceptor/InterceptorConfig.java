package com.zf.demo.config.Interceptor;

import com.zf.demo.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Create by zengfei
 * Date 2020/6/4 11:39
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    MyInterceptor myInterceptor;

    private static String excludePath=CommonUtils.getPrepertyByName("urlconfig","exclude-path");
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> list=Arrays.asList(excludePath.split(","));
        registry.addInterceptor(myInterceptor).addPathPatterns("/**").excludePathPatterns(list);
    }
}
