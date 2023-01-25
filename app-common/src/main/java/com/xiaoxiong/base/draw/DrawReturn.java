package com.xiaoxiong.base.draw;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author 六月
 * @Date 2023/1/25 18:57
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DrawReturn {

    /** 是否有实物奖励 */
    private Boolean hasRealPrize;

    /** 奖品列表 */
    private List<PrizeItem> prizeItemList;

    /** 幸运值 */
    private Integer luckValue;
}
