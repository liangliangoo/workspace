package com.xiaoxiong.demo.job;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

/**
 * @Author 六月
 * @Date 2023/3/1 17:29
 * @Version 1.0
 */
@Slf4j
public class PrintJob implements Job {


    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        try {
            log.debug("execute context {}",JSON.toJSONString(jobExecutionContext.getJobDetail()));
            log.debug("jobDetail hashCode {}", jobExecutionContext.getJobDetail().hashCode());
        } catch (Exception e) {
            log.debug("PrintJob error {}", e);
        }
    }

}
