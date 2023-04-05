package com.xiaoxiong.springboot.file;

import com.alibaba.fastjson2.JSON;
import com.xiaoxiong.springboot.utils.FileHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * excel 文件接收处理器
 * @Author 六月
 * @Date 2023/4/3 15:34
 * @Version 1.0
 */
@Slf4j
@Component
public class ReceiveExcelManager {

    /**
     * 解析广告id
     * @param file      文件
     * @param response  响应流
     * @return
     */
    public Boolean receiveExcelAd(MultipartFile file, HttpServletResponse response) {
        try {
            List<List<Object>> rows = FileHandler.parseExcel(file.getInputStream());
            log.debug("receiveExcelAd details {}", JSON.toJSONString(rows));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Boolean.FALSE;
    }

}
