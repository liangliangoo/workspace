package com.xiaoxiong.design.singleton.lazy;

/**
 * 懒汉式实现单例模式
 * @author xiongliang
 * @version 1.0
 * @since 2021/9/11 17:52
 */
public class LazySingleton0 {

  private LazySingleton0() {

  }

  // 实现1
  private static LazySingleton0 instance1;

  // 存在线程安全问题
  public static LazySingleton0 getInstance1() {
    if (instance1 == null) {
      instance1 = new LazySingleton0();
    }
    return instance1;
  }

  //线程安全
  public synchronized static LazySingleton0 getInstance2() {
    if (instance1 == null) {
      instance1 = new LazySingleton0();
    }
    return instance1;
  }

  //CAS
  public static LazySingleton0 getInstance3() {
    if (instance1 == null) {
      synchronized (LazySingleton0.class) {
        if (instance1 == null) {
          instance1 = new LazySingleton0();
        }
      }
    }
    return instance1;
  }

}
