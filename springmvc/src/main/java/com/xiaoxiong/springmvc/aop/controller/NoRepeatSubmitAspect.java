package com.xiaoxiong.springmvc.aop.controller;

import com.xiaoxiong.springmvc.annotations.NoRepeatSubmit;
import com.xiaoxiong.springmvc.common.ErrorCode;
import com.xiaoxiong.springmvc.exception.ServiceException;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author 六月
 * @Date 2022/8/13 10:26
 * @Version 1.0
 * 接口幂等处理切面
 */
@Aspect
@Component
public class NoRepeatSubmitAspect {

    private static final Logger log = LoggerFactory.getLogger(NoRepeatSubmitAspect.class);
    private static final String NO_REPEAT_SUBMIT_PREFIX = "NRSP_";
    private static final String SEPARATOR = "_";
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public NoRepeatSubmitAspect() {
    }

    @Around("@annotation(noRepeatSubmit)")
    public Object aroundRepeatPoint(ProceedingJoinPoint joinPoint, NoRepeatSubmit noRepeatSubmit) throws Exception {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        int time = noRepeatSubmit.time();
        ErrorCode errorCode = noRepeatSubmit.errorCode();
        if (null == errorCode) {
            errorCode = ErrorCode.REPEAT_SUBMIT;
        }

        String errorMsg = noRepeatSubmit.errorMsg();
        if (StringUtils.isEmpty(errorMsg)) {
            errorMsg = errorCode.getDescription();
        }

        String lockKey = this.getLockKey(joinPoint, signature, noRepeatSubmit);
        log.info("aroundRepeatPoint lockKey {}", lockKey);
        boolean lock = this.lock(lockKey, time);
        if (!lock) {
            throw new ServiceException(errorCode, errorMsg);
        } else {
            Object var10;
            try {
                Object proceed = joinPoint.proceed();
                var10 = proceed;
            } catch (ServiceException var15) {
                throw var15;
            } catch (Throwable var16) {
                throw new Exception(var16);
            } finally {
                this.unlock(lockKey);
            }

            return var10;
        }
    }

    private String getLockKey(ProceedingJoinPoint joinPoint, MethodSignature signature, NoRepeatSubmit noRepeatSubmit) {
        String eventKey = noRepeatSubmit.eventKey();
        boolean requiredUser = noRepeatSubmit.requiredUser();
        String[] paramKeys = noRepeatSubmit.paramKeys();
        StringBuilder lockKey = new StringBuilder("NRSP_");
        lockKey.append(StringUtils.isEmpty(eventKey) ? signature.getDeclaringType().getSimpleName() + "_" + signature.getName() : eventKey);
        if (requiredUser) {
            // TODO: 2022/8/13 通过上下文获取一个幂等唯一标识 晓宇公司的处理方式： MDC存储当前登录用户信息做幂等
            Long curAppIdByMdc = 2L;
            Long curUserIdByMdc = 1L;
            if (null == curAppIdByMdc || null == curUserIdByMdc) {
                throw new ServiceException(ErrorCode.INVALID_PARAM, "登陆信息不存在");
            }

            lockKey.append("_" + curAppIdByMdc + "_" + curUserIdByMdc);
        }

        if (null != paramKeys && paramKeys.length > 0) {
            String[] parameterNames = signature.getParameterNames();
            Object[] args = joinPoint.getArgs();
            String[] var10 = paramKeys;
            int var11 = paramKeys.length;

            for (int var12 = 0; var12 < var11; ++var12) {
                String paramKey = var10[var12];
                int i = ArrayUtils.indexOf(parameterNames, paramKey);
                if (i >= 0) {
                    Object arg = args[i];
                    if (null == arg) {
                        arg = "null";
                    }

                    lockKey.append("_" + arg.toString());
                }
            }
        }

        return lockKey.toString();
    }

    private boolean lock(String key, int seconds) {
        try {
            return seconds > 0 ? this.redisTemplate.opsForValue().setIfAbsent(key, 1, (long) seconds, TimeUnit.SECONDS) : this.redisTemplate.opsForValue().setIfAbsent(key, 1);
        } catch (Exception var4) {
            log.error("failed key : {}", key, var4);
            return true;
        }
    }

    private boolean unlock(String key) {
        try {
            return this.redisTemplate.delete(key);
        } catch (Exception var3) {
            log.error("failed key : {}", key, var3);
            return true;
        }
    }

}
