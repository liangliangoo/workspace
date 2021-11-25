package com.demo.orm.executor;

import java.util.List;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/12  13:29
 */
public interface Executor {

    <E> List<E> findAll();

    boolean insert(Object o);


}
