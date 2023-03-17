package com.xiaoxiong.demo.job;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.PersistJobDataAfterExecution;

/**
 * @PersistJobDataAfterExecution
 * @DisallowConcurrentExecution
 * 这两个注解强烈建议一起使用
 * 因为 如果是有状态的job 很有可能出现并发问题 即：两个线程同时修改内存或则DB中的数据
 *
 * @Author 六月
 * @Date 2023/3/1 17:29
 * @Version 1.0
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
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
