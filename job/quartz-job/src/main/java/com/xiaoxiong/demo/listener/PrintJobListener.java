package com.xiaoxiong.demo.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * @Author 六月
 * @Date 2023/3/4 19:34
 * @Version 1.0
 */
public class PrintJobListener implements JobListener {


    @Override
    public String getName() {
        String name = this.getClass().getSimpleName();
        System.out.println("监听器的名称是：" + name);
        return name;
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        String name = context.getJobDetail().getKey().getName();
        System.out.println("Job的名称是：" + name + "          Scheduler在JobDetail将要被执行时调用的方法");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        String name = context.getJobDetail().getKey().getName();
        System.out.println("Job的名称是：" + name + "          Scheduler在JobDetail即将被执行，但又被TriggerListener否决时会调用该方法");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        String name = context.getJobDetail().getKey().getName();
        System.out.println("Job的名称是：" + name + "          Scheduler在JobDetail被执行之后调用这个方法");
    }


}
