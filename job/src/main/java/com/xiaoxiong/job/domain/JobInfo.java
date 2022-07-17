package com.xiaoxiong.job.domain;

import lombok.Data;

/**
 * @Author 六月
 * @Date 2022/7/17 15:54
 * @Version 1.0
 */
@Data
public class JobInfo {

    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 任务组
     */
    private String jobGroup;
    /**
     * 触发器名称
     */
    private String triggerName;
    /**
     * 触发器组
     */
    private String triggerGroup;
    /**
     * cron表达式
     */
    private String cron;
    /**
     * 类名
     */
    private String className;
    /**
     * 状态
     */
    private String status;
    /**
     * 下一次执行时间
     */
    private String nextTime;
    /**
     * 上一次执行时间
     */
    private String prevTime;
    /**
     * 配置信息(data)
     */
    private String config;


}
