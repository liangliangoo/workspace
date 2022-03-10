package com.xiaoxiong.mockdata.enity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/2/14 13:18
 */
@ExtendWith(MockitoExtension.class)
public class ATest {

    @InjectMocks
    private A a=new A();
    @Mock
    private B b;
    @Spy
    private D d;
    @InjectMocks
    private C c= Mockito.spy(new C());;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @ParameterizedTest
    @ValueSource(strings = {"/com/alibaba/cq/springtest/jcode5test/needMockService/A/func.json"})
    public void funcTest(String str) {
        System.out.println(str);
        a.func();
        //todo verify the result
    }

}
