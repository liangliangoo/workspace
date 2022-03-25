package com.xiaoxiong.design.future;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/3/25  10:39
 */
public class FutureClient {

    public Data request(final String queryStr) {
        // 1 我想要一个代理对象（Data接口的实现类）先返回给发送请求的客户端，
        // 告诉他的请求已经收到，可以干其他事情
        final FutureData futureData = new FutureData();

        // 2.创建一个新的线程，去加载真是数据,传递个这个代理对象
        new Thread(() -> {
            // 3.这个新的线程可以慢慢去加载真是对象，然后传递给代理对象
            RealData realData = new RealData(queryStr);
            futureData.setRealData(realData);
        }).start();

        return futureData;
    }

}
