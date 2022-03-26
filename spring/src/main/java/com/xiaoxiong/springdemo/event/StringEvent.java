package com.xiaoxiong.springdemo.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/3/26  10:36
 */
// 事件监听对象
public class StringEvent extends ApplicationEvent {

    private String str;
    // 构造函数
    public StringEvent(Object source) {
        super(source);
        str = source.toString();
    }
    // 获取字符串
    public String getStr(){
        return str;
    }

}
