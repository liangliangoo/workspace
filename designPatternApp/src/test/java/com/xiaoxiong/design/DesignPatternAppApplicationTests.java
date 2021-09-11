package com.xiaoxiong.design;

import com.xiaoxiong.design.filter.check.CheckerHandler;
import com.xiaoxiong.design.filter.check.CheckerHandlerFactoryBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DesignPatternAppApplicationTests {

  @Autowired
  private CheckerHandlerFactoryBean checkerHandlerFactoryBean;

  @Test
  void contextLoads() throws Exception {
    System.out.println("test");
    CheckerHandler handler = checkerHandlerFactoryBean.getObject();
    assert handler != null;
    handler.check(10L);
  }

}
