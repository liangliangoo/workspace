package com.xiaoxiong.common.aop;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


/**
 * @Author 六月
 * @Date 2022/8/14 19:38
 * @Version 1.0
 */
@Slf4j
@Aspect
@Component
public class DubboAspect {

    /**
     * 拦截dubbo服务所有的方法
     */
    @Pointcut("@within(org.apache.dubbo.config.annotation.DubboService)")
    public void dubboPointcut() {
    }

    @Around("dubboPointcut()")
    public Object aroundDubboPoint(ProceedingJoinPoint point) {
        Signature signature = point.getSignature();
        String methodName = signature.getDeclaringTypeName() + "." + signature.getName();
        Object[] args = point.getArgs();
        log.info("rpc 调用方法：{}，params:{}", methodName, JSONObject.toJSONString(args));
        long start = System.currentTimeMillis();
        Object proceed = null;
        try {
            proceed = point.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        log.info(methodName + "调用耗时：{} ms", System.currentTimeMillis() - start);
        return proceed;
    }

}
