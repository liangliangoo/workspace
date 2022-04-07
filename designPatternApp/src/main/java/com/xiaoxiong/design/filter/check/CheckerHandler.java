package com.xiaoxiong.design.filter.check;

import java.util.HashMap;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/9/10 20:53
 */
public class CheckerHandler {

  public CheckerResult check(Long id) {
    return new CheckerResult(new HashMap<>());
  }

  /**
   * 构造处理链
   * @param handler CheckerHandler
   * @param checkers Checker[]
   * @return CheckerHandler
   */
  public static CheckerHandler wrapHandler(CheckerHandler handler, Checker[] checkers) {
    CheckerHandler last = handler;
    for (int i = checkers.length - 1; i >= 0; --i) {
      CheckerHandler next = last;
      Checker checker = checkers[i];
      last = new CheckerHandler() {
        @Override
        public CheckerResult check(Long id) {
          // 形成责任链的关键，
          return checker.check(next,id);
        }
      };
    }
    return last;
  }

}
