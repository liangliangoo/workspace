package com.xiaoxiong.mapreducer.wc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/5  21:31
 */
public class WordCounterDriver {

    public static void main(String[] args) {
        // 1 获取job
        Configuration configuration = new Configuration();
        try (Job job = Job.getInstance(configuration)) {
            // 2 设置jar包路径
            job.setJarByClass(WordCounterDriver.class);

            // 3 关联mapper和reducer
            job.setMapperClass(WordCounterMapper.class);
            job.setReducerClass(WordCounterReducer.class);

            // 4 设置map输出kv类型
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(LongWritable.class);

            // 5 设置最终输出类型
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(LongWritable.class);

            // 6 设置输入输出路径
            FileInputFormat.setInputPaths(job,new Path("F:\\JavaProject\\appdemo\\hadoop\\mapreducer\\input"));
            FileOutputFormat.setOutputPath(job,new Path("F:\\JavaProject\\appdemo\\hadoop\\mapreducer\\output"));

            // 7 启动job
            System.exit(job.waitForCompletion(true) ? 0 : 1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

}
