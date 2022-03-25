package com.xiaoxiong.design.future;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/3/25  10:35
 */
public class FutureData implements Data{

    private RealData realData;

    private boolean isReady = false;

    @Override
    public synchronized String getRequest() {

        // 如果没有装载好久一直处于阻塞状态
        while (!isReady) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 装载好可以直接获取数据
        return this.realData.getRequest();
    }

    public synchronized void setRealData(RealData realData) {
        if (isReady) {
            return;
        }
        this.realData = realData;
        isReady = true;
        notify();
    }
}
