package com.xiaoxiong.userservices.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author 六月
 * @Date 2022/8/14 13:50
 * @Version 1.0
 */
@Slf4j
public class BeanUtil {

    /**
     * 类拷贝，忽略为空的属性
     *
     * @param sourceObj 目标对象
     * @param destObj   目标对象（拷贝后的结果对象）
     * @return 拷贝后的结果对象
     */
    public static Object copyBeanIgnoreNullProperties(Object sourceObj, Object destObj) {
        if (sourceObj == null || destObj == null) {
            log.error("[BeanUtil-copyBeanIgnoreNullProperties] copyBean参数缺失！原参数为空 {}，目标参数为空 {}", sourceObj == null, destObj == null);
            return destObj;
        }
        try {
            BeanUtils.copyProperties(sourceObj, destObj, getNoValuePropertyNames(sourceObj));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return destObj;
    }

    /**
     * 获取需要忽略的属性数组
     *
     * @param source 原对象
     * @return 需要忽略的属性数组
     */
    private static String[] getNoValuePropertyNames(Object source) {
        Assert.notNull(source, "[BeanUtil-copyBeanIgnoreNullProperties] 传递的参数对象不能为空");
        final BeanWrapper beanWrapper = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = beanWrapper.getPropertyDescriptors();
        Set<String> noValuePropertySet = new HashSet<>();
        Arrays.stream(pds).forEach(pd -> {
            Object propertyValue = beanWrapper.getPropertyValue(pd.getName());
            if (StringUtils.isEmpty(propertyValue)) {
                noValuePropertySet.add(pd.getName());
            } else {
                if (Iterable.class.isAssignableFrom(propertyValue.getClass())) {
                    Iterable iterable = (Iterable) propertyValue;
                    Iterator iterator = iterable.iterator();
                    if (!iterator.hasNext()) {
                        noValuePropertySet.add(pd.getName());
                    }
                }
                if (Map.class.isAssignableFrom(propertyValue.getClass())) {
                    Map map = (Map) propertyValue;
                    if (map.isEmpty()) {
                        noValuePropertySet.add(pd.getName());
                    }
                }
            }
        });
        String[] result = new String[noValuePropertySet.size()];
        return noValuePropertySet.toArray(result);
    }

    /**
     * 给对象赋值，忽略空值
     *
     * @param object     目标 bean
     * @param fieldName  字段名字 比如：name
     * @param value      需要赋给目标值
     * @param dateFormat 如果是 date 类型的日期，则该字段必传
     *                   传入的是String字符串'2018-12-09'   则需要传入相应Fromat格式 yyyy-MM-dd
     */
    public static Object setFieldValueIgnoreNull(Object object, String fieldName, Object value, String dateFormat) {
        try {
            if (StringUtils.isEmpty(value)) {
                return object;
            }
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            String fieldType = field.getGenericType().toString();
            if (!StringUtils.isEmpty(fieldType)) {
                String valueStr = value.toString();
                switch (fieldType) {
                    case "class java.lang.String":
                        field.set(object, valueStr);
                        break;
                    case "class java.lang.Integer":
                        field.set(object, Integer.valueOf(valueStr));
                        break;
                    case "class java.lang.Long":
                        field.set(object, Long.valueOf(valueStr));
                        break;
                    case "class java.lang.Double":
                        field.set(object, Double.valueOf(valueStr));
                        break;
                    case "class java.math.BigDecimal":
                        field.set(object, BigDecimal.valueOf(Double.parseDouble(valueStr)));
                        break;
                    case "class java.util.Date":
                        Date date;
                        if (!StringUtils.isEmpty(dateFormat)) {
                            date = new SimpleDateFormat(dateFormat).parse(valueStr);
                            field.set(object, date);
                        }
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            log.error("[BeanUtil-setFieldValueIgnoreNull] error", e);
        }
        return object;
    }

    /**
     * 给对象赋值，不忽略空值
     *
     * @param object    目标 bean
     * @param fieldName 字段名字 比如：name
     * @param value     需要赋给目标值
     */
    public static Object setFieldValueWithoutDate(Object object, String fieldName, Object value) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            String fieldType = field.getGenericType().toString();
            switch (fieldType) {
                case "class java.lang.String":
                    field.set(object, null == value ? "" : value.toString());
                    break;
                case "class java.lang.Integer":
                    field.set(object, null == value ? 0 : Integer.parseInt(value.toString()));
                    break;
                case "class java.lang.Long":
                    field.set(object, null == value ? 0L : Long.parseLong(value.toString()));
                    break;
                case "class java.lang.Double":
                    field.set(object, null == value ? 0d : Double.parseDouble(value.toString()));
                    break;
                case "class java.math.BigDecimal":
                    field.set(object, null == value ? BigDecimal.ZERO : BigDecimal.valueOf(Double.parseDouble(value.toString())));
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            log.error("[BeanUtil-setFieldValueWithoutDate] error", e);
        }
        return object;
    }

}
