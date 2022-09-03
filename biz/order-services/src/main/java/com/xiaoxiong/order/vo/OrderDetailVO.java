package com.xiaoxiong.order.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author 六月
 * @Date 2022/8/16 23:01
 * @Version 1.0
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailVO implements Serializable {

    private Long productId;
    private Long productPrice;
    private Integer num;
    private String productSnapshot;
}
