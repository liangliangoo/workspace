package com.xiaoxiong;

import java.util.Objects;

/**
 * @Author 六月
 * @Date 2023/5/17 15:38
 * @Version 1.0
 */
public abstract class AbstractManager {

    abstract String getChannle();




    /**
     * umt_campany
     * @return
     */
    abstract Object getData();

    public static class Data {
        String dataranger;
    }

}
