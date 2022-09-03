package com.xiaoxiong.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @Author 六月
 * @Date 2022/8/16 23:01
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@TableName(value = "trade_order_2c")
public class OrderDO extends BaseDO {

    private Long appId;
    private String unionId;
    private Long userId;
    /** 订单类型 */
    private Integer orderType;
    /** 支付类型 */
    private Integer payType;
    /** 订单价格 */
    private Long orderPrice;
    /** 订单支付价格 */
    private Long orderPayPrice;
    /** 优惠券ID */
    private Long couponId;
    /** 优惠金额 */
    private Long couponPrice;
    /** 订单状态 */
    private String status;
    /** 商品ID */
    private Long productId;
    /** 商品快照 */
    private String productSnapshot;

}
