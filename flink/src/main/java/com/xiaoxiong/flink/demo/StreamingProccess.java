package com.xiaoxiong.flink.demo;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * 流处理
 * @author xiongliang
 * @version 1.0
 * @since 2021/8/23 22:20
 */
public class StreamingProccess {

    public static void main(String[] args) {
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> dataStreamSource = environment.socketTextStream("47.112.159.14", 8080);
        SingleOutputStreamOperator<Tuple2<String, Integer>> result = dataStreamSource.flatMap(new BatchProcess.MyFlatMap()).keyBy(0).sum(1);
        result.print();
        try {
            environment.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
