package com.demo.mybatis.mapper;

import com.demo.mybatis.GenericMapper;
import com.demo.mybatis.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/8/14 10:48
 */
@Mapper
public interface UserMapper {

    @Insert("insert into user values(#{id},#{name},#{age})")
    void insert(User user);

    @Update("update user set name = #{name},age = #{age} where id = #{id}")
    void update(User user);

    @Delete("delete from user where id = #{id}")
    void delete(Integer id);

    @Select("select * from user where id = #{id}")
    User findById(Integer id);

    @Select("select * from user")
    List<User> listAll();

}
