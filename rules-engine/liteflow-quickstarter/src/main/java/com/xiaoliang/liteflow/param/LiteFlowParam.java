package com.xiaoliang.liteflow.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author 六月
 * @Date 2023/4/6 20:12
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LiteFlowParam implements Serializable {

    private Long id;
    private String name;

}
