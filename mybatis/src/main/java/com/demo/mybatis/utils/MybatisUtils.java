package com.demo.mybatis.utils;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/6/30 13:38
 */
@Slf4j
public class MybatisUtils {


    @SneakyThrows
    public static SqlSession getSqlSession() {
        log.info("init()");
        String resource = "mappers/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);
        SqlSession sqlSession = factory.openSession();
        return sqlSession;
    }

    @SneakyThrows
    public static SqlSessionFactory getSqlSessionFactory() {
        log.info("init()");
        String resource = "mappers/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);
        return factory;
    }


}
