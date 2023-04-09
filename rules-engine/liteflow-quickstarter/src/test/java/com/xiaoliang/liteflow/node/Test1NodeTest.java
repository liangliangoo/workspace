package com.xiaoliang.liteflow.node;

import com.xiaoliang.liteflow.config.ChainExecute;
import com.xiaoliang.liteflow.param.LiteFlowParam;
import com.yomahub.liteflow.core.FlowExecutor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class Test1NodeTest {

    @Resource
    private FlowExecutor flowExecutor;
    @Resource
    private ChainExecute chainExecute;

    @Test
    void test_oneChain() throws Exception {
        flowExecutor.execute("oneChain");
    }

    @Test
    void test_chainContext() {
        LiteFlowParam liteFlowParam = LiteFlowParam.builder().id(-1l).name("小熊").build();
        flowExecutor.execute2Resp("oneChain", liteFlowParam);
    }

}