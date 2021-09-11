package com.xiaoxiong.design.filter.check.checker;

import com.xiaoxiong.design.filter.check.CheckerHandler;
import com.xiaoxiong.design.filter.check.CheckerResult;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/9/10 20:50
 */
@Component
@Order(3)
public class ReplicationChecker extends BaseChecker{

  @Override
  public CheckerResult check(CheckerHandler handler, Long id) {
    return handler.check(id);
  }
}
