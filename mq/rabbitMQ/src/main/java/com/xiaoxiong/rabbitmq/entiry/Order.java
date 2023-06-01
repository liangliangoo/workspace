package com.xiaoxiong.rabbitmq.entiry;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/2/17 14:13
 */
@Data
@Accessors(chain = true)
public class Order implements Serializable {

    private Integer Id;

    private Date time;


}
