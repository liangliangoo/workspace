package com.xiaoxiong.dtsp.config;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.api.config.listener.Listener;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.concurrent.*;

/**
 * @author xiongliang
 * @version 1.0
 * @description 基于nacos 动态线程池配置
 * @since 2023/2/4  15:08
 */
@Slf4j
@RefreshScope
@Configuration
public class DynamicThreadPool implements InitializingBean {

    private String coreSize;
    private String maxSize;

    public static ThreadPoolExecutor threadPoolExecutor;

    @Resource
    private NacosConfigManager nacosConfigManager;
    @Resource
    private NacosConfigProperties nacosConfigProperties;

    @Override
    public void afterPropertiesSet() throws Exception {
        threadPoolExecutor = new ThreadPoolExecutor(Integer.valueOf(coreSize), Integer.valueOf(maxSize), 5L, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(1000), new ThreadFactory() {
            @Override
            public Thread newThread(@NotNull Runnable r) {
                return null;
            }
        }, new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

            }
        }
        );

        nacosConfigManager.getConfigService().addListener("dtp-services.yaml", nacosConfigProperties.getGroup(), new Listener() {
            @Override
            public Executor getExecutor() {
                return null;
            }

            @Override
            public void receiveConfigInfo(String s) {
                log.info("receiveConfigInfo {}",s);
                changeThreadPoolConfig(null, null);
            }
        });
    }

    private void changeThreadPoolConfig(Integer core, Integer max) {
        // TODO: 2023/2/4 修改线程池参数
    }

}
