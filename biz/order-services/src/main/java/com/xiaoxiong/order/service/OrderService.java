package com.xiaoxiong.order.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxiong.order.entity.OrderDO;
import com.xiaoxiong.order.mapper.OrderMapper;
import org.springframework.stereotype.Service;

/**
 * @Author 六月
 * @Date 2022/8/16 22:57
 * @Version 1.0
 */
@Service
public class OrderService extends ServiceImpl<OrderMapper, OrderDO> {
}
