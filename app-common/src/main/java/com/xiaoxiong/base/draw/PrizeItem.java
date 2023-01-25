package com.xiaoxiong.base.draw;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 奖品列表项
 * @Author 六月
 * @Date 2023/1/25 18:51
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrizeItem {

    /** 奖品 key */
    private String prizeKey;
    /** 奖品名称 */
    private String prizeName;
    /** 奖品 icon */
    private String prizeIcon;
    /** 奖品类型 */
    private String prizeType;
    /** 奖品类型描述 */
    private String prizeTypeDesc;
    /** 奖品价值金币 */
    private Integer valueGold;
    /** 奖品数量（礼物类型展示）*/
    private Integer prizeNum;
    /** 奖品有效期（非礼物类型展示）*/
    private Integer effectiveDays;
    /** 奖品有效期字符串 */
    private String effectiveDaysStr;
    /** 抽中次数 */
    private Integer targetTimes;
    /** 中奖时间（日志记录） */
    private String prizeTime;
    /** 奖品标签 **/
    private List<TagVO> tags;
    /** 奖励数值描述 */
    private Integer prizeCountValue;
    /** 奖励数值单位 */
    private String prizeCountUnit;
}
