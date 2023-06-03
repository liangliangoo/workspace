package com.xiaoxiong.concurrent;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 * @Author 六月
 * @Date 2023/4/4 16:16
 * @Version 1.0
 */
public class CompletableFutureTest {

    @Test
    void test_runAsync() throws IOException {
        CompletableFuture.runAsync(() -> System.out.println(1));
        System.in.read();
    }

}
