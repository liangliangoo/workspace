package com.xiaoxiong.redisdemo.utils;

import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/1/18 12:04
 */
public class LongitudeAndLatitude_Test {

    @Test
    public void test_getLongitudeAndLatitude() {
        try {
            System.out.println(LongitudeAndLatitude.getLongitudeAndLatitude("河南省信阳市").toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_getLngAndLag() {
        try {
            System.out.println(LongitudeAndLatitude.getLngAndLag("信阳市肖店乡"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
