package com.xiaoxiong.webfluxclient.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author xiongliang
 * @version 1.0
 * @description
 * @since 2022/5/11  19:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {

    private String id;

    private String name;

    private Integer age;

}
