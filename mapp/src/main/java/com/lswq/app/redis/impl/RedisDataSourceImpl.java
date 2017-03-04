package com.lswq.app.redis.impl;

import com.lswq.app.redis.RedisDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * Created by zhangsw on 2016/12/29.
 */
@Service("redisDataSource")
public class RedisDataSourceImpl implements RedisDataSource {
    
    private static final Logger log = LoggerFactory.getLogger(RedisDataSourceImpl.class);
    @Autowired
    private ShardedJedisPool shardedJedisPool;

    @Autowired
    private JedisPool jedisPool;

    @Override
    public ShardedJedis getRedisClient() {
        try {
            ShardedJedis shardJedis = shardedJedisPool.getResource();
            return shardJedis;
        } catch (Exception e) {
            log.error("getRedisClent error", e);
        }
        return null;
    }

    @Override
    public void returnResource(ShardedJedis shardedJedis) {
        shardedJedisPool.returnResource(shardedJedis);
    }

    @Override
    public void returnResource(ShardedJedis shardedJedis, boolean broken) {
        if (broken) {
            shardedJedisPool.returnBrokenResource(shardedJedis);
        } else {
            shardedJedisPool.returnResource(shardedJedis);
        }
    }

    @Override
    public Jedis getJedisClient() {
        try {
            return jedisPool.getResource();
        } catch (Exception e) {
            log.error("getJedisClent error", e);
        }
        return null;
    }

    @Override
    public void returnResource(Jedis jedis, boolean broken) {
        if (broken) {
            jedisPool.returnBrokenResource(jedis);
        } else {
            jedisPool.returnResource(jedis);
        }
    }

    @Override
    public void returnResource(Jedis jedis) {
        jedisPool.returnResource(jedis);
    }
}
