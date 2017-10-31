package com.findsoft.SpringMVCDemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@ComponentScan
public class RedisConfig {
    // redisœ‡πÿ≈‰÷√
    private final int MAX_IDLE = 300;
    private final int MAX_TOTAL = 100;
    private final int MAX_WAIT = 1000;

    private final String REDIS_HOST = "127.0.0.1";
    private final int REDIS_PORT = 6379;
    private final int REDIS_TIMEOUT = 2000;

    @Bean(name = "jedisPoolConfig")
    public JedisPoolConfig getJedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(MAX_IDLE);
        jedisPoolConfig.setMaxTotal(MAX_TOTAL);
        jedisPoolConfig.setMaxWaitMillis(MAX_WAIT);
        return jedisPoolConfig;
    }

    @Bean(name = "jedisPool")
    public JedisPool getJedisPool() {
        JedisPool pool = new JedisPool(getJedisPoolConfig(), REDIS_HOST, REDIS_PORT, REDIS_TIMEOUT);
        return pool;
    }
}
