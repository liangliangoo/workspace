package com.xiaoxiong.springmvc.annotations;

import com.xiaoxiong.springmvc.common.ErrorCode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author 六月
 * @Date 2022/8/13 10:34
 * @Version 1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NoRepeatSubmit {

    String eventKey() default "";

    boolean requiredUser() default true;

    int time() default -1;

    String[] paramKeys() default {};

    ErrorCode errorCode() default ErrorCode.REPEAT_SUBMIT;

    String errorMsg() default "";

}
