package com.ms.springcloudbase.service.impl;

import com.ms.springcloudbase.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPool;

/**
 * @创建人 Oliver.Liu
 * @创建时间 4/10/2019
 * @描述
 */
@Service
public class RedisServiceImpl implements RedisService {
    private static Logger log = LoggerFactory.getLogger(RedisServiceImpl.class);
    @Autowired
    private JedisPool jedisPool;
    @Override
    public Jedis getResource() {
        return jedisPool.getResource();
    }
    @Override
    public void close(Jedis jedis) {
        if(jedis != null) {
            jedis.close();
        }
    }

    @Override
    public void set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.set(key, value);
            log.info("Redis set success - "+ key + ", value: " + value);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Redis set error: " + e.getMessage() + " - " + key + ", value: " + value);
        } finally {
            close(jedis);
        }
    }

    @Override
    public String get(String key) {
        String result = null;
        Jedis jedis=null;
        try{
            jedis = getResource();
            result = jedis.get(key);
            log.info("Redis get success - " + key + ", value:" + result);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Redis set error: "+ e.getMessage() +" - " + key + ", value:" + result);
        }finally{
            close(jedis);
        }
        return result;
    }

    @Override
    public long incr(String key) {
        return 0;
    }

    @Override
    public long decr(String key) {
        return 0;
    }
}
