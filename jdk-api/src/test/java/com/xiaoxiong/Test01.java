package com.xiaoxiong;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 基础数据类型
 * @Author 六月
 * @Date 2023/7/31 14:39
 * @Version 1.0
 */
public class Test01 {

    public static void main(String[] args) {
        DrawContext drawContext = new DrawContext();
        drawContext.setTimes(10_0000);
        Integer times = drawContext.getTimes();
        times = 100;
        System.out.println(drawContext.getTimes());
        System.out.println(StrUtil.concat(true, null, null));
        System.out.println(StrUtil.concat(false, "sss", null));
        System.out.println(StrUtil.join("-", "1", null, "2"));
        List<String> strings = parseBatchDateParams("20230801@20230831");
        System.out.println(strings.size());
    }

    private static List<String> parseBatchDateParams(String params) {
        if (StringUtils.isBlank(params) || params.split("@").length < 2) {
            return Lists.newArrayList();
        }
        String[] split = params.split("@");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate start = LocalDate.parse(split[0], formatter);
        LocalDate end = LocalDate.parse(split[1], formatter);
        ArrayList<String> ret = Lists.newArrayList();
        for (LocalDate i = start; i.isBefore(end) || i.isEqual(end); i = i.plusDays(1)) {
            ret.add(String.format("%s-%s", formatter.format(i), formatter.format(i)));
        }
        return ret;
    }

    @Data
    static class DrawContext {
        private Integer times;
    }
}
