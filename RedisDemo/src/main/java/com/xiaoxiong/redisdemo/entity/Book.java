package com.xiaoxiong.redisdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/1/20 15:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {

    private static final long serialVersionUID = 2629983876059197650L;

    private String id;
    private String name;   // 书名
    private Integer price; // 价格
    private Date update;   //

}
