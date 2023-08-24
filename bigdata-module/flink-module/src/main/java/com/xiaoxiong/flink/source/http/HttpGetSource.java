package com.xiaoxiong.flink.source.http;

import com.alibaba.fastjson.JSONObject;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.metrics.Counter;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;

/**
 * http get 请求数据源
 * @author 小熊不会写代码
 * @date 2023/8/22
 */
public class HttpGetSource extends RichSourceFunction<JSONObject> {

    private volatile boolean isRunning = true;
    private String url;
    private long requestInterval;// 请求时间间隔
    private DeserializationSchema<JSONObject> deserializationSchema;
    private transient Counter counter;

    public HttpGetSource(String url, long requestInterval, DeserializationSchema<JSONObject> deserializationSchema) {
        this.url = url;
        this.requestInterval = requestInterval;
        this.deserializationSchema = deserializationSchema;
    }

    @Override
    public void open(Configuration parameters) throws Exception {
        this.counter = getRuntimeContext()
                .getMetricGroup()
                .counter("myGetHttpCounter");
    }

    @Override
    public void run(SourceContext<JSONObject> sourceContext) throws Exception {
        while (isRunning) {
            try {
                String ret = null; //  http 响应数据
                sourceContext.collect(JSONObject.parseObject(ret));
                Thread.sleep(requestInterval);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void cancel() {
        this.isRunning = false;
    }
}
