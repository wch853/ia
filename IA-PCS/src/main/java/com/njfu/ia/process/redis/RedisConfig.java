package com.njfu.ia.process.redis;

import com.njfu.ia.process.utils.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Component
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.timeout}")
    private int timeout;

    private Jedis jedis;

    /**
     * 获取redis连接
     *
     * @return Jedis
     */
    private Jedis getRedisConnection() {
        if (null == jedis || !jedis.isConnected()) {
            jedis = new Jedis(host, port, timeout);
            jedis.auth(password);
        }
        return jedis;
    }

    /**
     * 写入redis
     *
     * @param key        key
     * @param value      value
     * @param expireTime expireTime seconds
     * @return is success
     */
    public boolean set(String key, String value, Integer expireTime) {
        String result;
        Jedis jedis = this.getRedisConnection();
        if (null == expireTime) {
            result = jedis.set(key, value);
        } else {
            result = jedis.setex(key, expireTime, value);
        }
        return Constants.REDIS_SUCCESS_MSG.equals(result);
    }

    /**
     * 写入redis
     *
     * @param key   key
     * @param value value
     * @return is success
     */
    public boolean set(String key, String value) {
        return this.set(key, value, null);
    }

    /**
     * 获取value
     *
     * @param key key
     * @return value
     */
    public String get(String key) {
        Jedis jedis = this.getRedisConnection();
        return jedis.get(key);
    }
}
