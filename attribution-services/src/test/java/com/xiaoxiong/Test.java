package com.xiaoxiong;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author 六月
 * @Date 2023/8/23 19:53
 * @Version 1.0
 */
public class Test {

    public static void main(String[] args) throws ParseException {
        String s = "TT13_yule_0517";
        System.out.println(s.matches("([^_ ]*)[_ ]"));
        System.out.println(getDateByStr("2023-08-19"));
    }

    public static Date getDateByStr(String str) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(str);
    }
}
