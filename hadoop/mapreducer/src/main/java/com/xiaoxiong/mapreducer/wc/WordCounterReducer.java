package com.xiaoxiong.mapreducer.wc;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/5  21:31
 */
public class WordCounterReducer extends Reducer<Text, LongWritable, Text, LongWritable> {

    private LongWritable outValue = new LongWritable();

    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Reducer<Text, LongWritable, Text, LongWritable>.Context context) throws IOException, InterruptedException {
        long sum = 0;
        while (values.iterator().hasNext()) {
            sum += values.iterator().next().get();
        }
        outValue.set(sum);
        context.write(key, outValue);
    }
}
