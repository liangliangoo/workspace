package com.xiaoxiong.mission.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

/**
 * mybatis 配置
 * @Author 六月
 * @Date 2023/1/29 18:37
 * @Version 1.0
 */
@Configurable
public class MybatisConfig {

    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题(该属性会在旧插件移除后一同移除)
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }

    @Bean
    public MetaObjectHandler MybatisDateConfig() {
        return new MetaObjectHandler() {

            /**
             * 插入元对象字段填充（用于插入时对公共字段的填充）
             *
             * @param metaObject 元对象
             */
            @Override
            public void insertFill(MetaObject metaObject) {

                this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
                this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
            }

            /**
             * 更新元对象字段填充（用于更新时对公共字段的填充）
             *
             * @param metaObject 元对象
             */
            @Override
            public void updateFill(MetaObject metaObject) {
                this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject.metaObjectForProperty("updateTime"));
            }
        };
    }

}
