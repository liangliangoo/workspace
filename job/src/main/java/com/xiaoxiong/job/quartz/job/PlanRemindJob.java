package com.xiaoxiong.job.quartz.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author 六月
 * @Date 2022/7/17 15:55
 * @Version 1.0
 */
@Component
public class PlanRemindJob extends QuartzJobBean {


    /**
     * Execute the actual job. The job data map will already have been
     * applied as bean property values by execute. The contract is
     * exactly the same as for the standard Quartz execute method.
     *
     * @param context
     * @see #execute
     */
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println("PlanRemindJob正在执行..." + LocalDateTime.now());
    }

}
