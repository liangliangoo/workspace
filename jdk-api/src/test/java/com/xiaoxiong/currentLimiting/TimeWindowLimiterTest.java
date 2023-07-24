package com.xiaoxiong.currentLimiting;

import com.xiaoxiong.learning.currentLimiting.TimeWindowLimiter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author 六月
 * @Date 2023/7/23 16:28
 * @Version 1.0
 */
public class TimeWindowLimiterTest {

    @Test
    void test_timeWindowLimiter() throws IOException {
        TimeWindowLimiter limiter = TimeWindowLimiter.create(60L, 100L);
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(() ->
            System.out.println("限流结果 : " + limiter.acquire())
        , 0, 500, TimeUnit.MILLISECONDS);
        System.in.read();
    }

}
