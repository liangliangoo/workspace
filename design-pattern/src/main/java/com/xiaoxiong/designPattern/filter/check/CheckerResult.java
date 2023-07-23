package com.xiaoxiong.designPattern.filter.check;

import lombok.Data;

import java.util.Map;

/**
 * 接收校验结果
 * @author xiongliang
 * @version 1.0
 * @since 2021/9/10 20:46
 */
@Data
public class CheckerResult {

  private Map<String,String> result;

  public CheckerResult(Map<String, String> result) {
    this.result = result;
  }

  public CheckerResult merge(CheckerResult checkerResult) {
    result.putAll(checkerResult.getResult());
    return this;
  }

}
