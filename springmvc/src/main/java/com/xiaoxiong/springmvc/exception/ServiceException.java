package com.xiaoxiong.springmvc.exception;

import com.xiaoxiong.springmvc.common.ErrorCode;

/**
 * @Author 六月
 * @Date 2022/8/13 10:40
 * @Version 1.0
 */
public class ServiceException extends RuntimeException{

    private static final long serialVersionUID = -1;
    private ErrorCode errorCode;
    private String errorMsg;

    public ServiceException(ErrorCode e) {
        super(e.getDescription());
        this.errorCode = e;
        this.errorMsg = e.getDescription();
    }

    public ServiceException(ErrorCode e, String errorMsg) {
        super(e.getDescription());
        this.errorCode = e;
        this.errorMsg = errorMsg;
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
