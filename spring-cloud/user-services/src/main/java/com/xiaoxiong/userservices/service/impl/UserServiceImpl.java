package com.xiaoxiong.userservices.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxiong.userservices.dal.UserDao;
import com.xiaoxiong.userservices.dataobj.UserDO;
import com.xiaoxiong.userservices.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Author 六月
 * @Date 2022/8/13 23:00
 * @Version 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, UserDO> implements UserService {
}
