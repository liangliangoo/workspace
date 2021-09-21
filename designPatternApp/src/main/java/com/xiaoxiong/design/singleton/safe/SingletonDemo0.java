package com.xiaoxiong.design.singleton.safe;

/**
 * 静态内部类的方式实现线程安全
 * @author xiongliang
 * @version 1.0
 * @since 2021/9/11 17:38
 */
public class SingletonDemo0 {

  private SingletonDemo0 () {

  }

  private static class SingletonInstance {
    private static final SingletonDemo0 INSTANCE = new SingletonDemo0();
  }

  /**
   * 这种方式也可以保证线程安全
   * JVM帮我们实现了线程安全
   * 主要原因是static内部类保证全局唯一
   * @return  SingletonDemo0
   */
  public static SingletonDemo0 getInstance() {
    return SingletonInstance.INSTANCE;
  }

}
