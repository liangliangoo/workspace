package com.xiaoxiong.order.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author 六月
 * @Date 2022/8/16 22:57
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BaseDO implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "create_time",jdbcType = JdbcType.DATE, fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time",jdbcType = JdbcType.DATE, fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
