package com.xiaoxiong.mockdata.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

class StaticUtilsTest {


    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void range() {
        try (MockedStatic<StaticUtils> mockedStatic = Mockito.mockStatic(StaticUtils.class)) {
            mockedStatic.when(() -> StaticUtils.range(1, 10)).thenReturn(Arrays.asList(1, 2, 3, 4));
            List<Integer> range = StaticUtils.range(1, 10);
            System.out.println(range.size());
            mockedStatic.clearInvocations();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void name() {
    }

}