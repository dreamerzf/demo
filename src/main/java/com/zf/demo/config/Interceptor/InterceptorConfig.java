package com.zf.demo.config.Interceptor;

import com.zf.demo.utils.CommonUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
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

    /**
     * addMapping在拦截器之后执行，不能解决跨域问题
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("POST","GET","OPTIONS","DELETE")
                .maxAge(1800)
                .allowCredentials(true);
    }
    private CorsConfiguration getCorsConfig(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(1800L);
        return corsConfiguration;
    }

    /**
     * 使用过滤器解决跨域问题，在拦截器之前执行
     * @return
     */
    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",getCorsConfig());
        return new CorsFilter(source);
    }

}
