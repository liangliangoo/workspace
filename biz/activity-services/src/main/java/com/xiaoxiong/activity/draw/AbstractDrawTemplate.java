package com.xiaoxiong.activity.draw;

import com.xiaoxiong.activity.draw.param.DrawContext;
import com.xiaoxiong.activity.draw.param.DrawPoolItemDTO;
import com.xiaoxiong.base.draw.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 抽奖模版
 * @Author 六月
 * @Date 2023/1/25 18:58
 * @Version 1.0
 */
public abstract class AbstractDrawTemplate implements DrawManager {


    @Override
    public DrawReturn draw(DrawParam param) {
        DrawContext context = DrawContext.builder().drawParam(param).build();

        // 填充奖池、奖品信息
        filledDrawContext(context);
        // 资源检查
        resourceCheck(context);
        // 扣除资源
        deductResource(context);
        // 抽奖
        draw(context);
        // 下发奖励
        sendPrize(param, context.getPrizeItemList());
        // 后置处理
        callback(context);
        // 封装出参
        return getResult(context);
    }

    /**
     * 封装出参
     *
     * @param context
     * @return
     */
    public DrawReturn getResult(DrawContext context) {
        List<PrizeItem> prizeItemList = context.getPrizeItemList().stream().map((item) -> {
            // todo @六月
            return new PrizeItem();
        }).collect(Collectors.toList());
        return DrawReturn.builder()
                .hasRealPrize(Boolean.FALSE)
                .prizeItemList(prizeItemList).build();
    }

    /**
     * 资源检查
     *
     * @param context
     * @return
     */
    protected abstract void resourceCheck(DrawContext context);

    /**
     * 抽奖实现
     *
     * @param context
     * @return
     */
    protected abstract void draw(DrawContext context);

    /**
     * 下发奖励
     *
     * @param drawParam
     * @param prizeList
     */
    protected void sendPrize(DrawParam drawParam, List<DrawPoolItemDTO> prizeList) {

    }

    /**
     * 资源扣除
     *
     * @param context
     * @return
     */
    protected abstract void deductResource(DrawContext context);

    /**
     * 后置处理
     *
     * @param context
     * @return
     */
    protected abstract void doCallback(DrawContext context);


    /**
     * 后置处理
     *
     * @param context
     * @return
     */
    private void callback(DrawContext context) {

    }

    /**
     * 抽奖记录查询
     * <p>
     * 该方法用于统一索引，谨慎调整查询方式
     *
     * @param param
     * @return
     */
    final public DrawLogVO drawLog(DrawLogParam param) {
        return new DrawLogVO();
    }

    /**
     * 抽奖记录查询（分页）
     * <p>
     * 该方法用于统一索引，谨慎调整查询方式
     *
     * @param param
     * @return
     */
    final public DrawLogVO drawLogByPage(DrawLogParam param) {
        return new DrawLogVO();
    }

    /**
     * 填充奖池上下文信息
     * @param context
     */
    protected void filledDrawContext(DrawContext context) {

    }

}
