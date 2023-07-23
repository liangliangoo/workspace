package com.xiaoxiong.designPattern.filter.check;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/9/10 20:52
 */
public interface Checker {

    CheckerResult check(CheckerHandler handler, Long id);

}
