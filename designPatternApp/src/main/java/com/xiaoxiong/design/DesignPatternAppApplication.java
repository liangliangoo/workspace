package com.xiaoxiong.design;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DesignPatternAppApplication {

  public static void main(String[] args) {
    SpringApplication.run(DesignPatternAppApplication.class, args);
    System.out.println();
  }

}
