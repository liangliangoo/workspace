package com.xiaoxiong.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaoxiong.order.vo.OrderDetailVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @Author 六月
 * @Date 2022/8/16 23:00
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@TableName(value = "trade_order_detail")
public class OrderDetailDO extends BaseDO {

    private Long appId;
    private String unionId;
    private Long userId;
    private Integer orderType;
    private Long orderId;
    private Long productId;
    private Long productPrice;
    private Integer num;
    private String orderStatus;
    private String productSnapshot;

    public OrderDetailVO convert2VO() {
        return OrderDetailVO.builder()
                .productId(getProductId())
                .productPrice(getProductPrice())
                .num(getNum())
                .productSnapshot(getProductSnapshot()).build();
    }

}
