package com.xiaoxiong.activity.checker;

/**
 * @Author 六月
 * @Date 2022/9/4 22:02
 * @Version 1.0
 * 采用责任链模式设计
 */
public interface Checker {

    /**
     * 校验逻辑
     * @param context
     * @return
     */
    Boolean check(CheckContext context);

    /**
     *
     * @param checker
     */
    void addNextChecker(Checker checker);

}
