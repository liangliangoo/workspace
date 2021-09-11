package com.xiaoxiong.design.filter;

import com.xiaoxiong.design.filter.check.CheckerHandler;
import com.xiaoxiong.design.filter.check.CheckerHandlerFactoryBean;
import com.xiaoxiong.design.filter.check.CheckerResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/9/11 11:16
 */
@SpringBootTest
public class FilterChainTest {

  @Autowired
  private CheckerHandlerFactoryBean checkerHandlerFactoryBean;

  /**
   * 责任链模式 测试
   * @throws Exception e
   */
  @Test
  void contextLoads() throws Exception {
    CheckerHandler handler = checkerHandlerFactoryBean.getObject();
    assert handler != null;
    CheckerResult check = handler.check(10L);
    System.out.println(check.getResult().toString());
  }

}
