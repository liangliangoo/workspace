package com.demo.mybatis.mapper;

import com.demo.mybatis.entity.User;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/6/30 12:19
 * 批量插入
 */
public interface UserXmlMapper {

    /**
     * 上次面试问了，如何获取插入的ID
     * mybatis会修改user中的id属性，如果返回的是int类型，返回的是影响的行数
     * @param user
     */
    void insertStudentCacheId(User user);

    void insertStudentByList(List<User> users);

    List<User> findAll();

    @MapKey("id")
    Map<Integer,User> selectAllToMap();


}
