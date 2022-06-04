package com.xiaoxiong.redisdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/1/20 15:58
 */
@Data
@AllArgsConstructor
public class BookQuery {

    private String id;
    private String name; // 书名

}
