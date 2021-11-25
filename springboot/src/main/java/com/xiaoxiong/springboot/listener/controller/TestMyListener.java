package com.xiaoxiong.springboot.listener.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/1 10:35
 */
@RestController
@RequestMapping("api")
public class TestMyListener {

  @GetMapping("/total")
  @ResponseBody
  public String getTotalUser(HttpServletRequest request) {
    Integer count = (Integer) request.getSession().getServletContext().getAttribute("count");
    return "当前在线人数：" + count;
  }
}
