package com.xiaoxiong.demo.job;

import com.alibaba.fastjson.JSON;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @Author 六月
 * @Date 2023/3/1 17:29
 * @Version 1.0
 */
public class PrintJob implements Job {


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(JSON.toJSON(jobExecutionContext));
        System.out.println(jobExecutionContext.getJobDetail().hashCode());
    }

}
