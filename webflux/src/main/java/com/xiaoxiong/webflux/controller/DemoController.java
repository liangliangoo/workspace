package com.xiaoxiong.webflux.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author xiongliang
 * @version 1.0
 * @description
 * @since 2022/5/9  19:50
 */
@RestController
@Slf4j
public class DemoController {

    @GetMapping("/str")
    public String getStr() {
        log.info("start");
        String data = null;
        try {
            TimeUnit.SECONDS.sleep(5);
            data = "hello";
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("end");
        return data;
    }

    @GetMapping("/mono")
    public Mono<String> getStrForMono() {
        log.info("start---mono");
        Mono<String> res = Mono.fromSupplier(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello mono";
        });
        log.info("end---mono");
        return res;
    }

    @GetMapping(value = "/flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getStrForFlux() {
        log.info("start---flux");
        Flux<String> result = Flux.fromStream(IntStream.range(1, 10).mapToObj(i -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "flux i=" + i;
        }));
        log.info("end---flux");
        return result;
    }
}
