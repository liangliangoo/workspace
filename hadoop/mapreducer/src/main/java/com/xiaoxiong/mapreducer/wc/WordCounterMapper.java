package com.xiaoxiong.mapreducer.wc;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/5  21:31
 */
public class WordCounterMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

    private Text outKey = new Text();
    private LongWritable outValue = new LongWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, LongWritable>.Context context) throws IOException, InterruptedException {
        String lineText = value.toString();
        // 直接catch不规范
        /* Arrays.stream(lineText.split(" ")).forEach(s -> {
            outKey.set(s);
            try {
                context.write(outKey, outValue);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });*/

        for (String str : lineText.split(" ")) {
            outKey.set(str);
            context.write(outKey, outValue);
        }

    }
}
