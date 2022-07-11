package com.demo.mybatis.entity;

//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.annotation.TableId;
//import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/8/14 10:44
 */
@Data
@Accessors(chain = true)
//@TableName(value = "user")
public class User {

    //@TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer age;

    private String email;

}
