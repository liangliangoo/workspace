package com.xiaoxiong.job.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * @Author 六月
 * @Date 2022/7/17 13:15
 * @Version 1.0
 * 配置spring scheduler线程池的配置
 */
@Configuration
public class SchedulerConfig implements SchedulingConfigurer {
    private final int POOL_SIZE = 10;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(POOL_SIZE);
        threadPoolTaskScheduler.setThreadNamePrefix("my-scheduled-task-pool-");
        threadPoolTaskScheduler.initialize();
        scheduledTaskRegistrar.setTaskScheduler(threadPoolTaskScheduler);
    }

}
