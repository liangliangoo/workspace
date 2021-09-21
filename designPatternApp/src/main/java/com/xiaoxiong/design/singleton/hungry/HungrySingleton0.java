package com.xiaoxiong.design.singleton.hungry;

import javafx.concurrent.Task;

/**
 * 饿汉式实现单例模式
 * 线程不安全
 * @author xiongliang
 * @version 1.0
 * @since 2021/9/11 18:05
 */
public class HungrySingleton0 {

  private HungrySingleton0() {

  }

  private static HungrySingleton0 instance;

  static {
    instance = new HungrySingleton0();
  }

  public static HungrySingleton0 getInstance() {
    return instance;
  }

  public static void main(String[] args) throws Exception{
    HungrySingleton0 instance = HungrySingleton0.getInstance();
    HungrySingleton0 instance1 = HungrySingleton0.getInstance();
    Task<HungrySingleton0> task = new Task<HungrySingleton0>() {
      @Override
      protected HungrySingleton0 call() throws Exception {
        return HungrySingleton0.getInstance();
      }
    };
    HungrySingleton0 instance3 = task.getValue();
    System.out.println(instance3 == instance);
  }

}
