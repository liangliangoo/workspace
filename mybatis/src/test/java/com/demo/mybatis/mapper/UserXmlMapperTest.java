package com.demo.mybatis.mapper;

import com.alibaba.fastjson.JSONObject;
import com.demo.mybatis.entity.User;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.demo.mybatis.utils.MybatisUtils.getSqlSessionFactory;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/6/30 12:52
 */
@Slf4j
public class UserXmlMapperTest {

    @Test
    public void findAllTest() {
        SqlSession sqlSession = getSqlSession();
        UserXmlMapper userXmlMapper = sqlSession.getMapper(UserXmlMapper.class);
        List<User> users = userXmlMapper.findAll();
        for (User user :
                users) {
            System.out.println(user);
        }
        sqlSession.close();
    }

    @Test
    public void selectAllToMapTest() {
        SqlSession sqlSession = getSqlSession();
        UserXmlMapper userXmlMapper = sqlSession.getMapper(UserXmlMapper.class);
        Map<Integer, User> userMap = userXmlMapper.selectAllToMap();
        System.out.println(userMap.size());
        sqlSession.close();
    }

    @Test
    public void insertStudentCacheIdTest() {
        SqlSession sqlSession = getSqlSession();
        UserXmlMapper userXmlMapper = sqlSession.getMapper(UserXmlMapper.class);
        User user = new User().setAge(19).setEmail("汪汪@163.com").setName("汪汪");
        userXmlMapper.insertStudentCacheId(user);
        System.out.println(user);
        System.out.println(user.getId());
    }

    @Test
    public void batchInsertTest() {
        // 设置ExecutorType.BATCH原理：把SQL语句发个数据库，数据库预编译好，数据库等待须要运行的参数，
        // 接收到参数后一次运行，ExecutorType.BATCH只打印一次SQL语句，屡次设置参数步骤，
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        UserXmlMapper userXmlMapper = sqlSession.getMapper(UserXmlMapper.class);
        for (int i = 0; i < 1000; i++) {
            User user = new User().setAge(18 + i % 18).setEmail("liuyue" + i + "qq.com").setName("六月" + i);
            userXmlMapper.insertStudentCacheId(user);
        }
    }

    @Test
    public void insertStudentByListTest() {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserXmlMapper userXmlMapper = sqlSession.getMapper(UserXmlMapper.class);
        ArrayList<User> users = Lists.newArrayList();
        for (int i = 0; i < 100; i++) {
            User user = new User().setAge(18 + i % 18 + 10).setEmail("liuyue" + i + 1000 + "qq.com").setName("六月" + i + 1000);
            users.add(user);
        }
        log.info("insert into user List:{}", JSONObject.toJSONString(users));
        userXmlMapper.insertStudentByList(users);
        log.info("insert into user List:{}", JSONObject.toJSONString(users));
        System.out.println(users.get(0).getId());
    }

    @SneakyThrows
    private SqlSession getSqlSession() {
        log.info("init()");
        String resource = "mappers/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);
        SqlSession sqlSession = factory.openSession();
        return sqlSession;
    }


}
