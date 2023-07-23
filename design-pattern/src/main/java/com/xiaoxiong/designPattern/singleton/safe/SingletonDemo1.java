package com.xiaoxiong.designPattern.singleton.safe;

/**
 * 线程安全的单例模式
 * @author xiongliang
 * @version 1.0
 * @since 2021/9/11 17:29
 */
public class SingletonDemo1 {

  private static volatile SingletonDemo1 demo1;

  private SingletonDemo1() {

  }

  /**
   * 线程安全
   * @return SingletonDemo1
   */
  public static SingletonDemo1 getInstance(){
    if (demo1 == null) {
      synchronized (SingletonDemo1.class) {
        if (demo1 == null) {
          demo1 = new SingletonDemo1();
        }
      }
    }
    return demo1;
  }

  public static synchronized SingletonDemo1 getInstance1() {
    demo1 = new SingletonDemo1();
    return demo1;
  }

}
