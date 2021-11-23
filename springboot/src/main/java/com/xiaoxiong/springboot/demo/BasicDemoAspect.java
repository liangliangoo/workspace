package com.xiaoxiong.springboot.demo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/23 9:49
 */
@Aspect
public class BasicDemoAspect {

    // 注意在这个方法所在类上，添加注解 @Aspect
    @Around("execution(public * com.xiaoxiong.springboot.demo.BasicDemo.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        return joinPoint.proceed();
    }

}
