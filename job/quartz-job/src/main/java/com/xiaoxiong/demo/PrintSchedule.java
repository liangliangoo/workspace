package com.xiaoxiong.demo;

import com.xiaoxiong.demo.job.PrintJob;
import com.xiaoxiong.demo.listener.PrintJobListener;
import com.xiaoxiong.demo.listener.PrintTriggerListener;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;

/**
 * @Author 六月
 * @Date 2023/3/1 17:35
 * @Version 1.0
 */
public class PrintSchedule {

    public static void main(String[] args) throws SchedulerException {
        // 1、获取调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        // 2、构造jobDetail
        JobDetail job = JobBuilder.newJob(PrintJob.class)
                .withIdentity("printJob", "testJobGroup").usingJobData("data1", 1).build();
        System.out.println("main" + job.hashCode());
        // 3、定义触发器, 会马上执行一次, 接着5秒执行一次
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("testTrigger", "testTriggerGroup")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(5))
                .build();
        // 使用触发器调度任务的执行
        scheduler.scheduleJob(job, trigger);

        // 创建并注册一个全局的Job Listener
        // scheduler.getListenerManager().addJobListener(new MyJobListener(), EverythingMatcher.allJobs());
        // 创建并注册一个局部的Job Listener，表示指定的任务Job
        scheduler.getListenerManager().addJobListener(new PrintJobListener(), KeyMatcher.keyEquals(JobKey.jobKey("printJob", "testJobGroup")));

        // 创建并注册一个全局的Trigger Listener
        // scheduler.getListenerManager().addTriggerListener(new MyTriggerListener(), EverythingMatcher.allTriggers());
        // 创建并注册一个局部的Trigger Listener
        scheduler.getListenerManager().addTriggerListener(new PrintTriggerListener("printTriggerListener"),
                KeyMatcher.keyEquals(TriggerKey.triggerKey("testTrigger", "testTriggerGroup")));

        // 开启任务
        scheduler.start();
    }

}
