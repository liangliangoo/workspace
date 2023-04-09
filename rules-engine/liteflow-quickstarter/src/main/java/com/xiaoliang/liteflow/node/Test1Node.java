package com.xiaoliang.liteflow.node;

import com.alibaba.fastjson2.JSON;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author 六月
 * @Date 2023/4/5 19:41
 * @Version 1.0
 */
@Slf4j
@LiteflowComponent("test1Component")
public class Test1Node extends NodeComponent {
    @Override
    public void process() throws Exception {
        log.info("==============  test1Component  ===========");
        Object oneChain = this.getSlot().getChainReqData("oneChain");
        log.info("request context {}", JSON.toJSONString(oneChain));
        Object requestData = this.getSlot().getRequestData();
        log.info("request requestData {}", JSON.toJSONString(requestData));
    }
}
