package com.xiaoxiong.design.future;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/3/25  10:37
 */
public class RealData implements Data{

    private String result;

    public RealData() {
    }

    public RealData(String queryStr) {
        System.out.println("根据" + queryStr + "进行查询" + "这是一个很耗时间的操作...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("操作完毕");
        result = "查询结果";
    }

    @Override
    public String getRequest() {
        return result;
    }

}
