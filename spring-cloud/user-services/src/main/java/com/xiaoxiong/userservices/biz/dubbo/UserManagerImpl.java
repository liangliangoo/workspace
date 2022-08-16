package com.xiaoxiong.userservices.biz.dubbo;

import com.xiaoxiong.common.dubbo.UserManager;
import com.xiaoxiong.common.vo.UserVo;
import com.xiaoxiong.userservices.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Author 六月
 * @Date 2022/8/14 13:53
 * @Version 1.0
 *
 */
@DubboService
public class UserManagerImpl implements UserManager {

    @Resource
    private UserService userService;

    @Override
    public UserVo getUserByID(Integer id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return userService.getById(id).convert2Vo();
    }

}
