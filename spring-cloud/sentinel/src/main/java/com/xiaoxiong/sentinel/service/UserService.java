package com.xiaoxiong.sentinel.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author 六月
 * @Date 2022/8/7 14:25
 * @Version 1.0
 */
@Slf4j
@Service
public class UserService {

    private final AtomicInteger counter = new AtomicInteger(1);

    @SentinelResource(value = "findAllUser", blockHandler = "findAllUserFail", fallback = "findAllUserFailBack")
    public List<User> findAllUser() {

        if (counter.incrementAndGet() % 4 == 0) {
            log.info("counter:{}", counter.get());
            new RuntimeException("error");
        }
        log.info("counter:{}", counter.get());
        return Lists.newArrayList(User.builder().id(1l).userName("user1").age(18).build(),
                User.builder().id(2l).userName("user2").age(19).build(),
                User.builder().id(3l).userName("user3").age(17).build()
        );
    }

    public List<User> findAllUserBlockHandler(BlockException e) {
        log.warn("降级");
        if (e instanceof FlowException) {
            log.warn("限流了");
        }
        if (e instanceof DegradeException) {
            log.warn("服务降级了");
        }
        return Lists.newArrayList();
    }

    public List<User> findAllUserFailBack() {
        log.warn("其他异常处理,counter:{}", counter.getAndIncrement());
        return Lists.newArrayList(User.builder().userName("error").build());
    }


    @Data
    @Builder
    private static class User {
        private Long id;
        private String userName;
        private Integer age;
    }

}
