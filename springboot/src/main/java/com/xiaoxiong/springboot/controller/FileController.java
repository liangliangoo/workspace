package com.xiaoxiong.springboot.controller;

import com.xiaoxiong.springboot.file.ReceiveExcelManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * 文件处理接口
 * @Author 六月
 * @Date 2023/4/3 15:35
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/file/")
public class FileController {

    @Resource
    private ReceiveExcelManager receiveExcelManager;


    @PostMapping("/ad/receive-excel")
    public Boolean receiveExcelAd(MultipartFile file, HttpServletResponse response) {
        receiveExcelManager.receiveExcelAd(file, response);
        return Boolean.TRUE;
    }

}
