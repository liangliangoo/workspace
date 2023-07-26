package com.xiaoxiong.learning.currentLimiting;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class SlidingWindowLimiterTest {

    @Test
    void acquire() throws IOException {
        SlidingWindowLimiter limiter = SlidingWindowLimiter.create(50, 3600);
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(() ->
                        System.out.println("限流结果 : " + limiter.acquire())
                , 0, 500, TimeUnit.MILLISECONDS);
        System.in.read();
    }
}