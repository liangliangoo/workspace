package com.xiaoxiong.job.quartz.schedule;

import com.xiaoxiong.job.quartz.job.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @Author 六月
 * @Date 2022/7/17 15:04
 * @Version 1.0
 */
public class HelloSchedule {

    public static void main(String[] args) throws SchedulerException {

        //创建调度器实例
        Scheduler defaultScheduler = StdSchedulerFactory.getDefaultScheduler();

        //创建任务实例
        JobDetail build = JobBuilder.newJob(HelloJob.class)
                .withIdentity("test1", "xiaoxiong")
                //将一些必要的参数传递给任务（HelloJob）  ，通过context即可获取
                .usingJobData("testMap","testValue")
                .usingJobData("message","jobDetail")
                .build();

        // 创建触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "xiaoxiao")
                //也可以通过自定的时间段触发   .startAt(date)  endAt(date)
                .startNow() //立即开始
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(10)
                        //设置执行次数  n+1次
                        .withRepeatCount(11))
                .usingJobData("message", "trigger")
                .build();

        //产生关联
        defaultScheduler.scheduleJob(build, trigger);

        //启动
        defaultScheduler.start();
    }

}
