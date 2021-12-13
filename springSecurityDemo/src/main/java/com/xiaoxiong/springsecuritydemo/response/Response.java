package com.xiaoxiong.springsecuritydemo.response;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/12/7  20:12
 */
@ApiModel
@Data
public class Response<T> {

    public final static Integer SUCCESS = 0;

    public final static Integer FAIL = 1;

    @ApiModelProperty(value = "返回码", example = "0")
    private Integer code;
    @ApiModelProperty(value = "返回码描述", example = "ok")
    private String msg;
    @ApiModelProperty(value = "返回结果")
    private T data;

    public Response(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Response(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 状态码 + 成功提示信息
     */
    public static <T> Response<T> success(String msg) {
        return new Response<>(SUCCESS, msg);
    }

    /**
     * 状态码 + 成功提示信息 + 数据
     */
    public static <T> Response<T> success(String msg, T data) {
        return new Response<>(SUCCESS, msg, data);
    }

    /**
     * 状态码 + 成功提示信息 + 数据
     */
    public static <T> Response<T> success(T data) {
        return new Response<>(SUCCESS, "server success", data);
    }

    /**
     * 状态码 + 错误信息
     */
    public static <T> Response<T> fail(String msg) {
        return new Response<>(FAIL, msg);
    }

}
