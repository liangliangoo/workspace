package com.xiaoxiong.design.singleton.safe;

/**
 * 枚举实现方式不仅能够避免多线程同步问题，
 * 而且还能防止反序列化重新创建新的对象。
 * @author xiongliang
 * @version 1.0
 * @since 2021/9/11 17:45
 */
public class SingletonDemo2 {

  public static void main(String[] args) {
    Singleton instance1 = Singleton.INSTANCE;
    Singleton instance2 = Singleton.INSTANCE;
    System.out.println(instance1 == instance2);
  }

}

enum Singleton {

  INSTANCE;

  public void say() {
    System.out.println("singleton");
  }
}
