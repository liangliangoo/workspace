package com.xiaoxiong.springboot.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author xiongliang
 * @version 1.0
 * @description 容器启动后，执行的业务逻辑
 * @since 2022/6/14  12:22
 */
@Slf4j
@Order(-1)
@Component
public class StartUpRunner1 implements CommandLineRunner {

    /**
     * {@inheritDoc}
     *
     * @param args incoming main method arguments
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        log.info("StartUpRunner1.run");
        log.info(args.toString());
    }
}
