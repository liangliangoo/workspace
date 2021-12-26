package com.xiaoxiong.flink.pojo;

import java.io.Serializable;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/12/15  19:05
 */
public class Sensor implements Serializable {

    public Integer id;
    public Long timeStamp;
    public Double temperature;

    public Sensor() {
    }

    public Sensor(Integer id, Long timeStamp, Double temperature) {
        this.id = id;
        this.timeStamp = timeStamp;
        this.temperature = temperature;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + id +
                ", timeStamp=" + timeStamp +
                ", temperature=" + temperature +
                '}';
    }
}
