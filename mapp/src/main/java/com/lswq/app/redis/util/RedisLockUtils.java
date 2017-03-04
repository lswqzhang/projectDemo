package com.lswq.app.redis.util;

import com.lswq.app.redis.impl.RedisClientTemplate;
import com.lswq.app.util.spring.SpringContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.Set;

/**
 * redis 工具类
 *
 * @author zhangsw
 * @version V1.0
 */
public class RedisLockUtils {

    private static final Logger logger = LoggerFactory.getLogger(RedisLockUtils.class);

    public static int LOCK_SECONDS = 60;// key过期时间单位：秒

    public static int MAX_GET_LOCK_COUNT = 60;// 获取锁最高次数

    private static final String OK_CODE = "OK";
    private static final String OK_MULTI_CODE = "+OK";

    private static RedisLockUtils instance;

    private RedisClientTemplate redisClientTemplate;


    /**
     * 单例模式
     *
     * @return RedisLockUtils
     * @author lifangyu
     */
    public static RedisLockUtils getInstance() {
        if (instance == null) {
            synchronized (new Object()) {
                if (instance == null) {
                    instance = new RedisLockUtils();
                }
            }
        }
        return instance;
    }

    public RedisLockUtils() {
        super();
        redisClientTemplate = SpringContextUtil.getBean("redisClientTemplate");
    }

    /**
     * 初始化全局参数实例
     *
     * @param lock_seconds
     * @param max_get_lock_count
     */
    public RedisLockUtils(int lock_seconds, int max_get_lock_count) {
        super();
        RedisLockUtils.LOCK_SECONDS = lock_seconds;
        RedisLockUtils.MAX_GET_LOCK_COUNT = max_get_lock_count;
        redisClientTemplate = SpringContextUtil.getBean("redisClientTemplate");
    }


    /**
     * 业务加锁[设置有效时间60s,防止系统宕机宕机导致不能获取锁]
     *
     * @param key
     * @param value
     * @return
     * @throws InterruptedException
     * @author lifangyu
     */
    public BaseResponseVo businesslock(String key, String value) {
        int count = 0;
        while (!lock(key, value, LOCK_SECONDS)) {
            if (count++ > MAX_GET_LOCK_COUNT) {
                return new BaseResponseVo(BaseResponseVo.CODE_FAILS_MAX_TIMES,
                        "获取锁" + key + "超过最高次数[" + MAX_GET_LOCK_COUNT + "]");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.error("Thread.sleep出现异常！");
            }
        }
        return new BaseResponseVo(BaseResponseVo.CODE_SUCC, "获取锁" + key + "成功");
    }

    /**
     * 加锁:增加key的失效时间：单位秒
     *
     * @param key
     * @param value
     * @param seconds
     * @return
     * @author lifangyu
     */
    public boolean lock(String key, String value, int seconds) {
        String resu = "";
        String flag = redisClientTemplate.get(key);
        if (null != flag && StringUtils.isNotBlank(flag)) {
            // redis 中已经存在锁，须要等待 释放锁后在执行
            logger.debug("redis 中已经存在key,请稍后再来获取锁...");
            return false;
        }
        if (seconds > 0) {
            resu = redisClientTemplate.setex(key, seconds, value);
        } else {
            resu = redisClientTemplate.set(key, value);
        }
        if (resu != null && (OK_CODE.toUpperCase(Locale.SIMPLIFIED_CHINESE).equals(resu.toUpperCase(Locale.SIMPLIFIED_CHINESE)) || OK_MULTI_CODE.toUpperCase(Locale.SIMPLIFIED_CHINESE).equals(resu.toUpperCase(Locale.SIMPLIFIED_CHINESE)))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 加锁：key的过期时间seconds默认为：秒
     *
     * @param key
     * @param value
     * @return boolean
     * @author lifangyu
     */
    public boolean lock(String key, String value) {
        return lock(key, value, LOCK_SECONDS);
    }

    /**
     * 解锁：支持多个key的字符串数组
     *
     * @param key
     * @return boolean
     * @author lifangyu
     */
    public boolean unlock(String key) {
        return redisClientTemplate.del(key) != null ? true : false;
    }

    /**
     * 解锁set<String>
     *
     * @param keys
     * @return boolean
     * @author lifangyu
     */
    public boolean unlock(Set<String> keys) {
        if (keys != null && !keys.isEmpty()) {
            for (String key : keys) {
                if (!unlock(key)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 执行结果响应类
     *
     * @author lifangyu
     * @version V1.0
     */
    public static class BaseResponseVo {
        public String code;
        public String message;

        /**
         * 返回代码：成功
         */
        public static String CODE_SUCC = "00000";

        public static String message_succ = "加锁成功!";
        /**
         * 返回代码：失败
         */
        public static String CODE_FAILS = "00001";

        public static String message_fails = "加锁失败!";

        /**
         * 返回代码：失败-加锁次数超过限制的最大次数
         */
        public static String CODE_FAILS_MAX_TIMES = "00009";

        /**
         *
         */
        public BaseResponseVo() {
            super();
        }

        /**
         * @param code
         * @param message
         */
        public BaseResponseVo(String code, String message) {
            super();
            this.code = code;
            this.message = message;
        }

        /**
         * @return the code
         */
        public String getCode() {
            return code;
        }

        /**
         * @param code the code to set
         */
        public void setCode(String code) {
            this.code = code;
        }

        /**
         * @return the message
         */
        public String getMessage() {
            return message;
        }

        /**
         * @param message the message to set
         */
        public void setMessage(String message) {
            this.message = message;
        }
    }

}
