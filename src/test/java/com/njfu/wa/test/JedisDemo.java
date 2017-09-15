package com.njfu.wa.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisDemo {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 单实例
     */
    @Test
    public void demo1() {
        Jedis jedis = new Jedis("106.14.200.121", 6379, 1000);
        jedis.auth("admin");
        jedis.set("name", "wch");
        String name = jedis.get("name");
        log.info("name: {}", name);
        jedis.close();
    }

    /**
     * 连接池
     */
    @Test
    public void demo2() {
        // 连接池配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        // 最大连接数
        config.setMaxTotal(30);
        // 最大空闲连接数
        config.setMaxIdle(10);
        // 连接池对象
        JedisPool jedisPool = new JedisPool(config, "106.14.200.121", 6379);
        // 获得核心对象
        Jedis jedis = null;

        try {
            // 通过连接池获得连接
            jedis = jedisPool.getResource();
            jedis.auth("admin");
            // 设置数据
            jedis.set("name", "wch1");
            // 获取数据
            String name = jedis.get("name");
            log.info("name : {}", name);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            if (null != jedis) {
                jedis.close();
            }
            if (null != jedisPool) {
                jedisPool.close();
            }
        }
    }
}
