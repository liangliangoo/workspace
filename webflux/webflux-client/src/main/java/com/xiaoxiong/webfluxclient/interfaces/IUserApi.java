package com.xiaoxiong.webfluxclient.interfaces;

import com.xiaoxiong.webfluxclient.annotation.ApiServer;
import com.xiaoxiong.webfluxclient.domain.User;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author xiongliang
 * @version 1.0
 * @description
 * @since 2022/5/11  19:34
 */
@ApiServer("http://127.0.0.1:8080/userRouter")
public interface IUserApi {

    @GetMapping("/")
    Flux<User> getAllUser();

    @GetMapping("/{id}")
    Mono<User> getUserById(@PathVariable("id") String id);

    @DeleteMapping("/{id}")
    Mono<Void> deleteUserById(@PathVariable("id") String id);

    @PostMapping("/")
    Mono<User> createUser(@RequestBody Mono<User> user);

}
