package com.xiaoxiong.mockdata.demo;

import com.xiaoxiong.mockdata.enity.User;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * @author xiongliang
 * @version 1.0
 * @description
 * @since 2022/5/8  10:49
 */
public class Mock_Test {

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void test_user() {
        User user = Mockito.mock(User.class);
    }

}
