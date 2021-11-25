package com.xiaoxiong.mapreducer.writeable;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/7  19:53
 */
public class FlowMapper extends Mapper<LongWritable, Text,Text,FlowBean> {

    private Text outKey = new Text();
    private FlowBean outValue = new FlowBean();

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, FlowBean>.Context context) throws IOException, InterruptedException {
        // 19 	13975057813	192.168.100.16	www.baidu.com	11058	48243	200
        String line = value.toString();
        String[] split = line.split("\t");

        // 获取我需要的数据
        outKey.set(split[1]);
        outValue.setUpFlow(Long.parseLong(split[split.length-3]));
        outValue.setDownFlow(Long.parseLong(split[split.length-2]));
        outValue.setSumFlow();
        context.write(outKey, outValue);
    }
}
