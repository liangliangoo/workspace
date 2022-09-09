package com.xiaoxiong.activity.checker;

import lombok.Setter;

/**
 * @author xiongliang
 * @version 1.0
 * @description checker类型
 * @since 2022/9/8  21:57
 */
@Setter
public enum CheckerEnum {

    COMMON_CHECKER("COMMON_CHECKER", "通用校验器"),
    BIZ_CHECKER("BIZ_CHECKER", "业务校验器"),
    ;

    private String type;
    private String description;

    private CheckerEnum(String type, String description) {
        this.type = type;
        this.description = description;
    }
}
