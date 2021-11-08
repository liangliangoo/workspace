package com.xiaoxiong.springdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/8  15:40
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer id;
    private Integer age;
    private String name;

}
