package com.xiaoxiong.flink.window;

import com.xiaoxiong.flink.pojo.Sensor;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.SlidingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/12/15  19:04
 */
public class WindowDemo1 {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<String> dataStream = environment.readTextFile("F:\\JavaProject\\appdemo\\flink\\sensor.txt");
        DataStream<Sensor> outputStreamOperator = dataStream.map(str -> {
            String[] split = str.split(",");
            return new Sensor(Integer.valueOf(split[0]), Long.valueOf(split[1]), Double.valueOf(split[2]));
        });
        SingleOutputStreamOperator<Sensor> max = outputStreamOperator.keyBy(Sensor::getId)
                .window(SlidingProcessingTimeWindows.of(Time.seconds(10),Time.seconds(5))) //添加滑动窗口，采用处理时间进行处理
                .max("temperature");
        max.print("max");
        environment.execute();

    }

}
