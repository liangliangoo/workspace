package com.xiaoxiong.mockdata.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MockDemo1Test {

    @Test
    void add() {
        Random random = Mockito.mock(Random.class);
        // mock 出的random对象并没有调用真实的Random的方法
        // 这里只会返回0  因为没有定义mock对象的行为
        System.out.println(random.nextInt(9));
        // nextInt(9) 验证该方法是否被调用了1次，如果不是就报错
        Mockito.verify(random, Mockito.times(1)).nextInt(9);
    }

    @Test
    void add1() {
        Random random = Mockito.mock(Random.class);
        Mockito.when(random.nextInt(9)).thenReturn(1000);
        System.out.println(random.nextInt(9));
    }

    @Mock
    private Random random2;
    @Test
    void add2() {
        MockitoAnnotations.openMocks(this);
        Mockito.when(random2.nextInt(9)).thenReturn(1000);
        System.out.println(random2.nextInt(9));
    }

    @Spy
    private MockDemo1 mockDemo1;
    @Test
    void add3() {
        MockitoAnnotations.openMocks(this);
        System.out.println(mockDemo1.add(1, 2));
        Assertions.assertEquals(3, mockDemo1.add(1, 2));
    }

}