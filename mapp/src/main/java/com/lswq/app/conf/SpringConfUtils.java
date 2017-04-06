package com.lswq.app.conf;

import com.lswq.app.util.spring.Constants;
import org.springframework.stereotype.Component;

/**
 * Created by zhangsw on 2016/12/30.
 */
@Component
public class SpringConfUtils {

    /**
     * 图片地址
     */
    @LqConfig(clientId = Constants.APP_KEY, key = "${imagePath}")
    public static String imagePath;
    
    @LqConfig(clientId = Constants.APP_KEY, key = "${rediskey.RedisLock}")
    public static String redisLock;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        SpringConfUtils.imagePath = imagePath;
    }

    public String getRedisLock() {
        return redisLock;
    }

    public void setRedisLock(String redisLock) {
        SpringConfUtils.redisLock = redisLock;
    }
}
