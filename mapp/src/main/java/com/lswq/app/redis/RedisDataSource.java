package com.lswq.app.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;

/**
 * Created by zhangsw on 2016/12/29.
 */
public interface RedisDataSource {
    /**
     * 取得redis的客户端，可以执行命令了。
     *
     * @return
     */
    ShardedJedis getRedisClient();


    /**
     * 将资源返还给pool
     *
     * @param shardedJedis
     */
    void returnResource(ShardedJedis shardedJedis);

    /**
     * 出现异常后，将资源返还给pool （其实不需要第二个方法）
     *
     * @param shardedJedis
     * @param broken
     */
    void returnResource(ShardedJedis shardedJedis, boolean broken);

    /**
     * 取得redis的客户端，可以执行命令了,单机redis使用
     *
     * @return
     */
    Jedis getJedisClient();

    /**
     * 出现异常后，将资源返还给pool （其实不需要第二个方法）
     *
     * @param jedis
     * @param broken
     */
    void returnResource(Jedis jedis, boolean broken);

    /**
     * 将资源返还给pool
     *
     * @param jedis
     */
    void returnResource(Jedis jedis);
}
