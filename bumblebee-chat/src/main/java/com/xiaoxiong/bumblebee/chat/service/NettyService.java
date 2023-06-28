package com.xiaoxiong.bumblebee.chat.service;

import com.xiaoxiong.bumblebee.chat.model.ChatRecord;
import com.xiaoxiong.bumblebee.chat.model.User;
import com.xiaoxiong.bumblebee.chat.model.UserFriendVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <a href = "https://gitee.com/yin-daiyong/springcloud-study/blob/master/ydy-destined/ydy-netty/src/main/java/com/ydy/netty/service/NettyService.java"></a>
 * @Author 六月
 * @Date 2023/6/27 21:55
 * @Version 1.0
 */
public interface NettyService {

    List<UserFriendVo> getUserFriendsList(String userCode);

    List<UserFriendVo> getCanAddFriendList(String userCode);


    void addFriend(String userCode,String friendCode);

    void insertChatRecord(ChatRecord chatRecord);

    Map<String, List<ChatRecord>> queryChatRecord(String userCode);

    User queryUserInfo(String userCode);

    String getWebSocketUrl();

    User headPortraitImageUpload(MultipartFile file, String userCode, String nickName) ;

    List<UserFriendVo> queryAddFriendRequestList(String userCode);

    //void agreeAddFriend(UserFriend userFriend);
    //
    //void addGroupChat(List<UserFriend> list);

    List<String> queryGroupChatUsers(String toCode);

    List<UserFriendVo> getFriendList(String userCode);

}
