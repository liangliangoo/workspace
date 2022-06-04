package com.xiaoxiong.redisdemo.case2.service;

import com.xiaoxiong.redisdemo.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/1/20 16:10
 */
@SpringBootTest
public class AbstractServiceTest {

    @Autowired
    private AbstractService abstractService;

    @Test
    public void test_queryBookCacheable() {
        Book book = abstractService.queryBookCacheable("1");
        System.out.println(book);
    }

    @Test
    public void test_clearBook1All() {
        abstractService.clearBook1All();
    }

}
