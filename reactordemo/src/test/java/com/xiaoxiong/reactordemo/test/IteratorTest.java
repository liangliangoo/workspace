package com.xiaoxiong.reactordemo.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/12  21:11
 */
public class IteratorTest {

    @Test
    public void test_iterator() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

}
