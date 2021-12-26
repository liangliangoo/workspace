package com.xiaoxiong.flink.watermark;

import com.xiaoxiong.flink.constant.NetCat;
import com.xiaoxiong.flink.pojo.Sensor;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor;
import org.apache.flink.streaming.api.windowing.time.Time;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/12/16  14:56
 */
public class Demo1 {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<String> inputStream = environment.socketTextStream(NetCat.HOST, NetCat.PORT);

        // 将nc数据转换为sensor对象
        SingleOutputStreamOperator<Sensor> outputStreamOperator = inputStream.map(line -> {
            String[] split = line.split(",");
            return new Sensor(Integer.valueOf(split[0]), Long.valueOf(split[1]), Double.valueOf(split[2]));
        }).assignTimestampsAndWatermarks(new BoundedOutOfOrdernessTimestampExtractor<Sensor>(Time.seconds(3)) {
            @Override
            public long extractTimestamp(Sensor element) {
                return element.timeStamp;
            }
        });
        // .assignTimestampsAndWatermarks(new AssignerWithPeriodicWatermarksAdapter.Strategy<>());

        environment.execute();
    }
}
