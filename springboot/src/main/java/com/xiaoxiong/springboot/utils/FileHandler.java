package com.xiaoxiong.springboot.utils;

import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

import java.io.InputStream;
import java.util.List;

/**
 * @Author 六月
 * @Date 2023/4/3 15:51
 * @Version 1.0
 */
public class FileHandler {

    ///**
    // * 解析Excel文件
    // *
    // * @param in
    // * @param fileName
    // */
    //public static List<List<Object>> parseExcel(InputStream in, String fileName) throws Exception {
    //    List list = null;
    //    Workbook work = null;
    //    list = new ArrayList<>();
    //    //创建Excel工作薄
    //    work = getWorkbook(in, fileName);
    //    if (null == work) {
    //        throw new Exception("创建Excel工作薄为空！");
    //    }
    //    Sheet sheet = null;
    //    Row row = null;
    //    Cell cell = null;
    //
    //    for (int i = 0; i < work.getNumberOfSheets(); i++) {
    //        sheet = work.getSheetAt(i);
    //        if (sheet == null) {
    //            continue;
    //        }
    //
    //        for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
    //            row = sheet.getRow(j);
    //            if (row == null || row.getFirstCellNum() == j) {
    //                continue;
    //            }
    //
    //            List<Object> li = new ArrayList<>();
    //            for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
    //                cell = row.getCell(y);
    //                li.add(cell);
    //            }
    //            list.add(li);
    //        }
    //    }
    //    return list;
    //
    //}

    public static List<List<Object>> parseExcel(InputStream inputStream) {
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        return reader.read();
    }

    public static void parseCsv(InputStream inputStream) {
        CsvReader reader = CsvUtil.getReader();
        //reader.read(FileUtil.)
    }

}
