package com.xiaoxiong.common.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;


/**
 * @Author 六月
 * @Date 2022/8/14 13:45
 * @Version 1.0
 */
@Data
@Builder
public class UserVo implements Serializable {

    private String name;
    private Integer age;
    private String phone;
    private String address;

}
