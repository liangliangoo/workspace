package com.xiaoxiong.consumer;

import org.junit.Test;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/30  17:57
 */
public class Consumer1Test {

    @Test
    public void consume_test() {
        Consumer1 consumer1 = new Consumer1();
        consumer1.consume("test");
    }
}
