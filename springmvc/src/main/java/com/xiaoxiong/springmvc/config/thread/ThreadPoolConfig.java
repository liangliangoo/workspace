package com.xiaoxiong.springmvc.config.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author 六月
 * @Date 2022/8/13 10:13
 * @Version 1.0
 * 线程池配置
 */
@Slf4j
@Configuration
public class ThreadPoolConfig {

    private static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2 + 1;
    private static final int MAX_POOL_SIZE = CORE_POOL_SIZE * 2;
    private static final int KEEP_ALIVE_TIME = 60;
    private static final int QUEUE_CAPACITY = 1000;
    private static final String BIZ_THREAD_POLL = "%s-thread-pool-";

    @Bean("logHandlerThreadPool")
    public ThreadPoolTaskExecutor logHandlerThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 基础参数设置
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setQueueCapacity(QUEUE_CAPACITY);
        executor.setThreadNamePrefix(String.format(BIZ_THREAD_POLL, "biz-log"));
        executor.setKeepAliveSeconds(KEEP_ALIVE_TIME);
        // 执行策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待任务完成后，才会关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 当任务等待一定时间还未执行的时候，直接终止任务,避免阻塞
        executor.setAwaitTerminationSeconds(KEEP_ALIVE_TIME);
        executor.initialize();
        return executor;
    }


    @Bean("reportHandlerThreadPool")
    public ThreadPoolTaskExecutor reportHandlerThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 基础参数设置
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setQueueCapacity(QUEUE_CAPACITY);
        executor.setThreadNamePrefix(String.format(BIZ_THREAD_POLL, "biz-report"));
        executor.setKeepAliveSeconds(KEEP_ALIVE_TIME);
        // 执行策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待任务完成后，才会关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 当任务等待一定时间还未执行的时候，直接终止任务,避免阻塞
        executor.setAwaitTerminationSeconds(KEEP_ALIVE_TIME);
        executor.initialize();
        return executor;
    }

}
