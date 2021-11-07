package com.xiaoxiong.mapreducer.writeable;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/7  20:04
 */
public class FlowReducer extends Reducer<Text,FlowBean,Text,FlowBean> {

    private FlowBean outValue = new FlowBean();

    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Reducer<Text, FlowBean, Text, FlowBean>.Context context) throws IOException, InterruptedException {
        AtomicLong totalUp = new AtomicLong();
        AtomicLong totalDown = new AtomicLong();
        values.iterator().forEachRemaining(flowBean -> {
            totalUp.addAndGet(flowBean.getUpFlow());
            totalDown.addAndGet(flowBean.getDownFlow());
        });
        outValue.setUpFlow(totalUp.get());
        outValue.setDownFlow(totalDown.get());
        outValue.setSumFlow();
        context.write(key, outValue);
    }
}
