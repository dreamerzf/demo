package com.zf.demo.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

/**
 * Create by zengfei
 * Date 2019/12/9 13:54
 */
@Configuration
@MapperScan(basePackages = {"com.zf.demo.**.oradao"},sqlSessionFactoryRef = "sqlSessionFactory-ds2")
public class Ds2DataSourceConfig {

    @Profile("dev")
    @Bean({"ds2"})
    @ConfigurationProperties("spring.datasource.druid2")
    public DataSource dataSourceJdbc(){
        return DruidDataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name={"sqlSessionFactory-ds2"})
    public SqlSessionFactory sqlSessionFactory(@Qualifier("ds2") DataSource dataSource) throws Exception{

        GlobalConfig gcfg = new GlobalConfig();
        gcfg.setDbConfig(new GlobalConfig.DbConfig().setDbType(DbType.ORACLE));

        MybatisConfiguration mcfg=new MybatisConfiguration();
        mcfg.setJdbcTypeForNull(JdbcType.NULL);


        final MybatisSqlSessionFactoryBean ssfb=new MybatisSqlSessionFactoryBean();
        ssfb.setDataSource(dataSource);
        ssfb.setPlugins(new Interceptor[]{new PaginationInterceptor()});
        ssfb.setGlobalConfig(gcfg);
        ssfb.setConfiguration(mcfg);
        return ssfb.getObject();

    }

}
