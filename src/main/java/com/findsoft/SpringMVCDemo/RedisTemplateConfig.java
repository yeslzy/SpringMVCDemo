package com.findsoft.SpringMVCDemo;

import java.nio.charset.Charset;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@ComponentScan
@EnableCaching
public class RedisTemplateConfig {
    private final String REDIS_HOST = "127.0.0.1";
    private final int REDIS_PORT = 6379;
    private final String REDIS_PASSWORD = "lzyredis";
    private final long DEFAULT_EXPIRE_TIME_SECOND= 1800;//默认key设置的缓存时间是30分钟
    
    @Bean
    public CacheManager getCacheManager(){
        RedisCacheManager cacheManager=new RedisCacheManager(getRedisTemplate());
        cacheManager.setDefaultExpiration(DEFAULT_EXPIRE_TIME_SECOND);
        return cacheManager;
    }
    @Bean
    public RedisConnectionFactory getRedisFactory() {
        JedisConnectionFactory cf = new JedisConnectionFactory();
        cf.setHostName(REDIS_HOST);
        cf.setPort(REDIS_PORT);
        cf.setPassword(REDIS_PASSWORD);
        return cf;
    }

    @Bean(name="redisTemplate")
    public RedisTemplate<String, Object> getRedisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(getRedisFactory());
        StringRedisSerializer stringSerializer=getStringRedisSerializer();
        template.setKeySerializer(stringSerializer);//key和hashkey均使用字符串序列化，不设置的话，使用默认的jdk序列化的方式
        template.setHashKeySerializer(stringSerializer);
        return template;
    }

    @Bean(name="stringRedisTemplate")
    public StringRedisTemplate getStringRedisTemplate() {
        return new StringRedisTemplate(getRedisFactory());
    }
    @Bean
    public StringRedisSerializer getStringRedisSerializer(){
        return new StringRedisSerializer(Charset.forName("UTF-8"));
    }
}
