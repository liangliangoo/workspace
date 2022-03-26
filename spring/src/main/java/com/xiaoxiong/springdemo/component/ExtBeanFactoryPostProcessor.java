package com.xiaoxiong.springdemo.component;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/3/26  10:28
 *
 * 扩展方法--后置增强器（可修改bean的定义信息）
 */
public class ExtBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // 得到完整BeanDefinition之后就可以进行创建对象了
        // BeanDefinition userDao = beanFactory.getBeanDefinition("userDao");
        System.out.println("扩展方法--可进行修改beanDefinition的定义信息");
    }
}
