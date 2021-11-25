package com.xiaoxiong.springbatch.config.decider;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.stereotype.Component;

/**
 * 决策器
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/24  10:33
 */
@Component
public class MyDecider implements JobExecutionDecider {

    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {

        return new FlowExecutionStatus("1");
    }
}
