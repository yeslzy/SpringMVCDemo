package com.findsoft.SpringMVCDemo.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class RedisService {
    @Autowired
    private JedisPool jedisPool;

    private static final String REDIS_PWD = "lzyredis";
    private static Jedis jedis;

    private Jedis getJedis() {
        if (jedis == null) {
            jedis = jedisPool.getResource();
            jedis.auth(REDIS_PWD);
        }
        return jedis;
    }

    public void del(byte[] key) {
        this.getJedis().del(key);
    }

    /**
     * 通过key删除
     * 
     * @param key
     */
    public void del(String key) {
        this.getJedis().del(key);
    }

    /**
     * 添加key value 并且设置存活时间(byte)
     * 
     * @param key
     * @param value
     * @param liveTime
     */
    public void set(byte[] key, byte[] value, int liveTime) {
        this.set(key, value);
        this.getJedis().expire(key, liveTime);
    }

    /**
     * 添加key value 并且设置存活时间
     * 
     * @param key
     * @param value
     * @param liveTime
     */
    public void set(String key, String value, int liveTime) {
        this.set(key, value);
        this.getJedis().expire(key, liveTime);
    }

    /**
     * 添加key value
     * 
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        this.getJedis().set(key, value);
    }

    /**
     * 添加key value (字节)(序列化)
     * 
     * @param key
     * @param value
     */
    public void set(byte[] key, byte[] value) {
        this.getJedis().set(key, value);
    }

    /**
     * 获取redis value (String)
     * 
     * @param key
     * @return
     */
    public String get(String key) {
        String value = this.getJedis().get(key);
        return value;
    }

    /**
     * 获取redis value (byte [] )(反序列化)
     * 
     * @param key
     * @return
     */
    public byte[] get(byte[] key) {
        return this.getJedis().get(key);
    }

    /**
     * 通过正则匹配keys
     * 
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern) {
        return this.getJedis().keys(pattern);
    }

    /**
     * 检查key是否已经存在
     * 
     * @param key
     * @return
     */
    public boolean exists(String key) {
        return this.getJedis().exists(key);
    }

    /**
     * 获取指定散列的所有key值
     * 
     * @param name
     * @return
     */
    public Set<String> getAllKeys(String name) {
        return this.getJedis().hkeys(name);
    }

    /**
     * 获取指定散列的所有value值
     * 
     * @param name
     * @return
     */
    public List<String> getAllValues(String name) {
        return this.getJedis().hvals(name);
    }

    /**
     * 根据散列key获取value
     * 
     * @param key
     * @param field
     * @return
     */
    public String getHashValuesByKey(String key, String field) {
        return this.getJedis().hget(key, field);
    }

    /**
     * 清空redis 所有数据
     * 
     * @return
     */
    public String flushDB() {
        return this.getJedis().flushDB();
    }

    /**
     * 查看redis里有多少数据
     */
    public long dbSize() {
        return this.getJedis().dbSize();
    }

    /**
     * 检查是否连接成功
     * 
     * @return
     */
    public String ping() {
        return this.getJedis().ping();
    }
}
