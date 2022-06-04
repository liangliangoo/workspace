package com.xiaoxiong.springdemo.di.case1;
/**
 * @author xiongliang
 * @version 1.0
 * @description 构造器注入不能解决循环依赖问题
 * @since 2022/6/4  23:03
 *
 * 导致循环依赖的原因：
 * field属性注入循环依赖（prototype）多实例，为什么它相比单实例的不行，因为 ioc容器不缓存 多实例的，也就是说 A创建完成，setB，B ioc容器中没有，
 * 创建B完成，setA,可以A虽然创建完成，但是ioc缓存中没有，那么ioc,容器又得创建A，这时候，就陷入了循环依赖，就是说A，B虽然通过无参构造创建成功了，
 * 但是ioc容器中不缓存他们
 *
 */