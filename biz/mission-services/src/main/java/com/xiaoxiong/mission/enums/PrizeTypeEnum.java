package com.xiaoxiong.mission.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author 六月
 * @Date 2023/1/29 19:35
 * @Version 1.0
 */
@AllArgsConstructor
@Getter
public enum PrizeTypeEnum {

    coin("coin", "金币"),
    point("point", "礼物"),
    gift("gift", "礼物"),
    dressUp("dressUp", "装扮信息"),
    ;

    private String name;
    private String description;

}
