package com.xiaoxiong.design.filter.check;

import lombok.Setter;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/9/10 21:00
 * 注意：
 *    1. FactoryBean 仅仅是一个工厂，用于封装了Bean的创建过程
 *    2. BeanFactory 也是用来创建Bean，但是它是spring IOC 容器的一个规范
 */
@Component("checkerHandler")
@Setter
public class CheckerHandlerFactoryBean implements FactoryBean<CheckerHandler> {

  @Autowired
  private Checker[] checkers;

  @Override
  public CheckerHandler getObject() throws Exception {
    CheckerHandler handler = new CheckerHandler();
    return CheckerHandler.wrapHandler(handler,checkers);
  }

  /**
   * 当时这个方法返回值写成了this.getClass()
   * 导致   ===》Caused by: org.springframework.beans.factory.BeanNotOfRequiredType
   *
   * @return Class
   */
  @Override
  public Class<?> getObjectType() {
    return CheckerHandler.class;
  }

  @Override
  public boolean isSingleton() {
    return true;
  }
}
