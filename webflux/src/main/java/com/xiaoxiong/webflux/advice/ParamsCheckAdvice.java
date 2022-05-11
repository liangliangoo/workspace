package com.xiaoxiong.webflux.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

/**
 * @author xiongliang
 * @version 1.0
 * @description
 * @since 2022/5/11  10:03
 */
@ControllerAdvice
public class ParamsCheckAdvice {

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<String> handlerBingException(WebExchangeBindException exception) {
        return new ResponseEntity<>(toStr(exception), HttpStatus.BAD_REQUEST);
    }

    private String toStr(WebExchangeBindException exception) {
        return exception.getFieldErrors().stream()
                .map(e -> e.getField() + ":" + e.getDefaultMessage())
                .reduce("", (str1, str2) -> str1 + "\n" + str2);
    }

}
