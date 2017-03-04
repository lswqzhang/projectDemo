package com.lswq.app.redis.impl;

import com.lswq.app.redis.RedisDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhangsw on 2016/12/30.
 */
@Repository("redisClientTemplate")
public class RedisClientTemplate {
    private static final Logger log = LoggerFactory.getLogger(RedisClientTemplate.class);

    @Autowired
    private RedisDataSource redisDataSource;

    

    /**
     * 设置单个值
     *
     * @param key
     * @param value
     * @return
     */
    public String set(String key, String value) {
        String result = null;

        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.set(key, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 获取单个值
     *
     * @param key
     * @return
     */
    public String get(String key) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        boolean broken = false;
        try {
            result = shardedJedis.get(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 判断看KEY 是否存在
     * @param key
     * @return
     */
    public Boolean exists(String key) {
        Boolean result = false;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.exists(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 判断KEY 的类型
     * @param key
     * @return
     */
    public String type(String key) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.type(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 在某段时间后实现
     * <p>为给定 key 设置生存时间，当 key 过期时(生存时间为 0 )，它会被自动删除。
     * @param key
     * @param seconds
     * @return
     */
    public Long expire(String key, int seconds) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.expire(key, seconds);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 设置过期时间
     * @param key
     * @param unixTime 时间戳，为具体时间安
     * @return
     * 如果生存时间设置成功，返回 1 。当 key 不存在或没办法设置生存时间，返回 0 。
     */
    public Long expireAt(String key, long unixTime) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.expireAt(key, unixTime);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     *
     * Time To Live
     * 当前key的剩余存活时间
     * <p>当 key 不存在时，返回 -2 。
     * <p>当 key 存在但没有设置剩余生存时间时，返回 -1 。
     * <p>否则，以秒为单位，返回 key 的剩余生存时间。
     *
     * @param key
     * @return
     */
    public Long ttl(String key) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.ttl(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 设置字符串类型键指定位置的二进制值，返回该位置的旧值
     * @param key 键值
     * @param offset 指定位置
     * @param value 值
     * @return
     */
    public boolean setbit(String key, long offset, boolean value) {

        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        boolean result = false;
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.setbit(key, offset, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 获得一个字符串指定位置的二进制位的值(0或1)
     * @param key
     * @param offset
     * @return
     */
    public boolean getbit(String key, long offset) {
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        boolean result = false;
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;

        try {
            result = shardedJedis.getbit(key, offset);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * <p>从offset开始覆盖，替换的值为value
     * <p>用 value 参数覆写(overwrite)给定 key 所储存的字符串值，从偏移量 offset 开始。
     * @param key
     * @param offset
     * @param value
     * @return
     */
    public long setrange(String key, long offset, String value) {
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        long result = 0;
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.setrange(key, offset, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * <p>返回 key 中字符串值的子字符串，字符串的截取范围由 start 和 end 两个偏移量决定(包括 start 和 end 在内)。
     * <p>负数偏移量表示从字符串最后开始计数， -1 表示最后一个字符， -2 表示倒数第二个，以此类推。
     * <p>相当于：substr
     * @param key
     * @param startOffset
     * @param endOffset
     * @return  子字符串，并不更改原字符串
     */
    public String getrange(String key, long startOffset, long endOffset) {
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        String result = null;
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.getrange(key, startOffset, endOffset);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 将给定 key 的值设为 value ，并返回 key 的旧值(old value)。
     * 当 key 存在但不是字符串类型时，返回一个错误。
     * @param key
     * @param value
     * @return
     * <p>1、返回给定 key 的旧值。
     * <p>2、当 key 没有旧值时，也即是， key 不存在时，返回 nil 。
     */
    public String getSet(String key, String value) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.getSet(key, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * <p>若key 不存在则将key的值设置成value，返回：1，如果存在key则不错任何处理
     * <p>setnt = SET IF NOT EXISTS 
     * <p>原解： 
     * <p>将 key 的值设为 value ，当且仅当 key 不存在。
     * <p>若给定的 key 已经存在，则 SETNX 不做任何动作。
     * @param key
     * @param value
     * @return
     *<p>设置成功，返回 1 。
     *<p>设置失败，返回 0 。
     */
    public Long setnx(String key, String value) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.setnx(key, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * <p>摘要：
     * <p>1、key存在，则修改key的value为现再的value， 并且有效时间为：seconds
     * <p>2、key不存在，则添加key且值为value，并且有效时间为：seconds
     * @param key
     * @param seconds
     * @param value
     * @return
     * <p>设置成功时返回 OK 。
     * <p>当 seconds 参数不合法时，返回一个错误。
     */
    public String setex(String key, int seconds, String value) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.setex(key, seconds, value);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * <p>摘要：
     * <p>减法,当前key 的值减去integer 然后返回剩余值,返回值可以为复数
     * <p>如果,当前key 的值为字符串，则返回出现异常
     * <p>如果,当前key 不存在，则设置key 的值为0，然后再进行 减法
     * @param key
     * @param integer
     * @return 返回值为：减法的剩余值
     */
    public Long decrBy(String key, long integer) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.decrBy(key, integer);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * <p>摘要：
     * <p>减法,当前key 的值减去1 然后返回剩余值,返回值可以为复数
     * <p>如果,当前key 的值为字符串，则返回出现异常
     * <p>如果,当前key 不存在，则设置key 的值为0，然后再进行 减法
     * @param key
     * @return 返回值为：减法的剩余值
     */
    public Long decr(String key) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.decr(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * <p>摘要：
     * <p>加法,当前key 的值加上integer 然后返回剩余值
     * <p>如果,当前key 的值为字符串，则返回出现异常
     * <p>如果,当前key 不存在，则设置key 的值为0，然后再进行 加法
     * @param key
     * @param integer
     * @return 返回值为：加法的剩余值
     */
    public Long incrBy(String key, long integer) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.incrBy(key, integer);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * <p>摘要：
     * <p>加法,当前key 的值加上1 然后返回剩余值
     * <p>如果,当前key 的值为字符串，则返回出现异常
     * <p>如果,当前key 不存在，则设置key 的值为0，然后再进行 加法
     * @param key
     * @return 返回值为：加法的剩余值
     */
    public Long incr(String key) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.incr(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * <p>1、若key存在，则直接追加原来的字符串
     * <p>2、若key不存在，则直接定义key 的值为value
     * @param key
     * @param value
     * @return 追加之后的key 的value的字符串的长度
     */
    public Long append(String key, String value) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.append(key, value);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * <p>截取字符串，类似于getrange ,start end 都包含，切都是索引值
     * <p>-1表示最后一个值，0表示第一个
     * @param key
     * @param start
     * @param end
     * @return 返回截取的字符串 并不更改原来的字符串
     */
    public String substr(String key, int start, int end) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.substr(key, start, end);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 将哈希表 key 中的域 field 的值设为 value 。
     * <p>如果 key 不存在，一个新的哈希表被创建并进行 HSET 操作。
     * <p>如果域 field 已经存在于哈希表中，旧值将被覆盖。
     * @param key
     * @param field
     * @param value
     * @return
     * <p>如果 field 是哈希表中的一个新建域，并且值设置成功，返回 1 。
     * <p>如果哈希表中域 field 已经存在且旧值已被新值覆盖，返回 0 。
     */
    public Long hset(String key, String field, String value) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.hset(key, field, value);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 返回哈希表 key 中给定域 field 的值。
     * @param key
     * @param field
     * @return 给定域的值。当给定域不存在或是给定 key 不存在时，返回 nil 。
     */
    public String hget(String key, String field) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.hget(key, field);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * <p>将哈希表 key 中的域 field 的值设置为 value ，当且仅当域 field 不存在。
     * <p>若域 field 已经存在，该操作无效。
     * <p>如果 key 不存在，一个新哈希表被创建并执行 HSETNX 命令。
     * @param key
     * @param field
     * @param value
     * @return
     * <br>设置成功，返回 1 。
     * <br>如果给定域已经存在且没有操作被执行，返回 0 。
     */
    public Long hsetnx(String key, String field, String value) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.hsetnx(key, field, value);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * <br>同时将多个 field-value (域-值)对设置到哈希表 key 中。
     * <br>此命令会覆盖哈希表中已存在的域。
     * <br>如果 key 不存在，一个空哈希表被创建并执行 HMSET 操作。
     * @param key
     * @param hash
     * @return
     * <br>如果命令执行成功，返回 OK 。
     * <br>当 key 不是哈希表(hash)类型时，返回一个错误。
     */
    public String hmset(String key, Map<String, String> hash) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.hmset(key, hash);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * <br>返回哈希表 key 中，一个或多个给定域的值。
     * <br>如果给定的域不存在于哈希表，那么返回一个 nil 值。
     * <br>因为不存在的 key 被当作一个空哈希表来处理，所以对一个不存在的 key 进行 HMGET 操作将返回一个只带有 nil 值的表。
     * @param key
     * @param fields
     * @return 一个包含多个给定域的关联值的表，表值的排列顺序和给定域参数的请求顺序一样。
     */
    public List<String> hmget(String key, String... fields) {
        List<String> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.hmget(key, fields);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 为哈希表 key 中的域 field 的值加上增量 increment 。
     * 增量也可以为负数，相当于对给定域进行减法操作。
     * 如果 key 不存在，一个新的哈希表被创建并执行 HINCRBY 命令。
     * 如果域 field 不存在，那么在执行命令前，域的值被初始化为 0 。
     * 对一个储存字符串值的域 field 执行 HINCRBY 命令将造成一个错误。
     * 本操作的值被限制在 64 位(bit)有符号数字表示之内。
     * @param key
     * @param field
     * @param value
     * @return 执行 HINCRBY 命令之后，哈希表 key 中域 field 的值。
     */
    public Long hincrBy(String key, String field, long value) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.hincrBy(key, field, value);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 查看哈希表 key 中，给定域 field 是否存在。
     * @param key
     * @param field
     * @return
     * <br>如果哈希表含有给定域，返回 1 。
     * <br>如果哈希表不含有给定域，或 key 不存在，返回 0 。
     */
    public Boolean hexists(String key, String field) {
        Boolean result = false;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.hexists(key, field);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 删除给定的一个或多个 key 。
     * <br>不存在的 key 会被忽略。
     * @param key
     * @return 被删除的key 的数量
     */
    public Long del(String key) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.del(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
     * @param key
     * @param field
     * @return 被成功移除的域的数量，不包括被忽略的域。
     */
    public Long hdel(String key, String field) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.hdel(key, field);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 返回哈希表 key 中域的数量。
     * @param key
     * @return  哈希表中域的数量。 当 key 不存在时，返回 0 。
     */
    public Long hlen(String key) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.hlen(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 返回哈希表 key 中给定域 field 的值。
     * @param key
     * @return 返回以上，hset中的域的值
     */
    public Set<String> hkeys(String key) {
        Set<String> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.hkeys(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 根据Key的值，获取key 中所有域的值
     * @param key
     * @return 返回哈希表 key 中所有域的值。返回形式为LIST
     */
    public List<String> hvals(String key) {
        List<String> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.hvals(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 根据Key的值，获取key 中所有域的值
     * @param key
     * @return 返回哈希表 key 中所有域的值。返回形式为map
     */
    public Map<String, String> hgetAll(String key) {
        Map<String, String> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.hgetAll(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /********************************** 所有的LIST的的操作的******* r 表示右边， l 表示 左边**************************************************************/

    /**
     * <p>1、将一个或多个值 value 插入到列表 key 的表尾(最右边)。
     * <p>2、如过key 不存在，则，设置新的key，然后执行rpush
     * @param key
     * @param string
     * @return 返回LIST的长度
     */
    public Long rpush(String key, String string) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.rpush(key, string);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * <p>1、将一个或多个值 value 插入到列表 key 的表头，（最左边）
     * <p>2、如过key 不存在，则，设置新的key，然后执行rpush
     * @param key
     * @param string
     * @return 返回LIST的长度
     */
    public Long lpush(String key, String string) {
        Long result = null;
        Jedis jedis = redisDataSource.getJedisClient();
        if (jedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = jedis.lpush(key, string);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(jedis, broken);
        }
        return result;
    }

    /**
     * 1、根据key返回列表的长度
     * 2、如果key不存在则，返回一个空的列表
     * 3、如果key不是列表类型则，报错
     * @param key
     * @return
     */
    public Long llen(String key) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.llen(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * <p>获取；列表,根据索引，start end 都是索引值，0 表示第一个，-1 表示最后一个，用来获取所有的列表
     * <p>start end都包含
     * <p>超出索引的范围时，不会报错，会直接，替换为第一个值或者最后一个值,
     * <p>1、当start大于最大的下标还大的话直接返回一个空的列表
     * <p>2、当end比最大的坐标大时返回列表的最大值
     * <p>
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<String> lrange(String key, long start, long end) {
        List<String> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.lrange(key, start, end);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 剪切作用，再字符串为key的列表，除了指定范围之内（start，end 都包含）的，都删除，并保存
     * @param key
     * @param start
     * @param end
     * @return 成功返回 OK
     */
    public String ltrim(String key, long start, long end) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.ltrim(key, start, end);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 根据key获取下标（索引）为index的元素
     * @param key
     * @param index
     * @return 返回下标为index的元素，如果index 超出范围则，返回nil
     */
    public String lindex(String key, long index) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.lindex(key, index);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * <p>设置元素下标（索引）为index的值为value
     * <p>当 index 参数超出范围，或对一个空列表( key 不存在)进行 LSET 时，返回一个错误。
     * @param key
     * @param index
     * @param value
     * @return 成功则返回OK，否则返回错误信息
     */
    public String lset(String key, long index, String value) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.lset(key, index, value);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 摘要：根据 count来删除列表中元素等于value的元素
     * <p>1、count > 0 从表头开始删除 count 的绝对值哥元素等于value的元素
     * <p>2、count < 0 从表结尾开始删除 count 的绝对值哥元素等于value的元素 
     * <p>3、count = 删除所有的元素等于value的元素
     * @param key
     * @param count
     * @param value
     * @return 被移除元素的数量，如果KEY 不存在则返回0 
     */
    public Long lrem(String key, long count, String value) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.lrem(key, count, value);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 删除并返回列表元素的第一个（头元素）
     * @param key
     * @return 出栈，返回头元素，key不存在则返回nil
     */
    public String lpop(String key) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.lpop(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 删除并返回尾元素
     * @param key
     * @return 出栈，返回结尾元素，key 不存在则返回nil
     */
    public String rpop(String key) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.rpop(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    // return 1 add a not exist value ,
    // return 0 add a exist value
    /**
     * 1、将一个或者多个member元素加到集合key中，已经存在的集合的元素，将被忽略,如果存在则不存放。
     * <p>2、如果key不存在则创建一个只包含member元素作为成员的集合
     * @param key
     * @param member
     * @return key 不是集合类型时返回错误，正常返回则返回集合中新的元素的数量，且不包含被忽略的元素
     */
    public Long sadd(String key, String member) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.sadd(key, member);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 返回集合 key 中的所有成员。不存在的 key 被视为空集合。
     * @param key
     * @return 集合中所有的成员,如果key不是集合，则返回错误
     */
    public Set<String> smembers(String key) {
        Set<String> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.smembers(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 1、rem :为删除,s：集合;
     * <p>2、即：集合的删除。删除集合key中元素与member相等的元素。不存在的元素，则被忽略。
     * @param key
     * @param member
     * @return 成功删除元素的数量，不包含被忽略的元素
     */
    public Long srem(String key, String member) {
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();

        Long result = null;
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.srem(key, member);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 删除并返回集合key中的一个或者多个随机元素。
     * @param key
     * @return 被移除的元素，当key不存在或者key是空集合时，返回nil
     */
    public String spop(String key) {
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        String result = null;
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.spop(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 返回集合key中的基数（集合中元素的数量）
     * @param key
     * @return 集合的数量，集合不存在时则返回0，key不是集合则异常
     */
    public Long scard(String key) {
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        Long result = null;
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.scard(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 判断元素是存在集合key中
     * @param key
     * @param member
     * @return
     * 1、如果member存在集合key中，则返回 1 :存在
     * <p>2、如果member不在集合key中，或者key不存在的时候则返回 0 
     * <p>3、如果key不是集合元素则异常
     */
    public Boolean sismember(String key, String member) {
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        Boolean result = null;
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.sismember(key, member);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 1、返回集合中的一个随机元素
     * <p>2、REDIS > 2.6 可输入一个count参数，
     * <p>2.1如果count> 0   则返回count个随机元素，每个元素各不相同 
     * <p>2.1如果count< 0   则返回count个随机元素，每个元素有可能重复出现 
     * @param key
     * @return
     * 1、提供key返回一个元素，如果元素为空，则返回nil
     * <p>2、如果key不是集合，则出现异常
     */
    public String srandmember(String key) {
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        String result = null;
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.srandmember(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 1、将一个或者多个元素以及score的值加入到有序集合key中
     * <p>2、 如果集合中存在的某个元素跟member相等，则只更新score的值，并通过重新插入的这个memeber更新元素，保证member再正确的位置上。
     * <p>3、score的值可以是整数值或者双精度的浮点数。
     * <p>4、如果key不存在，则创建一个空的有序集合并执行zadd的操作
     * <p>5、如果key存在但是不是有序集合，则返回错误。
     * @param key
     * @param score
     * @param member
     * @return 被添加成功的新的元素的数量，不包含被更新的、已经存在的成员。
     */
    public Long zadd(String key, double score, String member) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.zadd(key, score, member);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 1、将一个或者多个元素以及score的值加入到有序集合key中
     * <p>2、 如果集合中存在的某个元素跟member相等，则只更新score的值，并通过重新插入的这个memeber更新元素，保证member再正确的位置上。
     * <p>3、scoreMembers的key 是值,value 是 score。
     * <p>4、如果key不存在，则创建一个空的有序集合并执行zadd的操作
     * <p>5、如果key存在但是不是有序集合，则返回错误。
     * @param key
     * @param scoreMembers
     * @return 被添加成功的新的元素的数量，不包含被更新的、已经存在的成员。
     */
    public Long zadd(String key, Map<String, Double> scoreMembers) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.zadd(key, scoreMembers);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 1、按照默认的score从小到大的顺序来排序，如果score的值相等，则按照score的值的成员按照字典排序
     * <p>2、不会出现下标越界的情况，0：表示第一个，-1：表示最后一个。
     * <p>3、如果使用withscores选项，则让成员跟score值一起返回，返回的列表形式以，value1，score1，value2，score2来展示
     * @param key
     * @param start
     * @param end
     * @return
     * 1、返回指定区间的有序集合的成员列表
     * <p>2、如果key不是有序集合则直接报出异常
     */
    public Set<String> zrange(String key, int start, int end) {
        Set<String> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.zrange(key, start, end);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 1、删除有序集合中的一个或者多个成员等于member的成员，不存在的成员则呗忽略，
     * <p>2、当key不存在或者不是有序集合的时候返回一个错误
     * @param key
     * @param member
     * @return 返回删除元素的数量，不包含呗忽略的元素
     */
    public Long zrem(String key, String member) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.zrem(key, member);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 有序集合的加法
     * <p>1、为有序集合的元素为member的score的值增加一个增量score
     * <p>2、传递进来的score的值可以是复数的整型数据，或者浮点型数据
     * <p>3、如果key不存在则直接相当于直接了zadd（key，score，member）
     * @param key
     * @param score
     * @param member
     * @return 元素memeber成员的新的score的值，如果key不是有序集合则直接返回一个异常
     */
    public Double zincrby(String key, double score, String member) {
        Double result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.zincrby(key, score, member);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * <p>1、有序集合中的member的排名并不是，score，有序集合中的排序是按照score从小到达排序的。
     * <p>2、排名以0为低，所以有序集合的默认第一个排名就是0（默认排序，否，则反之）
     * @param key
     * @param member
     * @return
     * 1、如果member是key的成员，返回有序集合key中的为member的排名
     * <p>2、如果member不是可以的成员的，则返回nil
     * <p>3、如果key不是有序集合，则返回一个异常
     */
    public Long zrank(String key, String member) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.zrank(key, member);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * <p>1、有序集合从大到小排序中的member的排名。
     * <p>2、排名以0为低
     * @param key
     * @param member
     * @return
     * 1、如果member是key的成员，返回有序集合key中的为member的排名
     * <p>2、如果member不是可以的成员的，则返回nil
     * <p>3、如果key不是有序集合，则返回一个异常
     */
    public Long zrevrank(String key, String member) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.zrevrank(key, member);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 按照从大到小的顺序或者指定区间的成员
     * @param key
     * @param start
     * @param end
     * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
     */
    public Set<String> zrevrange(String key, int start, int end) {
        Set<String> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.zrevrange(key, start, end);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 1、按照从小到大的顺序或者指定区间的成员
     * <p>2、withscores选项，则让成员跟score值一起返回，返回的列表形式以，value1，score1，value2，score2来展示
     * @param key
     * @param start
     * @param end
     * @return
     * 指定区间内，带有 score 值的有序集成员的列表。
     */
    public Set<Tuple> zrangeWithScores(String key, int start, int end) {
        Set<Tuple> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.zrangeWithScores(key, start, end);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 1、按照从大到小的顺序或者指定区间的成员
     * <p>2、withscores选项，则让成员跟score值一起返回，返回的列表形式以，value1，score1，value2，score2来展示
     * @param key
     * @param start
     * @param end
     * @return
     * 指定区间内，带有 score 值的有序集成员的列表。
     */
    public Set<Tuple> zrevrangeWithScores(String key, int start, int end) {
        Set<Tuple> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.zrevrangeWithScores(key, start, end);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 返回有序集合的数量，即：有序集合的基数
     * @param key
     * @return
     * 1、当 key 存在且是有序集类型时，返回有序集的基数。
     * <p>2、当 key 不存在时，返回 0 。
     */
    public Long zcard(String key) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.zcard(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 得到元素为member的score的值
     * @param key
     * @param member
     * @return
     * <p>1、member 成员的 score 值，以字符串形式表示。
     * <p>2、若key不是有序结合则返回一个异常
     */
    public Double zscore(String key, String member) {
        Double result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.zscore(key, member);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 列表从小到大排序，字典排序。(默认排序)
     * @param key
     * @return 排序完成后的列表
     */
    public List<String> sort(String key) {
        List<String> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.sort(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 自定义排序,可以根据sortingParameters.desc();倒序，sortingParameters.asc();正序
     * @param key
     * @param sortingParameters
     * @return
     */
    public List<String> sort(String key, SortingParams sortingParameters) {
        List<String> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.sort(key, sortingParameters);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 返回指定score范围之内的成员数量
     * @param key
     * @param min (包含)
     * @param max (包含)
     * @return 指定score范围内的元素数量
     */
    public Long zcount(String key, double min, double max) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.zcount(key, min, max);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 按照默认排序（从小到大） score 在 min跟Max之间的有序集合
     * @param key
     * @param min
     * @param max
     * @return score 在min跟max之间的闭区间的所有的有序集合
     */
    public Set<String> zrangeByScore(String key, double min, double max) {
        Set<String> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.zrangeByScore(key, min, max);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 按照倒序（从大到小） score 在 min跟Max之间的有序集合
     * @param key
     * @param min
     * @param max
     * @return score 在min跟max之间的闭区间的所有的有序集合
     */
    public Set<String> zrevrangeByScore(String key, double max, double min) {
        Set<String> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.zrevrangeByScore(key, max, min);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 1、按照默认排序（从小到大） score 在 min跟Max之间的有序集合
     * <p>2、分页数据，int offset 第几页，, int count 每页数量
     * @param key
     * @param min
     * @param max
     * @return score 在min跟max之间的闭区间的所有的有序集合
     */
    public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
        Set<String> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.zrangeByScore(key, min, max, offset, count);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 1、按照默认排序（从大到小） score 在 min跟Max之间的有序集合
     * <p>2、分页数据，int offset 第几页，, int count 每页数量
     * @param key
     * @param min
     * @param max
     * @return score 在min跟max之间的闭区间的所有的有序集合
     */
    public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
        Set<String> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.zrevrangeByScore(key, max, min, offset, count);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 按照默认排序（从小到大） score 在 min跟Max之间的有序集合
     * @param key
     * @param min
     * @param max
     * @return score 在min跟max之间的闭区间的所有的有序集合,并且返回score
     * for (Tuple tuple : result) {
    String element = tuple.getElement();
    double score = tuple.getScore();
    }
     */
    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
        Set<Tuple> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.zrangeByScoreWithScores(key, min, max);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 按照默认排序（从大到小） score 在 min跟Max之间的有序集合
     * @param key
     * @param min
     * @param max
     * @return score 在min跟max之间的闭区间的所有的有序集合,并且返回score
     * for (Tuple tuple : result) {
    String element = tuple.getElement();
    double score = tuple.getScore();
    }
     */
    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {
        Set<Tuple> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.zrevrangeByScoreWithScores(key, max, min);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 按照默认排序（从小到大） score 在 min跟Max之间的有序集合
     * 分页数据 int offset, int count ，offset：索引，count：数量
     * @param key
     * @param min
     * @param max
     * @return score 在min跟max之间的闭区间的所有的有序集合,并且返回score
     * for (Tuple tuple : result) {
    String element = tuple.getElement();
    double score = tuple.getScore();
    }
     */
    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
        Set<Tuple> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.zrangeByScoreWithScores(key, min, max, offset, count);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 按照默认排序（从大到小） score 在 min跟Max之间的有序集合
     * 分页数据 int offset, int count ，offset：索引，count：数量
     * @param key
     * @param min
     * @param max
     * @return score 在min跟max之间的闭区间的所有的有序集合,并且返回score
     * for (Tuple tuple : result) {
    String element = tuple.getElement();
    double score = tuple.getScore();
    }
     */
    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {
        Set<Tuple> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.zrevrangeByScoreWithScores(key, max, min, offset, count);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 1、移除有序集 key 中，指定排名(rank)区间内的所有成员。
     * <p>2、区间分别以下标参数 start 和 stop 指出，包含 start 和 stop 在内。
     * <p>3、下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。
     * <p>4、你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。
     * @param key
     * @param start
     * @param end
     * @return 被移除的元素的数量
     */
    public Long zremrangeByRank(String key, int start, int end) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.zremrangeByRank(key, start, end);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 移除有序集 key 中，所有 score 值介于 start 和 end 之间(包括等于 start 或 end )的成员。
     * <p>2、自版本2.1.6开始， score 值等于 start 或 end 的成员也可以不包括在内，
     * <p>3、下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。
     * <p>4、你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Long zremrangeByScore(String key, double start, double end) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.zremrangeByScore(key, start, end);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * <p>1、将value插入到key列表中，在pivot之前或者之后
     * <p>2、当 pivot 不存在于列表 key 时，不执行任何操作。
     * <p>3、当 key 不存在时， key 被视为空列表，不执行任何操作。
     * @param key
     * @param where LIST_POSITION.BEFORE :之前，  LIST_POSITION.AFTER：之后
     * @param pivot
     * @param value
     * @return
     * <p>如果命令执行成功，返回插入操作完成之后，列表的长度。
     * <p>如果没有找到 pivot ，返回 -1 。
     * <p>如果 key 不存在或为空列表，返回 0 。
     */
    public Long linsert(String key, BinaryClient.LIST_POSITION where, String pivot, String value) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.linsert(key, where, pivot, value);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 1、在redis执行序列话对象，然后set,可以储存Java对象,只需要将JAVA对象序列化可行
     * <p>2、redisClient.set(key.getBytes(), SerializeUtil.serialize(object));
     * @param key
     * @param value
     * @return 成功返回OK
     */
    public String set(byte[] key, byte[] value) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.set(key, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 针对set(byte[] key, byte[] value) 方法的获取方案
     * <p>2、bs=redisClient.get(key.getBytes());
     * <p>3、User unserialize = (User) SerializeUtil.unserialize(bs);
     * @param key
     * @return 序列化的对象
     */
    public byte[] get(byte[] key) {
        byte[] result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.get(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 针对set(byte[] key, byte[] value) 方法的判断是否存在的方案
     *
     * @param key
     * @return 成功为true 失败为false
     */
    public Boolean exists(byte[] key) {
        Boolean result = false;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.exists(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 判断byte[] key的类型
     * @param key
     * @return string list ...
     */
    public String type(byte[] key) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.type(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 设置存活时间
     * <p>为给定 key 设置生存时间，当 key 过期时(生存时间为 0 )，它会被自动删除。
     * @param key
     * @param seconds
     * @return
     */
    public Long expire(byte[] key, int seconds) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.expire(key, seconds);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 设置过期时间
     * @param key
     * @param unixTime 时间戳，为具体时间安
     * @return
     * 如果生存时间设置成功，返回 1 。当 key 不存在或没办法设置生存时间，返回 0 。
     */
    public Long expireAt(byte[] key, long unixTime) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.expireAt(key, unixTime);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 以秒为单位，计算剩余时间
     * @param key
     * @return
     * <br>当 key 不存在时，返回 -2 。
     * <br>当 key 存在但没有设置剩余生存时间时，返回 -1 。
     * <br>否则，以秒为单位，返回 key 的剩余生存时间。
     */
    public Long ttl(byte[] key) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.ttl(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 将给定 key 的值设为 value ，并返回 key 的旧值(old value)。当 key 存在但不是字符串类型时，返回一个错误。
     * @param key
     * @param value
     * @return 返回 key 的旧值(old value)。当 key 存在但不是字符串类型时，返回一个错误。
     */
    public byte[] getSet(byte[] key, byte[] value) {
        byte[] result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.getSet(key, value);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 1、将 key 的值设为 value ，当且仅当 key 不存在。
     * <br>2、若给定的 key 已经存在，则 SETNX 不做任何动作。
     * <br>3、SETNX 是『SET if Not eXists』(如果不存在，则 SET)的简写。
     * @param key
     * @param value
     * @return
     * <br>设置成功，返回 1 。
     * <br>设置失败，返回 0 。
     */
    public Long setnx(byte[] key, byte[] value) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.setnx(key, value);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 将值 value 关联到 key ，并将 key 的生存时间设为 seconds (以秒为单位)。
     * <br>如果 key 已经存在， SETEX 命令将覆写旧值。
     * @param key
     * @param seconds
     * @param value
     * @return 设置成功时返回 OK 。当 seconds 参数不合法时，返回一个错误。
     */
    public String setex(byte[] key, int seconds, byte[] value) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.setex(key, seconds, value);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 将 key 所储存的值减去减量 decrement 。
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 DECRBY 操作。
     * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。
     * 本操作的值限制在 64 位(bit)有符号数字表示之内。
     * @param key
     * @param integer
     * @return 减法，将byte类型的key的值减去之后的值
     */
    public Long decrBy(byte[] key, long integer) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.decrBy(key, integer);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 将 key 所储存的值减去减量 1
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 DECRBY 操作。
     * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。
     * 本操作的值限制在 64 位(bit)有符号数字表示之内。
     * @param key
     * @return 减法，将byte类型的key的值减去1 后的值
     */
    public Long decr(byte[] key) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.decr(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 将 key 中储存的数字值增 integer。
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 操作。
     * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。
     * 本操作的值限制在 64 位(bit)有符号数字表示之内。
     * @param key
     * @param integer
     * @return  将byte类型的key的值增 integer 后的值
     */
    public Long incrBy(byte[] key, long integer) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.incrBy(key, integer);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 将 key 中储存的数字值增一。
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 操作。
     * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。
     * 本操作的值限制在 64 位(bit)有符号数字表示之内。
     * @param key
     * @return  将byte类型的key的值增 1 后的值
     */
    public Long incr(byte[] key) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.incr(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * <br>如果 key 已经存在并且是一个字符串， APPEND 命令将 value 追加到 key 原来的值的末尾。
     * <br>如果 key 不存在， APPEND 就简单地将给定 key 设为 value ，就像执行 SET key value 一样。
     * @param key
     * @param value
     * @return 追加 value 之后， key 中字符串的长度。
     */
    public Long append(byte[] key, byte[] value) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.append(key, value);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * <p>返回 key 中字符串值的子字符串，字符串的截取范围由 start 和 end 两个偏移量决定(包括 start 和 end 在内)。
     * <p>负数偏移量表示从字符串最后开始计数， -1 表示最后一个字符， -2 表示倒数第二个，以此类推。
     * @param key
     * @param start
     * @param end
     * @return  子字符串，并不更改原字符串
     */
    public byte[] substr(byte[] key, int start, int end) {
        byte[] result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.substr(key, start, end);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 将哈希表 key 中的域 field 的值设为 value 。
     * <p>如果 key 不存在，一个新的哈希表被创建并进行 HSET 操作。
     * <p>如果域 field 已经存在于哈希表中，旧值将被覆盖。
     * @param key
     * @param field
     * @param value
     * @return
     * <p>如果 field 是哈希表中的一个新建域，并且值设置成功，返回 1 。
     * <p>如果哈希表中域 field 已经存在且旧值已被新值覆盖，返回 0 。
     */
    public Long hset(byte[] key, byte[] field, byte[] value) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.hset(key, field, value);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 返回哈希表 key 中给定域 field 的值。
     * @param key
     * @param field
     * @return 给定域的值。当给定域不存在或是给定 key 不存在时，返回 nil 。
     */
    public byte[] hget(byte[] key, byte[] field) {
        byte[] result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.hget(key, field);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * <p>将哈希表 key 中的域 field 的值设置为 value ，当且仅当域 field 不存在。
     * <p>若域 field 已经存在，该操作无效。
     * <p>如果 key 不存在，一个新哈希表被创建并执行 HSETNX 命令。
     * @param key
     * @param field
     * @param value
     * @return
     * <br>设置成功，返回 1 。
     * <br>如果给定域已经存在且没有操作被执行，返回 0 。
     */
    public Long hsetnx(byte[] key, byte[] field, byte[] value) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.hsetnx(key, field, value);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * <br>同时将多个 field-value (域-值)对设置到哈希表 key 中。
     * <br>此命令会覆盖哈希表中已存在的域。
     * <br>如果 key 不存在，一个空哈希表被创建并执行 HMSET 操作。
     * @param key
     * @param hash
     * @return
     * <br>如果命令执行成功，返回 OK 。
     * <br>当 key 不是哈希表(hash)类型时，返回一个错误。
     */
    public String hmset(byte[] key, Map<byte[], byte[]> hash) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.hmset(key, hash);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * <br>返回哈希表 key 中，一个或多个给定域的值。
     * <br>如果给定的域不存在于哈希表，那么返回一个 nil 值。
     * <br>因为不存在的 key 被当作一个空哈希表来处理，所以对一个不存在的 key 进行 HMGET 操作将返回一个只带有 nil 值的表。
     * @param key
     * @param fields
     * @return 一个包含多个给定域的关联值的表，表值的排列顺序和给定域参数的请求顺序一样。
     */
    public List<byte[]> hmget(byte[] key, byte[]... fields) {
        List<byte[]> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.hmget(key, fields);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 为哈希表 key 中的域 field 的值加上增量 increment 。
     * 增量也可以为负数，相当于对给定域进行减法操作。
     * 如果 key 不存在，一个新的哈希表被创建并执行 HINCRBY 命令。
     * 如果域 field 不存在，那么在执行命令前，域的值被初始化为 0 。
     * 对一个储存字符串值的域 field 执行 HINCRBY 命令将造成一个错误。
     * 本操作的值被限制在 64 位(bit)有符号数字表示之内。
     * @param key
     * @param field
     * @param value
     * @return 执行 HINCRBY 命令之后，哈希表 key 中域 field 的值。
     */
    public Long hincrBy(byte[] key, byte[] field, long value) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.hincrBy(key, field, value);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 查看哈希表 key 中，给定域 field 是否存在。
     * @param key
     * @param field
     * @return
     * <br>如果哈希表含有给定域，返回 1 。
     * <br>如果哈希表不含有给定域，或 key 不存在，返回 0 。
     */
    public Boolean hexists(byte[] key, byte[] field) {
        Boolean result = false;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.hexists(key, field);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
     * @param key
     * @param field
     * @return 被成功移除的域的数量，不包括被忽略的域。
     */
    public Long hdel(byte[] key, byte[] field) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.hdel(key, field);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 返回哈希表 key 中域的数量。
     * @param key
     * @return  哈希表中域的数量。 当 key 不存在时，返回 0 。
     */
    public Long hlen(byte[] key) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.hlen(key);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 返回哈希表 key 中的所有域。
     * @param key
     * @return 一个包含哈希表中所有域的表。当 key 不存在时，返回一个空表。
     */
    public Set<byte[]> hkeys(byte[] key) {
        Set<byte[]> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.hkeys(key);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 返回哈希表 key 中所有域的值。
     * @param key
     * @return  一个包含哈希表中所有值的表。 当 key 不存在时，返回一个空表。
     */
    public Collection<byte[]> hvals(byte[] key) {
        Collection<byte[]> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.hvals(key);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 返回哈希表 key 中，所有的域和值。 在返回值里，紧跟每个域名(field name)之后是域的值(value)，所以返回值的长度是哈希表大小的两倍。
     * @param key
     * @return  以列表形式返回哈希表的域和域的值。 若 key 不存在，返回空列表。
     */
    public Map<byte[], byte[]> hgetAll(byte[] key) {
        Map<byte[], byte[]> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.hgetAll(key);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * <p>1、将一个或多个值 value 插入到列表 key 的表尾(最右边)。
     * <p>2、如过key 不存在，则，设置新的key，然后执行rpush
     * @param key
     * @param string
     * @return 返回LIST的长度
     */
    public Long rpush(byte[] key, byte[] string) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.rpush(key, string);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * <p>1、将一个或多个值 value 插入到列表 key 的表头，（最左边）
     * <p>2、如过key 不存在，则，设置新的key，然后执行rpush
     * @param key
     * @param string
     * @return 返回LIST的长度
     */
    public Long lpush(byte[] key, byte[] string) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.lpush(key, string);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 1、根据key返回列表的长度
     * 2、如果key不存在则，返回一个空的列表
     * 3、如果key不是列表类型则，报错
     * @param key
     * @return
     */
    public Long llen(byte[] key) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.llen(key);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * <p>获取；列表,根据索引，start end 都是索引值，0 表示第一个，-1 表示最后一个，用来获取所有的列表
     * <p>start end都包含
     * <p>超出索引的范围时，不会报错，会直接，替换为第一个值或者最后一个值,
     * <p>1、当start大于最大的下标还大的话直接返回一个空的列表
     * <p>2、当end比最大的坐标大时返回列表的最大值
     * <p>
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<byte[]> lrange(byte[] key, int start, int end) {
        List<byte[]> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.lrange(key, start, end);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 剪切作用，再字符串为key的列表，除了指定范围之内（start，end 都包含）的，都删除，并保存
     * @param key
     * @param start
     * @param end
     * @return 成功返回 OK
     */
    public String ltrim(byte[] key, int start, int end) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.ltrim(key, start, end);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 根据key获取下标（索引）为index的元素
     * @param key
     * @param index
     * @return 返回下标为index的元素，如果index 超出范围则，返回nil
     */
    public byte[] lindex(byte[] key, int index) {
        byte[] result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.lindex(key, index);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * <p>设置元素下标（索引）为index的值为value
     * <p>当 index 参数超出范围，或对一个空列表( key 不存在)进行 LSET 时，返回一个错误。
     * @param key
     * @param index
     * @param value
     * @return 成功则返回OK，否则返回错误信息
     */
    public String lset(byte[] key, int index, byte[] value) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.lset(key, index, value);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 摘要：根据 count来删除列表中元素等于value的元素
     * <p>1、count > 0 从表头开始删除 count 的绝对值哥元素等于value的元素
     * <p>2、count < 0 从表结尾开始删除 count 的绝对值哥元素等于value的元素 
     * <p>3、count = 删除所有的元素等于value的元素
     * @param key
     * @param count
     * @param value
     * @return 被移除元素的数量，如果KEY 不存在则返回0 
     */
    public Long lrem(byte[] key, int count, byte[] value) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.lrem(key, count, value);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 删除并返回列表元素的第一个（头元素）
     * @param key
     * @return 出栈，返回头元素，key不存在则返回nil
     */
    public byte[] lpop(byte[] key) {
        byte[] result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.lpop(key);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 删除并返回尾元素
     * @param key
     * @return 出栈，返回结尾元素，key 不存在则返回nil
     */
    public byte[] rpop(byte[] key) {
        byte[] result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.rpop(key);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 删除并返回尾元素  沒有元素時该方法会一直阻塞
     * @param key
     * @return 出栈，返回结尾元素，key 不存在则返回nil
     * 存在则返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值
     */
    public List<String> brpop(String key) {
        List<String> result=null;
        Jedis jedis = redisDataSource.getJedisClient();
        if (jedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = jedis.brpop(0,key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(jedis, broken);
        }
        return result;
    }

    /**
     * 1、将一个或者多个member元素加到集合key中，已经存在的集合的元素，将被忽略,如果存在则不存放。
     * 2、如果key不存在则创建一个只包含member元素作为成员的集合
     * @param key
     * @param member
     * @return key 不是集合类型时返回错误，正常返回则返回集合中新的元素的数量，且不包含被忽略的元素
     */
    public Long sadd(byte[] key, byte[] member) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.sadd(key, member);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 返回集合 key 中的所有成员。不存在的 key 被视为空集合。
     * @param key
     * @return 集合中所有的成员,如果key不是集合，则返回错误
     */
    public Set<byte[]> smembers(byte[] key) {
        Set<byte[]> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.smembers(key);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 1、rem :为删除,s：集合;
     * <p>2、即：集合的删除。删除集合key中元素与member相等的元素。不存在的元素，则被忽略。
     * @param key
     * @param member
     * @return 成功删除元素的数量，不包含被忽略的元素
     */
    public Long srem(byte[] key, byte[] member) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.srem(key, member);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 删除并返回集合key中的一个随机元素。
     * @param key
     * @return 被移除的元素，当key不存在或者key是空集合时，返回nil
     */
    public byte[] spop(byte[] key) {
        byte[] result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.spop(key);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 返回集合key中的基数（集合中元素的数量）
     * @param key
     * @return 集合的数量，集合不存在时则返回0，key不是集合则异常
     */
    public Long scard(byte[] key) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.scard(key);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 判断元素是存在集合key中
     * @param key
     * @param member
     * @return
     * 1、如果member存在集合key中，则返回 1 :存在
     * <p>2、如果member不在集合key中，或者key不存在的时候则返回 0 
     * <p>3、如果key不是集合元素则异常
     */
    public Boolean sismember(byte[] key, byte[] member) {
        Boolean result = false;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.sismember(key, member);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 1、返回集合中的一个随机元素
     * <p>2、REDIS > 2.6 可输入一个count参数，
     * <p>2.1如果count> 0   则返回count个随机元素，每个元素各不相同 
     * <p>2.1如果count< 0   则返回count个随机元素，每个元素有可能重复出现 
     * @param key
     * @return
     * 1、提供key返回一个元素，如果元素为空，则返回nil
     * <p>2、如果key不是集合，则出现异常
     */
    public byte[] srandmember(byte[] key) {
        byte[] result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.srandmember(key);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 1、将一个或者多个元素以及score的值加入到有序集合key中
     * <p>2、 如果集合中存在的某个元素跟member相等，则只更新score的值，并通过重新插入的这个memeber更新元素，保证member再正确的位置上。
     * <p>3、score的值可以是整数值或者双精度的浮点数。
     * <p>4、如果key不存在，则创建一个空的有序集合并执行zadd的操作
     * <p>5、如果key存在但是不是有序集合，则返回错误。
     * @param key
     * @param score
     * @param member
     * @return 被添加成功的新的元素的数量，不包含被更新的、已经存在的成员。
     */
    public Long zadd(byte[] key, double score, byte[] member) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.zadd(key, score, member);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 1、按照默认的score从小到大的顺序来排序，如果score的值相等，则按照score的值的成员按照字典排序
     * <p>2、不会出现下标越界的情况，0：表示第一个，-1：表示最后一个。
     * <p>3、如果使用withscores选项，则让成员跟score值一起返回，返回的列表形式以，value1，score1，value2，score2来展示
     * @param key
     * @param start
     * @param end
     * @return
     * 1、返回指定区间的有序集合的成员列表
     * <p>2、如果key不是有序集合则直接报出异常
     */
    public Set<byte[]> zrange(byte[] key, int start, int end) {
        Set<byte[]> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.zrange(key, start, end);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 1、删除有序集合中的一个或者多个成员等于member的成员，不存在的成员则呗忽略，
     * <p>2、当key不存在或者不是有序集合的时候返回一个错误
     * @param key
     * @param member
     * @return 返回删除元素的数量，不包含呗忽略的元素
     */
    public Long zrem(byte[] key, byte[] member) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.zrem(key, member);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 有序集合的加法
     * <p>1、为有序集合的元素为member的score的值增加一个增量score
     * <p>2、传递进来的score的值可以是复数的整型数据，或者浮点型数据
     * <p>3、如果key不存在则直接相当于直接了zadd（key，score，member）
     * @param key
     * @param score
     * @param member
     * @return 元素memeber成员的新的score的值，如果key不是有序集合则直接返回一个异常
     */
    public Double zincrby(byte[] key, double score, byte[] member) {
        Double result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.zincrby(key, score, member);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * <p>1、有序集合中的member的排名并不是，score，有序集合中的排序是按照score从小到达排序的。
     * <p>2、排名以0为低，所以有序集合的默认第一个排名就是0（默认排序，否，则反之）
     * @param key
     * @param member
     * @return
     * 1、如果member是key的成员，返回有序集合key中的为member的排名
     * <p>2、如果member不是可以的成员的，则返回nil
     * <p>3、如果key不是有序集合，则返回一个异常
     */
    public Long zrank(byte[] key, byte[] member) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.zrank(key, member);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * <p>1、有序集合从大到小排序中的member的排名。
     * <p>2、排名以0为低
     * @param key
     * @param member
     * @return
     * 1、如果member是key的成员，返回有序集合key中的为member的排名
     * <p>2、如果member不是可以的成员的，则返回nil
     * <p>3、如果key不是有序集合，则返回一个异常
     */
    public Long zrevrank(byte[] key, byte[] member) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.zrevrank(key, member);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 按照从大到小的顺序或者指定区间的成员
     * @param key
     * @param start
     * @param end
     * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
     */
    public Set<byte[]> zrevrange(byte[] key, int start, int end) {
        Set<byte[]> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.zrevrange(key, start, end);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 1、按照从小到大的顺序或者指定区间的成员
     * <p>2、withscores选项，则让成员跟score值一起返回，返回的列表形式以，value1，score1，value2，score2来展示
     * @param key
     * @param start
     * @param end
     * @return
     * 指定区间内，带有 score 值的有序集成员的列表。
     */
    public Set<Tuple> zrangeWithScores(byte[] key, int start, int end) {
        Set<Tuple> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.zrangeWithScores(key, start, end);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 1、按照从大到小的顺序或者指定区间的成员
     * <p>2、withscores选项，则让成员跟score值一起返回，返回的列表形式以，value1，score1，value2，score2来展示
     * @param key
     * @param start
     * @param end
     * @return
     * 指定区间内，带有 score 值的有序集成员的列表。
     */
    public Set<Tuple> zrevrangeWithScores(byte[] key, int start, int end) {
        Set<Tuple> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.zrevrangeWithScores(key, start, end);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 返回有序集合的数量，即：有序集合的基数
     * @param key
     * @return
     * 1、当 key 存在且是有序集类型时，返回有序集的基数。
     * <p>2、当 key 不存在时，返回 0 。
     */
    public Long zcard(byte[] key) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.zcard(key);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 得到元素为member的score的值
     * @param key
     * @param member
     * @return
     * <p>1、member 成员的 score 值，以字符串形式表示。
     * <p>2、若key不是有序结合则返回一个异常
     */
    public Double zscore(byte[] key, byte[] member) {
        Double result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.zscore(key, member);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 列表从小到大排序，字典排序。(默认排序)
     * @param key
     * @return 排序完成后的列表
     */
    public List<byte[]> sort(byte[] key) {
        List<byte[]> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.sort(key);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 自定义排序,可以根据sortingParameters.desc();倒序，sortingParameters.asc();正序
     * @param key
     * @param sortingParameters
     * @return
     */
    public List<byte[]> sort(byte[] key, SortingParams sortingParameters) {
        List<byte[]> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.sort(key, sortingParameters);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 返回指定score范围之内的成员数量
     * @param key
     * @param min (包含)
     * @param max (包含)
     * @return 指定score范围内的元素数量
     */
    public Long zcount(byte[] key, double min, double max) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.zcount(key, min, max);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 按照默认排序（从小到大） score 在 min跟Max之间的有序集合
     * @param key
     * @param min
     * @param max
     * @return score 在min跟max之间的闭区间的所有的有序集合
     */
    public Set<byte[]> zrangeByScore(byte[] key, double min, double max) {
        Set<byte[]> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.zrangeByScore(key, min, max);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 1、按照默认排序（从小到大） score 在 min跟Max之间的有序集合
     * <p>2、分页数据，int offset 第几页，, int count 每页数量
     * @param key
     * @param min
     * @param max
     * @return score 在min跟max之间的闭区间的所有的有序集合
     */
    public Set<byte[]> zrangeByScore(byte[] key, double min, double max, int offset, int count) {
        Set<byte[]> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.zrangeByScore(key, min, max, offset, count);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 按照默认排序（从小到大） score 在 min跟Max之间的有序集合
     * @param key
     * @param min
     * @param max
     * @return score 在min跟max之间的闭区间的所有的有序集合,并且返回score
     * for (Tuple tuple : result) {
    String element = tuple.getElement();
    double score = tuple.getScore();
    }
     */
    public Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max) {
        Set<Tuple> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.zrangeByScoreWithScores(key, min, max);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 按照默认排序（从小到大） score 在 min跟Max之间的有序集合
     * 分页数据 int offset, int count ，offset：索引，count：数量
     * @param key
     * @param min
     * @param max
     * @return score 在min跟max之间的闭区间的所有的有序集合,并且返回score
     * for (Tuple tuple : result) {
    String element = tuple.getElement();
    double score = tuple.getScore();
    }
     */
    public Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max, int offset, int count) {
        Set<Tuple> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.zrangeByScoreWithScores(key, min, max, offset, count);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 按照倒序（从大到小） score 在 min跟Max之间的有序集合
     * @param key
     * @param min
     * @param max
     * @return score 在min跟max之间的闭区间的所有的有序集合
     */
    public Set<byte[]> zrevrangeByScore(byte[] key, double max, double min) {
        Set<byte[]> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.zrevrangeByScore(key, max, min);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 1、按照默认排序（从大到小） score 在 min跟Max之间的有序集合
     * <p>2、分页数据，int offset 第几页，, int count 每页数量
     * @param key
     * @param min
     * @param max
     * @return score 在min跟max之间的闭区间的所有的有序集合
     */
    public Set<byte[]> zrevrangeByScore(byte[] key, double max, double min, int offset, int count) {
        Set<byte[]> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.zrevrangeByScore(key, max, min, offset, count);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 按照默认排序（从大到小） score 在 min跟Max之间的有序集合
     * @param key
     * @param min
     * @param max
     * @return score 在min跟max之间的闭区间的所有的有序集合,并且返回score
     * for (Tuple tuple : result) {
    String element = tuple.getElement();
    double score = tuple.getScore();
    }
     */
    public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min) {
        Set<Tuple> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.zrevrangeByScoreWithScores(key, max, min);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 按照默认排序（从大到小） score 在 min跟Max之间的有序集合
     * 分页数据 int offset, int count ，offset：索引，count：数量
     * @param key
     * @param min
     * @param max
     * @return score 在min跟max之间的闭区间的所有的有序集合,并且返回score
     * for (Tuple tuple : result) {
    String element = tuple.getElement();
    double score = tuple.getScore();
    }
     */
    public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min, int offset, int count) {
        Set<Tuple> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.zrevrangeByScoreWithScores(key, max, min, offset, count);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 1、移除有序集 key 中，指定排名(rank)区间内的所有成员。
     * <p>2、区间分别以下标参数 start 和 stop 指出，包含 start 和 stop 在内。
     * <p>3、下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。
     * <p>4、你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。
     * @param key
     * @param start
     * @param end
     * @return 被移除的元素的数量
     */
    public Long zremrangeByRank(byte[] key, int start, int end) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.zremrangeByRank(key, start, end);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 移除有序集 key 中，所有 score 值介于 start 和 end 之间(包括等于 start 或 end )的成员。
     * <p>2、自版本2.1.6开始， score 值等于 start 或 end 的成员也可以不包括在内，
     * <p>3、下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。
     * <p>4、你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Long zremrangeByScore(byte[] key, double start, double end) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.zremrangeByScore(key, start, end);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * <p>1、将value插入到key列表中，在pivot之前或者之后
     * <p>2、当 pivot 不存在于列表 key 时，不执行任何操作。
     * <p>3、当 key 不存在时， key 被视为空列表，不执行任何操作。
     * @param key
     * @param where LIST_POSITION.BEFORE :之前，  LIST_POSITION.AFTER：之后
     * @param pivot
     * @param value
     * @return
     * <p>如果命令执行成功，返回插入操作完成之后，列表的长度。
     * <p>如果没有找到 pivot ，返回 -1 。
     * <p>如果 key 不存在或为空列表，返回 0 。
     */
    public Long linsert(byte[] key, BinaryClient.LIST_POSITION where, byte[] pivot, byte[] value) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {

            result = shardedJedis.linsert(key, where, pivot, value);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /*****************************************************************************下列方法无使用效果*******************************************************************************************/

    @SuppressWarnings("deprecation")
    public List<Object> pipelined(ShardedJedisPipeline shardedJedisPipeline) {
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        List<Object> result = null;
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.pipelined(shardedJedisPipeline);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    public Jedis getShard(byte[] key) {
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        Jedis result = null;
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.getShard(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    public Jedis getShard(String key) {
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        Jedis result = null;
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.getShard(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    public JedisShardInfo getShardInfo(byte[] key) {
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        JedisShardInfo result = null;
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.getShardInfo(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    public JedisShardInfo getShardInfo(String key) {
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        JedisShardInfo result = null;
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.getShardInfo(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    public String getKeyTag(String key) {
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        String result = null;
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.getKeyTag(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    public Collection<JedisShardInfo> getAllShardInfo() {
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        Collection<JedisShardInfo> result = null;
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.getAllShardInfo();

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    public Collection<Jedis> getAllShards() {
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        Collection<Jedis> result = null;
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.getAllShards();

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }
}
