package com.xiaoxiong.base.engine;

import com.linkedin.parseq.Engine;
import com.linkedin.parseq.EngineBuilder;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 并发执行引擎
 * @author xiongliang
 * @version 1.0
 * @since 2021/8/22 9:16
 */
public class ParseqEngine {

    private Engine engine;

    ExecutorService taskScheduler;

    ScheduledExecutorService timerScheduler;

    @PostConstruct
    private void initEngine() {
        int count = Runtime.getRuntime().availableProcessors();
        taskScheduler = Executors.newFixedThreadPool(count * 4 + 1);
        timerScheduler = Executors.newSingleThreadScheduledExecutor();
        engine = new EngineBuilder()
                .setTaskExecutor(taskScheduler)
                .setTimerScheduler(timerScheduler)
                .build();
    }

    @PreDestroy
    private void dropEngine() {
        engine.shutdown();
        try {
            engine.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        taskScheduler.shutdown();
        timerScheduler.shutdown();
    }

    public Engine getEngine() {
        return engine;
    }

}
