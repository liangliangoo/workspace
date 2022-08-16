package com.xiaoxiong.userservices.dal;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaoxiong.userservices.dataobj.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author 六月
 * @Date 2022/8/13 22:20
 * @Version 1.0
 */
@Mapper
public interface UserDao extends BaseMapper<UserDO> {

}
