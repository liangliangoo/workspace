package com.xiaoxiong.mapreducer.outputformat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/10  15:33
 */
public class LogDriver {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        try {
            Job job = Job.getInstance(configuration);

            job.setJarByClass(LogDriver.class);

            job.setMapperClass(LogMapper.class);
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(NullWritable.class);

            job.setReducerClass(LogReducer.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(NullWritable.class);

            job.setOutputFormatClass(LogOutputFormat.class);

            FileInputFormat.setInputPaths(job, new Path("F:\\JavaProject\\appdemo\\hadoop\\mapreducer\\input\\log.txt"));
            FileOutputFormat.setOutputPath(job, new Path("F:\\JavaProject\\appdemo\\hadoop\\mapreducer\\output\\outputDemo"));

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
