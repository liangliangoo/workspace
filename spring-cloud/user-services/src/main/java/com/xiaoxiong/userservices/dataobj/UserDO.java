package com.xiaoxiong.userservices.dataobj;

import com.baomidou.mybatisplus.annotation.*;
import com.xiaoxiong.common.vo.UserVo;
import lombok.Builder;
import lombok.Data;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @Author 六月
 * @Date 2022/8/13 20:27
 * @Version 1.0
 */
@Data
@Builder
@TableName(value = "t_user")
public class UserDO {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String phone;
    private String address;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    public  UserVo convert2Vo() {
        if (Objects.isNull(this)) {
            return null;
        }
        return UserVo.builder()
                .name(this.getName())
                .address(this.getAddress())
                .age(this.getAge())
                .phone(this.phone)
                .build();
    }


}
