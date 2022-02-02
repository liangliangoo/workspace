package com.xiaoxiong.flink.state;

import com.xiaoxiong.flink.constant.NetCat;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.checkpoint.ListCheckpointed;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Collections;
import java.util.List;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/12/16  20:59
 */
public class OperationState {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        environment.setParallelism(1);

        DataStreamSource<String> inputStream = environment.socketTextStream(NetCat.HOST, NetCat.PORT);

        inputStream.map(new MyOperationStateMap()).print();

        environment.execute();

    }

    private static class MyOperationStateMap implements MapFunction<String, Integer> , ListCheckpointed<Integer> {

        private Integer counter = new Integer(0);

        @Override
        public Integer map(String value) throws Exception {
            counter++;
            return counter;
        }

        @Override
        public List<Integer> snapshotState(long checkpointId, long timestamp) throws Exception {
            return Collections.singletonList(counter);
        }

        @Override
        public void restoreState(List<Integer> state) throws Exception {
            state.forEach(i-> counter = counter + i);
        }
    }
}
