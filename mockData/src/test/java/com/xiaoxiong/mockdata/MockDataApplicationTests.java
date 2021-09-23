package com.xiaoxiong.mockdata;

import com.github.houbb.data.factory.core.util.DataUtil;
import com.xiaoxiong.mockdata.dao.UserDao;
import com.xiaoxiong.mockdata.enity.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.creation.settings.CreationSettings;
import org.mockito.internal.util.MockUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MockDataApplicationTests {

    @Autowired
    private UserDao userDao;

    @Test
    void contextLoads() {
        System.out.println(userDao);
        for (int i = 0; i < 1000_0000; i++) {
            User build = DataUtil.build(User.class);
            System.out.println(build.toString());
        }
    }



    @Test
    public void test_DataUtil() {
        User build = DataUtil.build(User.class);
        System.out.println(build);
    }

    @Test
    public void test_mock() {
        User mock = Mockito.mock(User.class);
        System.out.println(mock);
    }

}
