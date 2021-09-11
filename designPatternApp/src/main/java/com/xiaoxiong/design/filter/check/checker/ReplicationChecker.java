package com.xiaoxiong.design.filter.check.checker;

import com.xiaoxiong.design.filter.check.CheckerHandler;
import com.xiaoxiong.design.filter.check.CheckerResult;
import java.util.HashMap;
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
    CheckerResult checkerResult = new CheckerResult(new HashMap<>());
    //具体业务处理逻辑
    checkerResult.getResult().put("ReplicationChecker", "success");
    return checkerResult.merge(handler.check(id));
  }
}
