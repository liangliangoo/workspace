package com.xiaoxiong.learning.currentLimiting;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.Builder;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 时间窗口限流算法
 *
 * @Author 六月
 * @Date 2023/7/23 16:10
 * @Version 1.0
 */
@Builder
public class TimeWindowLimiter {

    private LoadingCache<Long, AtomicInteger> counter;
    private Long windowSize;
    private Long limit;
    private TimeUnit unit;

    public static TimeWindowLimiter create(Long windowSize, Long limit) {
        return create(windowSize, limit, TimeUnit.SECONDS);
    }

    public static TimeWindowLimiter create(Long windowSize, Long limit,TimeUnit unit) {
        return TimeWindowLimiter.builder()
                .windowSize(windowSize)
                .limit(limit)
                .unit(unit)
                .counter(CacheBuilder.newBuilder()
                        .expireAfterWrite(windowSize, unit).build(new CacheLoader<Long, AtomicInteger>() {
                            @Override
                            public AtomicInteger load(Long key) throws Exception {
                                return new AtomicInteger(0);
                            }
                        }))
                .build();
    }

    public Boolean acquire() {
        // 确定窗口
        Long window = System.currentTimeMillis() / TimeUnit.MILLISECONDS.convert(windowSize, unit);
        try {
            AtomicInteger atomicInteger = counter.get(window);
            int curCount = atomicInteger.addAndGet(1);
            if (curCount > limit) {
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        } catch (ExecutionException e) {
            return Boolean.FALSE;
        }
    }

}
