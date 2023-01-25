package com.xiaoxiong.activity.draw.param;

import com.xiaoxiong.base.draw.DrawParam;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @Author 六月
 * @Date 2023/1/25 19:00
 * @Version 1.0
 */
@Data
@Builder
public class DrawContext {

    /** 抽奖参数 */
    private DrawParam drawParam;
    /** 抽奖配置 */
    private DrawConfig drawConfig;
    /** 奖池信息 */
    private DrawPoolDO drawPoolDO;
    /** 奖池奖品信息 */
    private List<DrawPoolItemDO> drawPoolItemDOS;
    /** 抽奖结果 */
    private List<DrawPoolItemDTO> prizeItemList;


    /** 业务相关参数 */
    private Long feeCoin = 0L;
    private Long freeCoin = 0L;

    /** 过期参数 */
    @Deprecated
    private List<DrawPoolItemDO> prizeDOList;
}
