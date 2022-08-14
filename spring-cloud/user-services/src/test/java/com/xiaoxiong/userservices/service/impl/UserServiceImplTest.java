package com.xiaoxiong.userservices.service.impl;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.xiaoxiong.userservices.config.MybatisPlusConfig;
import com.xiaoxiong.userservices.dal.UserDao;
import com.xiaoxiong.userservices.dataobj.UserDO;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@MapperScan(basePackages = "com.xiaoxiong.userservices.dal")
@MybatisPlusTest(properties = {"spring.datasource.schema=classpath:db/schema.sql"})
@Import({MybatisPlusConfig.class})
class UserServiceImplTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testInsert() {
        PodamFactory podamFactory = new PodamFactoryImpl();
        UserDO userDO = podamFactory.manufacturePojo(UserDO.class);
        System.out.println(userDO);
        System.out.println(userDao.insert(userDO));
    }

}