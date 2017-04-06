package test.com.lswq.app.redis;

import com.lswq.app.redis.impl.RedisClientTemplate;
import com.lswq.app.redis.util.RedisDSReentrantLock;
import com.lswq.app.conf.SpringConfUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import test.com.lswq.web.BaseTest;

import java.text.MessageFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangsw on 2016/12/30.
 */
public class RedisClientTemplateTest extends BaseTest {


    @Autowired
    @Qualifier("redisClientTemplate")
    private RedisClientTemplate redisClient;


    @Test
    public void redisSet() {

        String lockDept = MessageFormat.format(SpringConfUtils.redisLock, "LockDept");

        redisClient.set(lockDept, "11111");
    }


    @Test
    public void redisSingleThreadDSLock() throws InterruptedException {
        final String redisKey = "keyrs";//redis key
        final int keyExpireSecond = 105;//redis key值的有效期 单位秒


        ExecutorService executorService = Executors.newFixedThreadPool(10);


        final RedisDSReentrantLock redisLock = new RedisDSReentrantLock(redisClient);

        redisLock.lock(redisKey, keyExpireSecond); //执行业务逻辑

        
       
        redisLock.unlock(redisKey);

        while (true) {
            Thread.sleep(1000); //打印调试信息 
            System.out.println(redisLock.getContentOfKeyLockMap(redisKey));
        }
    }

    @Test
    public void redisDSLock() throws InterruptedException {
        final String redisKey = "keyrs";//redis key
        final int keyExpireSecond = 5;//redis key值的有效期 单位秒


        ExecutorService executorService = Executors.newFixedThreadPool(10);

        final int size = 321;

        final RedisDSReentrantLock redisLock = new RedisDSReentrantLock(redisClient);

        for (int i = 1; i <= size; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        redisLock.tryLock(redisKey, keyExpireSecond, 10, TimeUnit.SECONDS); //执行业务逻辑 
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        redisLock.unlock(redisKey);
                    }
                }
            });
        }

        while (true) {
            Thread.sleep(1000); //打印调试信息 
            System.out.println(redisLock.getContentOfKeyLockMap(redisKey));
        }
    }
}
