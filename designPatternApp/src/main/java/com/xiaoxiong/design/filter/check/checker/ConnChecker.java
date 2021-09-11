package com.xiaoxiong.design.filter.check.checker;

import com.xiaoxiong.design.filter.check.CheckerHandler;
import com.xiaoxiong.design.filter.check.CheckerResult;
import java.util.HashMap;
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
    CheckerResult checkerResult = new CheckerResult(new HashMap<>());
    //具体业务处理逻辑
    checkerResult.getResult().put("connChecker", "success");
    return checkerResult.merge(handler.check(id));
  }
}
