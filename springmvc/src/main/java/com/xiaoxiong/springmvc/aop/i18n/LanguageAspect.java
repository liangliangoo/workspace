package com.xiaoxiong.springmvc.aop.i18n;

import com.alibaba.fastjson.JSONObject;
import com.xiaoxiong.springmvc.common.Response;
import com.xiaoxiong.springmvc.component.i18n.I18nUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * @Author liuyue
 * @Date 2022/7/7 22:51
 * @Version 1.0
 */
@Slf4j
@Aspect
@Component
@AllArgsConstructor
@ConditionalOnProperty(prefix = "lang", name = "open", havingValue = "true")
public class LanguageAspect {

    @Resource
    I18nUtils i18nUtils;

    @Pointcut("execution(* com.xiaoxiong.springmvc.controller.i18n.*.*(..)))")
    public void annotationLangCut() {
    }

    /**
     * 拦截controller层返回的结果，修改msg字段
     *
     * @param point
     * @param obj
     */
    @AfterReturning(pointcut = "annotationLangCut()", returning = "obj")
    public void around(Object obj) {
        log.info("LanguageAspect 切面：{}", JSONObject.toJSONString(obj));
        Object resultObject = obj;
        try {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            //从获取RequestAttributes中获取HttpServletRequest的信息
            HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
            String langFlag = request.getHeader("lang");
            if (null != langFlag) {
                Response response = (Response) obj;
                String msg = response.getMsg();
                if (!StringUtils.isEmpty(msg)) {
                    if ("CN".equals(langFlag)) {
                        Locale locale = Locale.CHINA;
                        msg = i18nUtils.getKey(msg, locale);
                    } else if ("EN".equals(langFlag)) {
                        Locale locale = Locale.US;
                        msg = i18nUtils.getKey(msg, locale);
                    } else {
                        msg = i18nUtils.getKey(msg);
                    }
                }
                response.setMsg(msg);
            }
        } catch (Exception e) {
            obj = resultObject;
            log.error("LanguageAspect.around.....");
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
