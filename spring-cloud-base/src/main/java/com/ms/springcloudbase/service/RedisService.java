package com.ms.springcloudbase.service;

import redis.clients.jedis.Jedis;

/**
 * @创建人 Oliver.Liu
 * @创建时间 4/10/2019
 * @描述
 */
public interface RedisService {
    Jedis getResource();

    void close(Jedis jedis);

    void set(String key, String value);

    String get(String key);

    long incr(String key);

    long decr(String key);
}
