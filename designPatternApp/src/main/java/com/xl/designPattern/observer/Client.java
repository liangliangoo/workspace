package com.xl.designPattern.observer;

/**
 * @author liangliang
 * @date 2021/2/21 14:14
 * @e-mail 1640432919@qq.com
 */
public class Client {

    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditions currentConditions = new CurrentConditions();
        weatherData.registerObserver(currentConditions);
        weatherData.setData(300,200,100);
    }

}
