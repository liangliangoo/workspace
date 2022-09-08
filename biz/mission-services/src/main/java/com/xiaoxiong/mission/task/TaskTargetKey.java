package com.xiaoxiong.mission.task;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xiongliang
 * @version 1.0
 * @description 任务的key 也是唯一标识
 * @since 2022/9/5  22:39
 * windows idea 大小写转换快捷键 ctl + shift + u
 */
@AllArgsConstructor
@Getter
public enum TaskTargetKey {

    DAILY_SIGN_IN("DAILY_SIGN_IN", "用户每日登录任务"),
    ;

   private String code;
   private String description;

    public static TaskTargetKey getTaskTargetKeyByCode(String code) {
        for (TaskTargetKey item : values()) {
            if (code.equalsIgnoreCase(item.getCode())) {
                return item;
            }
        }
        return null;
    }

}
