package com.xiaoxiong.base.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数字处理工具类
 * @Author 六月
 * @Date 2023/1/25 18:46
 * @Version 1.0
 */
public class NumberUtil {

    private static final Pattern ISNUMERIC_PATTERN = Pattern.compile("-?[0-9]+\\.?[0-9]*");

    private static final Pattern ISCONTAINCHINESE_PATTERN = Pattern.compile("[\u4E00-\u9FA5|\\！|\\，|\\。|\\（|\\）|\\《|\\》|\\“|\\”|\\？|\\：|\\；|\\【|\\】]");

    /**
     * 判断字符串是数字，包括浮点数
     *
     * @return 是/否
     */
    public static boolean isNumeric(String s) {

        // 该正则表达式可以匹配所有的数字 包括负数
        String bigStr;
        try {
            bigStr = new BigDecimal(s).toString();
        } catch (Exception e) {
            return false;//异常 说明包含非数字。
        }

        Matcher isNum = ISNUMERIC_PATTERN.matcher(bigStr); // matcher是全匹配
        return isNum.matches();
    }

    /**
     * 获取${source}保留${digits}位小数double
     *
     * @param source 原数据
     * @param digits 保留几位小数，如：保留2位小数传2
     * @return
     */
    public static double reservedDigits(double source, int digits) {
        BigDecimal bigDecimal = new BigDecimal(source + "");
        return bigDecimal.setScale(digits, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 处理一万以上的数值
     *
     * @param amount
     * @return
     */
    public static String handleOverWan(Long amount) {
        String result = "0";
        if (null == amount || amount.longValue() < 0) {
            return result;
        }
        if (amount < 10000) {
            return amount.toString();
        } else if (amount < 100000000) {
            BigDecimal bigDecimal = new BigDecimal(amount);
            bigDecimal = bigDecimal.divide(new BigDecimal("10000"), 1, RoundingMode.DOWN);
            result = bigDecimal.doubleValue() + "万";
        } else {
            BigDecimal bigDecimal = new BigDecimal(amount);
            bigDecimal = bigDecimal.divide(new BigDecimal("100000000"), 2, RoundingMode.DOWN);
            result = bigDecimal.doubleValue() + "亿";
        }
        if (result.indexOf(".0") != -1) {
            result = result.replace(".0", "");
        }
        return result;
    }

    /**
     * 两long型相除，保留指定位小数
     * @param value1     原始值
     * @param value1     被除数
     * @param num       保留位数
     * @param keepType  保留方式，建议以该种方式传入：BigDecimal.XXX
     *                  BigDecimal.ROUND_HALF_UP    四舍五入
     *                  BigDecimal.ROUND_HALF_DOWN  五舍六入
     *                  BigDecimal.ROUND_UP         直接进位+1
     *                  BigDecimal.ROUND_DOWN       直接去掉尾数
     * @return
     */
    public static String keepBigDecimalsDivide(Long value1, Long value2, Integer num, Integer keepType) {
        BigDecimal decimal1 = new BigDecimal(value1);
        BigDecimal decimal2 = new BigDecimal(value2);
        BigDecimal decimal = decimal1.divide(decimal2, num, keepType);
        return decimal.toString();
    }

    /**
     * 字符串是否包含中文或中文标点
     *
     * @param str 待校验字符串
     * @return true - 包含中文字符 ; false - 不包含中文字符
     */
    public static boolean isContainChinese(String str) {

        if (StringUtils.isEmpty(str)) {
            throw new IllegalStateException();
        }
        Matcher matcher = ISCONTAINCHINESE_PATTERN.matcher(str);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    /**
     * 可用double
     * @param var
     * @return
     */
    public static boolean doubleAvailable(Double var){
        if (null == var || var.isInfinite() || var.isNaN()){
            return false;
        }
        return true;
    }

}
