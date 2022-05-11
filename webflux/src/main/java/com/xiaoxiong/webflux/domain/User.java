package com.xiaoxiong.webflux.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

/**
 * @author xiongliang
 * @version 1.0
 * @description
 * @since 2022/5/9  21:50
 */
@Document(collection = "user")
@Data
public class User {

    @Id
    private String id;

    @NotBlank
    private String name;

    @Range(max = 1500, min = 10)
    private Integer age;

}
