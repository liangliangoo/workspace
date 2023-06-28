package com.xiaoxiong.bumblebee.chat.config;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @Author 六月
 * @Date 2023/6/28 11:41
 * @Version 1.0
 */
public class ChannelGroupConfig {

    //存储每一个客户端接入进来的对象
    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

}
