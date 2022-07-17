package com.xiaoxiong.job.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executor;

/**
 * @Author 六月
 * @Date 2022/7/17 13:19
 * @Version 1.0
 */
@Component
@ConfigurationProperties(
        prefix = "threadpool",
        ignoreUnknownFields = false
)
public class ThreadPoolConfig {
    private static final Logger log = LoggerFactory.getLogger(ThreadPoolConfig.class);
    public static final String ASYNC_EXECUTOR_NAME = "asyncExecutor";
    private int corePoolSize = 4;
    private int maxPoolSize = 8;
    private int queueCapacity = 10000;
    private String threadNamePrefix = "AsyncThread-";

    public ThreadPoolConfig() {
    }

    public int getCorePoolSize() {
        return this.corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaxPoolSize() {
        return this.maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public int getQueueCapacity() {
        return this.queueCapacity;
    }

    public void setQueueCapacity(int queueCapacity) {
        this.queueCapacity = queueCapacity;
    }

    public String getThreadNamePrefix() {
        return this.threadNamePrefix;
    }

    public void setThreadNamePrefix(String threadNamePrefix) {
        this.threadNamePrefix = threadNamePrefix;
    }

    @PostConstruct
    private void init() {
        log.info("============== ThreadPoolConfig init ===============");
        log.info("threadNamePrefix:{}, corePoolSize:{}, maxPoolSize:{}, queueCapacity:{}", new Object[]{this.threadNamePrefix, this.corePoolSize, this.maxPoolSize, this.queueCapacity});
    }

    @Bean(
            name = {"asyncExecutor"}
    )
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setTaskDecorator(runnable -> runnable);
        executor.setCorePoolSize(this.corePoolSize);
        executor.setMaxPoolSize(this.maxPoolSize);
        executor.setQueueCapacity(this.queueCapacity);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setThreadNamePrefix(this.threadNamePrefix);
        executor.initialize();
        return executor;
    }
}
