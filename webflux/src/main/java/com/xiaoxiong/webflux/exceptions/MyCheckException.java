package com.xiaoxiong.webflux.exceptions;

import lombok.Data;

/**
 * @author xiongliang
 * @version 1.0
 * @description
 * @since 2022/5/11  15:01
 */
@Data
public class MyCheckException extends RuntimeException {

    private String fieldName;
    private String fieldValue;

    public MyCheckException(String fieldName, String fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public MyCheckException(String message) {
        super(message);
    }

    public MyCheckException(Throwable cause) {
        super(cause);

    }


}
