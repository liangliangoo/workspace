package com.xiaoxiong.base.draw;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 抽奖记录入参
 *
 * @Author 六月
 * @Date 2023/1/25 18:53
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DrawLogParam {
    private Long appId;
    private String unionId;
    private Long uid;
    private String activityCode;
    private String poolCode;
    private Integer pageNo;
    private Integer pageSize;
}
