package com.xiaoxiong.mission.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 任务类型 枚举类
 * @Author 六月
 * @Date 2023/1/29 19:29
 * @Version 1.0
 */
@AllArgsConstructor
@Getter
public enum MissionTypeEnum {

    daily("daily", "每日任务"),
    once("once", "一次行任务"),
    dailyAndMore("dailyMore", "每日任务，并且可以完成多次"),
    onceAndMore("onceAndMore", "一次性任务并且可以完成多次"),
    ;

    private String name;
    private String description;

}
