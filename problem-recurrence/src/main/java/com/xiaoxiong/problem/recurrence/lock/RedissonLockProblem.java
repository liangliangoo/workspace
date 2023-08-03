package com.xiaoxiong.problem.recurrence.lock;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Author 六月
 * @Date 2023/8/3 15:57
 * @Version 1.0
 */
public class RedissonLockProblem {

    private static RedissonClient redissonClient;

    /**
     * redis://redis-matrix-master.database.svc.cluster.local:6379
     * cjnM0pfCx0
     *
     * @param redisAddr
     * @param password
     * @return
     */
    public static RedissonClient getRedissonClient(String redisAddr, String password) {
        if (Objects.nonNull(redissonClient)) {
            return redissonClient;
        }
        synchronized (RedissonLockProblem.class) {
            if (Objects.isNull(redissonClient)) {
                Config config = new Config();
                config.useSingleServer()
                        .setAddress(redisAddr)
                        .setPassword(password)
                        .setDatabase(0);
                //获取客户端
                redissonClient = Redisson.create(config);
            }
        }
        return redissonClient;
    }

    /**
     * 指定分部锁 时间存在并发安全问题
     * @return
     */
    public Object lockWithTimeParams() {
        String lockWithTimeParams = "lockWithTimeParams";
        RLock lock = redissonClient.getLock(lockWithTimeParams);
        Object obj = findObj();
        if (obj != null) {
            return obj;
        }
        try {
            lock.lock(6L, TimeUnit.SECONDS);
            obj = findObj();
            if (obj != null) {
                // 构造obj 对象 & 插入数据库
                insert();
                return obj;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
        return null;
    }

    private Object findObj() {
        return null;
    }

    private void insert() {
        return;
    }



}
