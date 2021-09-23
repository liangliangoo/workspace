package com.xiaoxiong.flink.demo;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.AggregateOperator;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

/**
 * 批处理
 * @author xiongliang
 * @version 1.0
 * @since 2021/8/22 13:57
 */
public class BatchProcess {

    public static void main(String[] args) {

        ExecutionEnvironment environment = ExecutionEnvironment.getExecutionEnvironment();

        String path = "F:\\JavaProject\\appdemo\\flink\\demo1.txt";
        DataSource<String> dataSource = environment.readTextFile(path);

        AggregateOperator<Tuple2<String, Integer>> result = dataSource.flatMap(new MyFlatMap()).groupBy(0).sum(1);

        try {
            result.print();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static class MyFlatMap implements FlatMapFunction<String, Tuple2<String,Integer>> {

        @Override
        public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
            String[] words = s.split(" ");
            for (String word : words) {
                collector.collect(new Tuple2<>(word,1));
            }

        }
    }

}
