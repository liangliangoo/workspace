package com.xiaoxiong.demo.job;

import lombok.Setter;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/24  20:52
 */
public class HelloJob implements Job {

    /**
     * 在jobDetail中如果传递的参数有message那么会自动通过set方法注入
     * 如果trigger 中也出现了相同名的这个属性
     * 那么会依赖于trigger中的message
     */
    @Setter
    private String message;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd  HH:mm:ss");
        String format = simpleDateFormat.format(date);
        System.out.println(format);

        System.out.println(jobExecutionContext.getJobDetail().getKey().getGroup());
        System.out.println(jobExecutionContext.getJobDetail().getKey().getClass());
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        System.out.println(jobDataMap.get("testMap"));

        //获取任务的实例对象
        System.out.println(jobExecutionContext.getJobInstance().getClass());

        //获取执行任务的时间
        Date fireTime = jobExecutionContext.getFireTime();

        System.out.println("message 的值是======》" + message);
    }

}
