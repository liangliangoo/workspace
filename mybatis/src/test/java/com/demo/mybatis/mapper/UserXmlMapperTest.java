package com.demo.mybatis.mapper;

import com.demo.mybatis.entity.User;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
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
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
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
