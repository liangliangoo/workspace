package com.xiaoxiong.flink.state;

import com.xiaoxiong.flink.constant.NetCat;
import com.xiaoxiong.flink.pojo.Sensor;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.common.state.ListState;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/12/16  21:27
 */
public class KeyState {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        environment.setParallelism(1);

        DataStreamSource<String> inputStream = environment.socketTextStream(NetCat.HOST, NetCat.PORT);
        SingleOutputStreamOperator<Sensor> dataStream = inputStream.map(str -> {
            String[] split = str.split(",");
            return new Sensor(Integer.valueOf(split[0]), Long.valueOf(split[1]), Double.valueOf(split[2]));
        });

        dataStream.keyBy("id")
                .map(new KeyStateMap()).print();

        environment.execute();
    }

    private static class KeyStateMap extends RichMapFunction<Sensor, Integer> {

        private ValueState<Integer> counterValueState;

        //其他的状态声明
        private ListState<String> otherStates;

        @Override
        public void open(Configuration parameters) throws Exception {
            counterValueState = getRuntimeContext().getState(new ValueStateDescriptor<>("key-counter", Integer.class,0));
        }

        @Override
        public Integer map(Sensor value) throws Exception {
            Integer counter = counterValueState.value();
            counter++;
            counterValueState.update(counter);
            System.out.println(value
                    .toString());
            return counter;
        }
    }
}
