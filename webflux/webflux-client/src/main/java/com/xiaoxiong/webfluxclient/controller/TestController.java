package com.xiaoxiong.webfluxclient.controller;

import com.xiaoxiong.webfluxclient.domain.User;
import com.xiaoxiong.webfluxclient.interfaces.IUserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author xiongliang
 * @version 1.0
 * @description
 * @since 2022/5/11  20:39
 */
@RestController
public class TestController {

    @Autowired
    private IUserApi api;

    @GetMapping("/")
    public void test() {
        api.createUser(Mono.just(User.builder().age(30).name("webflux-client").build()))
                .subscribe(System.out::println);
    }

}
