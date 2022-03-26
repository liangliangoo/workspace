package com.xiaoxiong.springdemo.listener;

import com.xiaoxiong.springdemo.event.StringEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/3/26  10:39
 */
@Component
public class EventListener implements ApplicationListener<StringEvent> {

    @Override
    public void onApplicationEvent(StringEvent o) {
        System.out.println("监听到事件，内容："+o.getStr());
    }

}