package com.zf.demo.config;

import org.apache.catalina.Host;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * Create by zengfei
 * Date 2020/6/4 16:35
 */
@Configuration
@ConditionalOnClass({JedisCluster.class})
public class JedisConfig {

    @Value("${spring.redis.cluster.nodes}")
    private String clusterNodes;
    @Value("${spring.redis.jedis.pool.max-attempts}")
    private int maxAttempts;
    @Value("${spring.redis.database}")
    private int dataBase;
    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.jedis.pool.max-wait}")
    private int maxWaitMillis;
    @Value("${spring.redis.jedis.timeout}")
    private int timeout;
    @Value("${spring.redis.jedis.commandtimeout}")
    private int commandTimeout;

    @Bean
    public JedisPoolConfig getJedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        return jedisPoolConfig;
    }
    @Bean
    public JedisCluster getJedisCluster(){
        String[] nodesArr=clusterNodes.split(",");
        Set<HostAndPort> nodeSet=new HashSet<HostAndPort>();
        for (String node:nodesArr){
            String[] hp=node.split(":");
            nodeSet.add(new HostAndPort(hp[0],Integer.valueOf(hp[1])));
        }
        return new JedisCluster(nodeSet,timeout,maxAttempts,getJedisPoolConfig());
    }


}
