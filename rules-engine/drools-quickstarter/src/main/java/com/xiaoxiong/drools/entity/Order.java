package com.xiaoxiong.drools.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @Author 六月
 * @Date 2022/9/20 21:46
 * @Version 1.0
 */
@Data
@ToString
public class Order {

    private Double originalPrice; //原始价格
    private Double realPrice; // 订单真实价格

}
