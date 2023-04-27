package com.xiaoxiong.springboot.param;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author 六月
 * @Date 2023/4/26 10:47
 * @Version 1.0
 */
@Data
public class ParamBind implements Serializable {

    @JsonAlias("oaid_md5")
    public String oaidMd5;

    @JsonProperty(value = "imei_md5", required = true)
    private String imeiMd5;

}
