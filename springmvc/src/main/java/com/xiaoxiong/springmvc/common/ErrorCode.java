package com.xiaoxiong.springmvc.common;

/**
 * @Author 六月
 * @Date 2022/8/13 10:28
 * @Version 1.0
 */
public enum ErrorCode {


    SUCCESS("000000", "success"),
    ACCESS_TOKEN_EXPIRED("100000", "token过期"),
    NEED_LOGIN("100001", "请登录"),
    SYSTEM_EXCEPTION("000001", "系统异常"),
    SERVER_EXCEPTION("000002", "服务器一样"),
    INVALID_PARAM("000003", "参数非法"),
    REPEAT_SUBMIT("000004", "重复请求"),
    ;


    private String code;
    private String description;

    private ErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static ErrorCode findByCode(String code) {
        ErrorCode[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            ErrorCode type = var1[var3];
            if (type.getCode().equals(code)) {
                return type;
            }
        }

        return null;
    }
}
