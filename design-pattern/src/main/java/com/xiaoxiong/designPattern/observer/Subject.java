package com.xiaoxiong.designPattern.observer;

/**
 * @author liangliang
 * @date 2021/2/21 13:40
 * @e-mail 1640432919@qq.com
 */
public interface Subject {

    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();
}
