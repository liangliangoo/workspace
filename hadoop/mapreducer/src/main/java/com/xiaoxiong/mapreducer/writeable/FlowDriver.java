package com.xiaoxiong.mapreducer.writeable;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/7  20:15
 */
public class FlowDriver {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        try {
            Job job = Job.getInstance(configuration);
            job.setJarByClass(FlowDriver.class);
            job.setMapperClass(FlowMapper.class);
            job.setReducerClass(FlowReducer.class);

            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(FlowBean.class);

            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(FlowBean.class);
            // job.setPartitionerClass();

            FileInputFormat.setInputPaths(job,new Path("F:\\JavaProject\\appdemo\\hadoop\\mapreducer\\input\\phone_data.txt"));
            FileOutputFormat.setOutputPath(job,new Path("F:\\JavaProject\\appdemo\\hadoop\\mapreducer\\output\\flow"));

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
