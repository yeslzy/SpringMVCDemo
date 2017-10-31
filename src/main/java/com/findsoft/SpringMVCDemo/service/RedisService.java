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
     * ͨ��keyɾ��
     * 
     * @param key
     */
    public void del(String key) {
        this.getJedis().del(key);
    }

    /**
     * ���key value �������ô��ʱ��(byte)
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
     * ���key value �������ô��ʱ��
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
     * ���key value
     * 
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        this.getJedis().set(key, value);
    }

    /**
     * ���key value (�ֽ�)(���л�)
     * 
     * @param key
     * @param value
     */
    public void set(byte[] key, byte[] value) {
        this.getJedis().set(key, value);
    }

    /**
     * ��ȡredis value (String)
     * 
     * @param key
     * @return
     */
    public String get(String key) {
        String value = this.getJedis().get(key);
        return value;
    }

    /**
     * ��ȡredis value (byte [] )(�����л�)
     * 
     * @param key
     * @return
     */
    public byte[] get(byte[] key) {
        return this.getJedis().get(key);
    }

    /**
     * ͨ������ƥ��keys
     * 
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern) {
        return this.getJedis().keys(pattern);
    }

    /**
     * ���key�Ƿ��Ѿ�����
     * 
     * @param key
     * @return
     */
    public boolean exists(String key) {
        return this.getJedis().exists(key);
    }

    /**
     * ��ȡָ��ɢ�е�����keyֵ
     * 
     * @param name
     * @return
     */
    public Set<String> getAllKeys(String name) {
        return this.getJedis().hkeys(name);
    }

    /**
     * ��ȡָ��ɢ�е�����valueֵ
     * 
     * @param name
     * @return
     */
    public List<String> getAllValues(String name) {
        return this.getJedis().hvals(name);
    }

    /**
     * ����ɢ��key��ȡvalue
     * 
     * @param key
     * @param field
     * @return
     */
    public String getHashValuesByKey(String key, String field) {
        return this.getJedis().hget(key, field);
    }

    /**
     * ���redis ��������
     * 
     * @return
     */
    public String flushDB() {
        return this.getJedis().flushDB();
    }

    /**
     * �鿴redis���ж�������
     */
    public long dbSize() {
        return this.getJedis().dbSize();
    }

    /**
     * ����Ƿ����ӳɹ�
     * 
     * @return
     */
    public String ping() {
        return this.getJedis().ping();
    }
}
