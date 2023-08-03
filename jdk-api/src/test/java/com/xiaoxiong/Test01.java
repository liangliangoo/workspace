package com.xiaoxiong;

import cn.hutool.core.util.StrUtil;
import lombok.Data;

/**
 * 基础数据类型
 * @Author 六月
 * @Date 2023/7/31 14:39
 * @Version 1.0
 */
public class Test01 {

    public static void main(String[] args) {
        DrawContext drawContext = new DrawContext();
        drawContext.setTimes(10_0000);
        Integer times = drawContext.getTimes();
        times = 100;
        System.out.println(drawContext.getTimes());
        System.out.println(StrUtil.concat(true, null, null));
        System.out.println(StrUtil.concat(false, "sss", null));
        System.out.println(StrUtil.join("-", "1", null, "2"));
    }

    @Data
    static class DrawContext {
        private Integer times;
    }
}
