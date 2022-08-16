package com.xiaoxiong.common.dubbo;

import com.xiaoxiong.common.vo.UserVo;

/**
 * @Author 六月
 * @Date 2022/8/14 13:56
 * @Version 1.0
 */
public interface UserManager {

    UserVo getUserByID(Integer id);

}
