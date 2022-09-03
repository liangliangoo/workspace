package com.xiaoxiong.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaoxiong.order.entity.OrderDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author 六月
 * @Date 2022/8/16 22:57
 * @Version 1.0
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderDO> {
}
