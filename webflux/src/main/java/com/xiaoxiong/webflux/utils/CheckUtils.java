package com.xiaoxiong.webflux.utils;

import com.xiaoxiong.webflux.exceptions.MyCheckException;

import java.util.stream.Stream;

/**
 * @author xiongliang
 * @version 1.0
 * @description 检车
 * @since 2022/5/11  15:00
 */
public class CheckUtils {

    private static String[] INVALID_NAMES = {"admin", "root"};

    public static void checkUserName(String value) {
        Stream.of(INVALID_NAMES)
                .filter(name -> name.equalsIgnoreCase(value))
                .findAny().ifPresent(name -> {
                    throw new MyCheckException("name", value);
                });
    }

}
