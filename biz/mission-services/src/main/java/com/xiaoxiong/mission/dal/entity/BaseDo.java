package com.xiaoxiong.mission.dal.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.time.LocalDateTime;

/**
 * @Author 六月
 * @Date 2023/1/29 18:37
 * @Version 1.0
 */
@Data
public class BaseDo {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "create_time", jdbcType = JdbcType.DATE, fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", jdbcType = JdbcType.DATE, fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}
