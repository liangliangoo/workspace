package com.xiaoxiong.design;

import java.net.InetAddress;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DesignPatternAppApplicationTests {


  @Test
  void contextLoads() throws Exception {
    InetAddress i = InetAddress.getLocalHost();  // 获取 本地主机

    System.out.println(i.toString());
    System.out.println("address:"+i.getHostAddress());  // 本地主机的 地址
    System.out.println("name:"+i.getHostName());      // 本地主机的 名字
  }

}
