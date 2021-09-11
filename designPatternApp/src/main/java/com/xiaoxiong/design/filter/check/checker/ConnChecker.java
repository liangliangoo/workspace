package com.xiaoxiong.design.filter.check.checker;

import com.xiaoxiong.design.filter.check.CheckerHandler;
import com.xiaoxiong.design.filter.check.CheckerResult;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/9/10 20:45
 */
@Component
@Order(1)
public class ConnChecker extends BaseChecker {

  @Override
  public CheckerResult check(CheckerHandler handler, Long id) {
    return handler.check(id);
  }
}
