package com.xiongliang.problemInvestigation.serializable;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author 六月
 * @Date 2023/7/24 17:39
 * @Version 1.0
 */
@Data
public class Person implements Serializable {

    private String name;
    private String age;

}
