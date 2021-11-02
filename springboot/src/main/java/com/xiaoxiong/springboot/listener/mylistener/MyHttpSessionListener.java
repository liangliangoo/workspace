package com.xiaoxiong.springboot.listener.mylistener;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.springframework.stereotype.Component;

/**
 * 监听session
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/1 10:18
 */
@Component
public class MyHttpSessionListener implements HttpSessionListener {

  private static final Logger logger = Logger.getLogger("myLogger");

  public static AtomicInteger counter = new AtomicInteger(0);

  @Override
  public void sessionCreated(HttpSessionEvent httpSessionEvent) {
    logger.info("新用户上线了");
    counter.incrementAndGet();
    httpSessionEvent.getSession().getServletContext().setAttribute("count", counter.get());
  }

  @Override
  public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
    logger.info("用户下线了");
    counter.decrementAndGet();
    httpSessionEvent.getSession().getServletContext().setAttribute("count",counter.get());
  }

}
