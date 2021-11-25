package com.xiaoxiong.shardingjdbc.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/24  16:26
 */
@Data
@TableName("t_user")
public class User implements Serializable {

    private Long id;
    private String name;
    private Integer age;

}
