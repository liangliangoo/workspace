package com.xiaoliang.liteflow.config;

import com.xiaoliang.liteflow.param.LiteFlowParam;
import com.yomahub.liteflow.core.FlowExecutor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author 六月
 * @Date 2023/4/6 20:13
 * @Version 1.0
 */
@Component
public class ChainExecute implements CommandLineRunner {

    @Resource
    private FlowExecutor flowExecutor;

    @Override
    public void run(String... args) throws Exception {
        LiteFlowParam liteFlowParam = LiteFlowParam.builder().id(-1l).name("小熊").build();
        flowExecutor.execute2Resp("oneChain", liteFlowParam);
    }
}
