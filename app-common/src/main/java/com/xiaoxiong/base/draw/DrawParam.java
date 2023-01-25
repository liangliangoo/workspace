package com.xiaoxiong.base.draw;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.xiaoxiong.base.BaseParam;
import com.xiaoxiong.base.utils.NumberUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @Author 六月
 * @Date 2023/1/25 18:43
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DrawParam {

    /** UnionId */
    private String unionId;
    /** AppID */
    private Long appId;
    /** 抽奖用户 */
    private Long uid;
    /** 活动 Key */
    private String activityCode;
    /** 业务 Key */
    private String type;
    /** 奖池（用于奖池切换验证） key */
    private String poolCode;
    /** 抽奖次数 */
    private Integer times;
    /** 扩展信息 */
    private String extValue;

    /** 全服高爆 */
    private Boolean allOpen = false;
    /** 流程控制参数 - 不下发礼物 */
    private Boolean noSendPrize;


    public String getFrom() {
        String from = getFeatureMap().get("from");
        return from;
    }

    public Long getRelationId() {
        String relationId = getFeatureMap().get("relationId");
        if (StringUtils.isEmpty(relationId) || !NumberUtil.isNumeric(relationId)) {
            return null;
        }
        return Long.valueOf(relationId);
    }

    public Map<String, String> getFeatureMap() {
        if (StringUtils.isEmpty(extValue) || !extValue.startsWith("{")) {
            return Maps.newHashMap();
        }
        Map<String, String> map = JSON.parseObject(extValue, Map.class);
        return map;
    }

    public BaseParam getBaseParam() {
        return BaseParam.builder()
                .appId(getAppId())
                .unionId(getUnionId())
                .uid(getUid())
                .build();
    }
}
