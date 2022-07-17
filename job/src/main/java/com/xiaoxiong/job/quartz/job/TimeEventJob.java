package com.xiaoxiong.job.quartz.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author 六月
 * @Date 2022/7/17 15:56
 * @Version 1.0
 */
@Component
@DisallowConcurrentExecution
public class TimeEventJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("TimeEventJob正在执行..." + LocalDateTime.now());
    }
}

