package com.xl.designPattern.observer;

import java.util.ArrayList;

/**
 * @author liangliang
 * @date 2021/2/21 13:52
 * @e-mail 1640432919@qq.com
 */
public class WeatherData implements Subject {

    private float temperature;
    private float pressure;
    private float humidity;
    private ArrayList<Observer> observers;

    public WeatherData() {
        observers = new ArrayList<>();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getPressure() {
        return pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public void dataChange() {
        notifyObservers();
    }

    public void setData(float temperature, float pressure, float humidity) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        dataChange();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for ( Observer observer: observers
             ) {
            observer.update(this.temperature,this.pressure,this.humidity);
        }
    }
}
