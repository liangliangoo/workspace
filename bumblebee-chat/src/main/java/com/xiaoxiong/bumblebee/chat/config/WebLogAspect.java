package com.xiaoxiong.bumblebee.chat.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 接口日志切面
 * @Author 六月
 * @Date 2023/6/27 18:30
 * @Version 1.0
 */
@Aspect
@Component
@Slf4j
public class WebLogAspect {

    /**
     * 返回字符串最大长度
     */
    private static final Integer MAX_RESULT_STR_LENGTH = 2000;

    @Pointcut("execution(public * com.xiaoxiong.bumblebee.chat.controller..*.*(..))")
    public void attributionControllerLog() {
    }

    @Around("attributionControllerLog()")
    public Object aroundHandle(ProceedingJoinPoint joinPoint) throws Throwable {
        long ts = System.currentTimeMillis();
        log.info("{} request param:{}", getMethodName(joinPoint), JSON.toJSONString(getParams(joinPoint)));
        Object proceed = joinPoint.proceed();
        log.info("{} request result:{}, time:{}ms", getMethodName(joinPoint), getResultStr(proceed), System.currentTimeMillis() - ts);
        return proceed;
    }

    private String getMethodName(ProceedingJoinPoint joinPoint) {
        return joinPoint.getTarget().getClass().getSimpleName() + "." + joinPoint.getSignature().getName();
    }

    private String getResultStr(Object proceed) {
        String result = "";
        if (proceed != null) {
            result = JSONObject.toJSONString(proceed);
            if (result.length() > MAX_RESULT_STR_LENGTH) {
                result = result.substring(0, MAX_RESULT_STR_LENGTH);
            }
        }
        return result;
    }


    private Map<String, String> getParams(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        String[] parameterNames = methodSignature.getParameterNames();
        Object[] paramValues = joinPoint.getArgs();
        Map<String, String> params = new HashMap<>();
        if (Objects.nonNull(parameterNames) && parameterNames.length > 0) {
            for (int i = 0; i < parameterNames.length; i++) {
                String paramName = "";
                String paramValue = "";
                if (parameterNames[i] != null) {
                    paramName = parameterNames[i];
                }
                if (paramValues[i] != null) {
                    paramValue = paramValues[i].toString();
                } else {
                    paramValue = "null";
                }
                params.put(paramName, paramValue);
            }
        }
        return params;
    }


}
