package com.xiaoxiong.design.future;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/3/25  10:40
 */
public class FutureDemo {

    public static void main(String[] args) {
        FutureClient fc = new FutureClient();
        Data data = fc.request("请求参数");
        System.out.println("请求参数发送成功");
        System.out.println("开始做其他事情");
        String result = data.getRequest();
        System.out.println(result);
    }

}
