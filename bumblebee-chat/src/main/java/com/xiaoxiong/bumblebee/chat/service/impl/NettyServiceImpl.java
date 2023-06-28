package com.xiaoxiong.bumblebee.chat.service.impl;

import com.xiaoxiong.bumblebee.chat.model.ChatRecord;
import com.xiaoxiong.bumblebee.chat.model.User;
import com.xiaoxiong.bumblebee.chat.model.UserFriendVo;
import com.xiaoxiong.bumblebee.chat.service.NettyService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Author 六月
 * @Date 2023/6/28 14:28
 * @Version 1.0
 */
@Component
public class NettyServiceImpl implements NettyService {
    @Override
    public List<UserFriendVo> getUserFriendsList(String userCode) {
        return null;
    }

    @Override
    public List<UserFriendVo> getCanAddFriendList(String userCode) {
        return null;
    }

    @Override
    public void addFriend(String userCode, String friendCode) {

    }

    @Override
    public void insertChatRecord(ChatRecord chatRecord) {

    }

    @Override
    public Map<String, List<ChatRecord>> queryChatRecord(String userCode) {
        return null;
    }

    @Override
    public User queryUserInfo(String userCode) {
        return null;
    }

    @Override
    public String getWebSocketUrl() {
        return null;
    }

    @Override
    public List<String> queryGroupChatUsers(String toCode) {
        return null;
    }
}
