package com.xiaoxiong.flink.table;

import com.xiaoxiong.flink.pojo.Sensor;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.types.Row;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/12/19  19:40
 */
public class Demo1 {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        environment.setParallelism(1);
        // 1、读数据
        DataStreamSource<String> inputStream = environment.readTextFile("F:\\JavaProject\\appdemo\\flink\\sensor.txt");

        // 2、类型转换
        SingleOutputStreamOperator<Sensor> dataStream = inputStream.map(line -> {
            String[] split = line.split(",");
            return new Sensor(Integer.valueOf(split[0]), Long.valueOf(split[1]), Double.valueOf(split[2]));
        });

        // 3、创建表环境
        StreamTableEnvironment tableEnvironment = StreamTableEnvironment.create(environment);

        // 4、基于流创建表
        Table dataTable = tableEnvironment.fromDataStream(dataStream);

        // 5、基于table进行转换


        // 6
        tableEnvironment.createTemporaryView("sensor", dataTable);
        String sql = "select * from sensor where id = 1";
        Table table = tableEnvironment.sqlQuery(sql);
        tableEnvironment.toAppendStream(table, Row.class).print("sql");

        environment.execute();

    }

}
