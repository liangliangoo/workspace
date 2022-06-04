package com.xiaoxiong.springdemo.di.case2;
/**
 * @author xiongliang
 * @version 1.0
 * @description setter方式无法解决多实例bean的循环依赖问题
 * @since 2022/6/4  22:54
 *
 * 导致循环依赖的原因：
 *          Spring容器会将每一个正在创建的Bean 标识符放在一个“当前创建Bean池”中，Bean标识符在创建过程中将一直保持
 * 			此时若bean在创建的过程中，依赖的一个bean，发现这个bean在“池中”将抛出BeanCurrentlyInCreationException异常
 * 			表示循环依赖；而对于创建完毕的Bean将从“当前创建Bean池”中清除掉。
 *
 * 			A创建的过程中依赖B，beanFactory去ioc容器的缓存中get B,发现没有，那么就创建B
 * 			而B在创建的过程中，又依赖C，beanFactory去ioc容器的缓存中get C,发现没有，那么就创建C
 * 			而C在创建的过程中，又依赖A，beanFactory去 ioc容器的缓存中get A,
 * 			发现A 此时在 “池”中，那么抛出异常
 *
 */