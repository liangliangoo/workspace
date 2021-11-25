package com.xiaoxiong.springboot.listener.mylistener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 监听请求
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/1 10:44
 */
@Component
@Slf4j
public class MyServletRequestListener implements ServletRequestListener {


  /**
   * The request is about to go out of scope of the web application. The default implementation is a NO-OP.
   *
   * @param sre Information about the request
   */
  @Override
  public void requestDestroyed(ServletRequestEvent sre) {
    ServletRequestListener.super.requestDestroyed(sre);
  }

  /**
   * The request is about to come into scope of the web application. The default implementation is a NO-OP.
   *
   * @param sre Information about the request
   */
  @Override
  public void requestInitialized(ServletRequestEvent sre) {
    HttpServletRequest servletRequest = (HttpServletRequest) sre.getServletRequest();
    log.info("session id 是： {}", servletRequest.getRequestedSessionId());
    log.info("request url is: {}", servletRequest.getRequestURL());
    
  }

}
