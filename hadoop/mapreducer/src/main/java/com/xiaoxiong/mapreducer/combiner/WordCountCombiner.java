package com.xiaoxiong.mapreducer.combiner;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 *
 * combiner 能够提升reducer流程效率，在reducer之前能将相同的key合并处理
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/10  15:33
 */
public class WordCountCombiner extends Reducer<Text, IntWritable,Text, IntWritable> {

    private IntWritable outV = new IntWritable();
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

        int sum =0;
        for (IntWritable value : values) {
            sum += value.get();
        }

        outV.set(sum);

        context.write(key,outV);
    }
}
