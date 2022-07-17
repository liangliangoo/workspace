package com.xiaoxiong.job.controller;

import com.xiaoxiong.job.domain.JobInfo;
import com.xiaoxiong.job.quartz.handler.JobHandler;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author 六月
 * @Date 2022/7/17 15:59
 * @Version 1.0
 */
@RestController
@RequestMapping("/job")
public class QuartzController {

    @Autowired
    private JobHandler jobHandler;
    @Autowired
    private Scheduler quartzScheduler;

    /**
         * http://localhost:8888/job/all
     */
    @RequestMapping("/all")
    public List<JobInfo> list() throws SchedulerException {
        List<JobInfo> jobInfos = new ArrayList<>();
        List<String> triggerGroupNames = quartzScheduler.getTriggerGroupNames();
        for (String triggerGroupName : triggerGroupNames) {
            Set<TriggerKey> triggerKeySet = quartzScheduler
                    .getTriggerKeys(GroupMatcher.triggerGroupEquals(triggerGroupName));
            for (TriggerKey triggerKey : triggerKeySet) {
                Trigger trigger = quartzScheduler.getTrigger(triggerKey);
                JobKey jobKey = trigger.getJobKey();
                JobInfo jobInfo = jobHandler.getJobInfo(jobKey.getGroup(), jobKey.getName());
                jobInfos.add(jobInfo);
            }
        }
        return jobInfos;
    }

    /**
     * http://localhost:8888/job/add
     *
     * {
     * 	"className": "com.codecoord.springboot.quartz.job.PlanRemindJob",
     * 	"config": "配置信息，例如存储json",
     * 	"cron": "0/3 * * * * ?",
     * 	"jobGroup": "STANDARD_JOB_GROUP",
     * 	"jobName": "计划任务通知任务",
     * 	"triggerGroup": "STANDARD_TRIGGER_GROUP",
     * 	"triggerName": "计划任务通知触发器"
     * }
     *
     * {
     * 	"className": "com.xiaoxiong.job.quartz.job.TimeEventJob",
     * 	"config": "配置信息，例如存储json",
     * 	"cron": "0/10 * * * * ?",
     * 	"jobGroup": "STANDARD_JOB_GROUP",
     * 	"jobName": "时间通知任务",
     * 	"triggerGroup": "STANDARD_TRIGGER_GROUP",
     * 	"triggerName": "时间通知触发器"
     * }
     */
    @PostMapping("/add")
    public JobInfo addJob(@RequestBody JobInfo jobInfo) throws SchedulerException, ClassNotFoundException {
        jobHandler.addJob(jobInfo);
        return jobInfo;
    }

    /**
     * http://localhost:8888/job/pause?jobGroup=STANDARD_JOB_GROUP&jobName=计划任务通知任务
     * http://localhost:8888/job/pause?jobGroup=STANDARD_JOB_GROUP&jobName=时间通知任务
     */
    @RequestMapping("/pause")
    public void pauseJob(@RequestParam("jobGroup") String jobGroup, @RequestParam("jobName") String jobName)
            throws SchedulerException {
        jobHandler.pauseJob(jobGroup, jobName);
    }

    /**
     * http://localhost:8888/job/continue?jobGroup=STANDARD_JOB_GROUP&jobName=计划任务通知任务
     * http://localhost:8888/job/continue?jobGroup=STANDARD_JOB_GROUP&jobName=时间通知任务
     */
    @RequestMapping("/continue")
    public void continueJob(@RequestParam("jobGroup") String jobGroup, @RequestParam("jobName") String jobName)
            throws SchedulerException {
        jobHandler.continueJob(jobGroup, jobName);
    }

    /**
     * http://localhost:8888/job/delete?jobGroup=STANDARD_JOB_GROUP&jobName=计划任务通知任务
     * http://localhost:8888/job/delete?jobGroup=STANDARD_JOB_GROUP&jobName=时间通知任务
     */
    @RequestMapping("/delete")
    public boolean deleteJob(@RequestParam("jobGroup") String jobGroup, @RequestParam("jobName") String jobName)
            throws SchedulerException {
        return jobHandler.deleteJob(jobGroup, jobName);
    }

}
