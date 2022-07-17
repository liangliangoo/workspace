package com.xiaoxiong.job.quartz.handler;

import com.alibaba.fastjson.JSONObject;
import com.xiaoxiong.job.domain.JobInfo;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * @Author 六月
 * @Date 2022/7/17 15:57
 * @Version 1.0
 */
@Component
public class JobHandler {

    @Autowired
    private Scheduler quartzScheduler;

    /**
     * 添加任务
     */
    @SuppressWarnings("unchecked")
    public void addJob(JobInfo jobInfo) throws SchedulerException, ClassNotFoundException {
        Objects.requireNonNull(jobInfo, "任务信息不能为空");

        // 生成job key
        JobKey jobKey = JobKey.jobKey(jobInfo.getJobName(), jobInfo.getJobGroup());
        // 当前任务不存在才进行添加
        if (!quartzScheduler.checkExists(jobKey)) {
            Class<Job> jobClass = (Class<Job>) Class.forName(jobInfo.getClassName());
            // 任务明细
            JobDetail jobDetail = JobBuilder
                    .newJob(jobClass)
                    .withIdentity(jobKey)
                    .withIdentity(jobInfo.getJobName(), jobInfo.getJobGroup())
                    .withDescription(jobInfo.getJobName())
                    .build();
            // 配置信息
            jobDetail.getJobDataMap().put("config", jobInfo.getConfig());
            // 定义触发器
            TriggerKey triggerKey = TriggerKey.triggerKey(jobInfo.getTriggerName(), jobInfo.getTriggerGroup());
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(triggerKey)
                    .withSchedule(CronScheduleBuilder.cronSchedule(jobInfo.getCron()))
                    .build();
            quartzScheduler.scheduleJob(jobDetail, trigger);
        } else {
            throw new SchedulerException(jobInfo.getJobName() + "任务已存在，无需重复添加");
        }
    }

    /**
     * 任务暂停
     */
    public void pauseJob(String jobGroup, String jobName) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        if (quartzScheduler.checkExists(jobKey)) {
            quartzScheduler.pauseJob(jobKey);
        }
    }

    /**
     * 继续任务
     */
    public void continueJob(String jobGroup, String jobName) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        if (quartzScheduler.checkExists(jobKey)) {
            quartzScheduler.resumeJob(jobKey);
        }
    }

    /**
     * 删除任务
     */
    public boolean deleteJob(String jobGroup, String jobName) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        if (quartzScheduler.checkExists(jobKey)) {
            return quartzScheduler.deleteJob(jobKey);
        }
        return false;
    }

    /**
     * 获取任务信息
     */
    public JobInfo getJobInfo(String jobGroup, String jobName) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        if (!quartzScheduler.checkExists(jobKey)) {
            return null;
        }
        List<? extends Trigger> triggers = quartzScheduler.getTriggersOfJob(jobKey);
        if (Objects.isNull(triggers)) {
            throw new SchedulerException("未获取到触发器信息");
        }
        TriggerKey triggerKey = triggers.get(0).getKey();
        Trigger.TriggerState triggerState = quartzScheduler.getTriggerState(triggerKey);
        JobDetail jobDetail = quartzScheduler.getJobDetail(jobKey);

        JobInfo jobInfo = new JobInfo();
        jobInfo.setJobName(jobGroup);
        jobInfo.setJobGroup(jobName);
        jobInfo.setTriggerName(triggerKey.getName());
        jobInfo.setTriggerGroup(triggerKey.getGroup());
        jobInfo.setClassName(jobDetail.getJobClass().getName());
        jobInfo.setStatus(triggerState.toString());

        if (Objects.nonNull(jobDetail.getJobDataMap())) {
            jobInfo.setConfig(JSONObject.toJSONString(jobDetail.getJobDataMap()));
        }

        CronTrigger theTrigger = (CronTrigger) triggers.get(0);
        jobInfo.setCron(theTrigger.getCronExpression());
        return jobInfo;
    }

}
