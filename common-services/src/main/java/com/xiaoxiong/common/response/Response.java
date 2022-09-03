package com.xiaoxiong.common.response;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/1/18 10:11
 * 接口的返回数据类型
 */
public class Response<T> {

    public final static Integer SUCCESS = 0;

    public final static Integer FAIL = 1;

    private Integer code;
    private String msg;
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

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
