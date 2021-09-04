package com.xiaoxiong.jeager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * test
 * @author xiongliang
 * @version 1.0
 * @since 2021/9/2 22:23
 */
@RestController
public class TracingController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${server.port:8080}")
    private int port;

    @RequestMapping("/tracing")
    public String tracing() throws InterruptedException {
        Thread.sleep(100);
        return "tracing";
    }

    @RequestMapping("/open")
    public String open() throws InterruptedException {
        ResponseEntity<String> response =
                restTemplate.getForEntity("http://localhost:" + port + "/tracing",
                        String.class);
        Thread.sleep(200);
        return "open " + response.getBody();
    }
}
