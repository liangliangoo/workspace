package com.xiaoxiong.springmvc.component.mvc;

import com.xiaoxiong.springmvc.annotations.ApiVersions;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/7/5 21:03
 * api 版本控制器
 */
public class ApiVersionHandlerMapping extends RequestMappingHandlerMapping {

    /**
     * {@inheritDoc}
     * @param beanType
     * @return
     */
    @Override
    protected boolean isHandler(Class<?> beanType) {
        return AnnotatedElementUtils.hasAnnotation(beanType, Controller.class);
    }

    @Override
    protected void registerHandlerMethod(Object handler, Method method, RequestMappingInfo mapping) {
        Class<?> controllerClass = method.getDeclaringClass();
        // 判断类上是否使用到了ApiVersions注解
        ApiVersions apiVersions = AnnotationUtils.findAnnotation(controllerClass, ApiVersions.class);
        // 判断方法上是否使用了ApiVersions注解
        ApiVersions methodApiVersions = AnnotationUtils.findAnnotation(method, ApiVersions.class);
        if (methodApiVersions != null) {
            apiVersions = methodApiVersions;
        }

        String[] urlPatterns = apiVersions == null ? new String[0] : apiVersions.value();

        PatternsRequestCondition apiPattern = new PatternsRequestCondition(urlPatterns);
        PatternsRequestCondition olderPattern = mapping.getPatternsCondition();
        PatternsRequestCondition updatePattern = apiPattern.combine(olderPattern);

        mapping = new RequestMappingInfo(mapping.getName(),
                updatePattern,
                mapping.getMethodsCondition(),
                mapping.getParamsCondition(),
                mapping.getHeadersCondition(),
                mapping.getConsumesCondition(),
                mapping.getProducesCondition(),
                mapping.getCustomCondition());

        super.registerHandlerMethod(handler, method, mapping);
    }

}
