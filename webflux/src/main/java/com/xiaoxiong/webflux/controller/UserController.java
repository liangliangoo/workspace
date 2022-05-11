package com.xiaoxiong.webflux.controller;

import com.xiaoxiong.webflux.domain.User;
import com.xiaoxiong.webflux.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * @author xiongliang
 * @version 1.0
 * @description
 * @since 2022/5/9  21:57
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 一次将数据全部返回，数组的形式
     *
     * @return Flux<User>
     */
    @GetMapping("/all")
    public Flux<User> getAllUser() {
        return userRepository.findAll();
    }

    /**
     * 以SSE形式多次返回数据
     *
     * @return Flux<User>
     */
    @GetMapping(value = "/stream/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> getAllUserByStream() {
        return userRepository.findAll();
    }

    /**
     * 新增用户   在Spring JPA userRepository.save(user); 当中 如果插入的数据有ID这边是更新，否则是新增
     *
     * @param user
     * @return
     */
    @PostMapping("/add")
    public Mono<User> createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    /**
     * 新增用户   在Spring JPA userRepository.save(user); 当中 如果插入的数据有ID这边是更新，否则是新增
     *
     * @param user
     * @return
     */
    @PostMapping("/update")
    public Mono<User> updateUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable("id") String id) {
        return userRepository.findById(id).flatMap(user -> userRepository.delete(user)
                        .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
