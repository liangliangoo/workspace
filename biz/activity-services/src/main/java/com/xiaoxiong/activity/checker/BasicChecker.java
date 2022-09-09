package com.xiaoxiong.activity.checker;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author 六月
 * @Date 2022/9/4 22:05
 * @Version 1.0
 */
@Slf4j
public abstract class BasicChecker implements Checker {

    private Map<CheckerEnum, Checker> checkerMap = new ConcurrentHashMap<>();

    /**
     *
     * @param checker
     */
    @Override
    public void addNextChecker(Checker checker) {

    }

    /**
     * 构建校验逻辑
     * @param context
     * @return
     */
    @Override
    public Boolean check(CheckContext context) {
        return null;
    }

    /**
     * 子类需要实现自己的校验逻辑
     * @param context
     * @return
     */
    protected abstract Boolean doChecker(CheckContext context);

}
