package com.demo.mybatis.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/8/14 10:44
 */
@Data
@Accessors(chain = true)
public class User {

    private Integer id;

    private String name;

    private Integer age;

    private String email;

}
