package com.demo.mybatis;

import java.util.List;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/8/14 10:49
 */
public interface GenericMapper<T> {

    void insert(T t);

    void update(T t);

    void delete(Integer id);

    T findById(Integer id);

    List<T> listAll();

}
