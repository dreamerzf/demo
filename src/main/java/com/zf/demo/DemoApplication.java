package com.zf.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableCaching
@EnableElasticsearchRepositories(basePackages = "com.zf.demo.usermangeofes.dao")
public class DemoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        // 设置环境变量，解决Es的netty与Netty服务本身不兼容问题
        System.setProperty("es.set.netty.runtime.available.processors","false");
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DemoApplication.class);
    }
}
