package com.xiaoxiong.mapreducer.outputformat;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/10  15:19
 */
public class LogRecordWriter extends RecordWriter<Text, NullWritable> {

    private FSDataOutputStream otherOutputStream;
    private FSDataOutputStream aOutputStream;
    Logger logger = LoggerFactory.getLogger(LogRecordWriter.class);

    public LogRecordWriter(TaskAttemptContext job) {
        try {
            FileSystem fileSystem = FileSystem.get(job.getConfiguration());
            aOutputStream = fileSystem.create(new Path("F:\\JavaProject\\appdemo\\hadoop\\mapreducer\\output\\outputDemo\\out\\a.log"));
            otherOutputStream = fileSystem.create(new Path("F:\\JavaProject\\appdemo\\hadoop\\mapreducer\\output\\outputDemo\\out\\other.log"));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void write(Text key, NullWritable value) throws IOException, InterruptedException {
        if (key.toString().contains("baidu")) {
            aOutputStream.writeBytes(key + "\n");
        } else {
            otherOutputStream.writeBytes(key + "\n");
        }
    }

    @Override
    public void close(TaskAttemptContext context) throws IOException, InterruptedException {
        IOUtils.closeStream(aOutputStream);
        IOUtils.closeStream(otherOutputStream);
    }
}
