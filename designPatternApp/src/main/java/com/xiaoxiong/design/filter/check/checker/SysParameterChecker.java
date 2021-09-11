package com.xiaoxiong.design.filter.check.checker;

import com.xiaoxiong.design.filter.check.CheckerHandler;
import com.xiaoxiong.design.filter.check.CheckerResult;
import java.util.HashMap;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/9/10 20:51
 */
@Component
@Order(2)
public class SysParameterChecker extends BaseChecker{

  @Override
  public CheckerResult check(CheckerHandler handler, Long id) {
    CheckerResult checkerResult = new CheckerResult(new HashMap<>());
    //具体业务处理逻辑
    checkerResult.getResult().put("SysParameterChecker", "success");
    return checkerResult.merge(handler.check(id));
  }
}
