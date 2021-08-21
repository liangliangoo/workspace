package com.example.batchdemo.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import java.util.Date;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/8/15 16:43
 */
public class MyJobListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        jobExecution.setCreateTime(new Date());

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("hello bike");
    }
}
