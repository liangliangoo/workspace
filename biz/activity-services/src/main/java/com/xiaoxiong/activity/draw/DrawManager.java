package com.xiaoxiong.activity.draw;

import com.xiaoxiong.base.draw.DrawLogParam;
import com.xiaoxiong.base.draw.DrawLogVO;
import com.xiaoxiong.base.draw.DrawParam;
import com.xiaoxiong.base.draw.DrawReturn;

/**
 * @Author 六月
 * @Date 2023/1/25 18:41
 * @Version 1.0
 */
public interface DrawManager {

    /**
     * 抽奖
     *
     */
    DrawReturn draw(DrawParam drawParam);

    /**
     * 抽奖记录
     *
     * @param drawLogParam
     * @return
     */
    default DrawLogVO drawLog(DrawLogParam drawLogParam) {
        return null;
    }

    /**
     * 抽奖记录（分页）
     *
     * @param drawLogParam
     * @return
     */
    default DrawLogVO drawLogByPage(DrawLogParam drawLogParam) {
        return null;
    }


}
