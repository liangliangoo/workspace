package com.xl.designPattern.singleton;

/**
 * @author liangliang
 * @date 2021/1/27 11:18
 * @e-mail 1640432919@qq.com
 */
public class SingletonTest06 {

    public static void main(String[] args) {
        // System.out.println(Singleton06.getInstance() == Singleton06.getInstance());
        Singleton06 instance = Singleton06.getInstance();
        for (int i = 0; i < 10_0000; i++) {
            Thread thread = new Thread(() -> {
                // System.out.print(Thread.currentThread().getName().toString() + "\t");
                String string = Thread.currentThread().getName().toString();
                System.out.println(string + (Singleton06.getInstance() == instance));

            });
            thread.start();
        }


    }

}


class Singleton06 {

    private Singleton06() {

    }

    //静态内部类
    private static class SingletonInstance {
        private static final Singleton06 INSTANCE = new Singleton06();
    }

    public static Singleton06 getInstance() {
        return SingletonInstance.INSTANCE;
    }

}
