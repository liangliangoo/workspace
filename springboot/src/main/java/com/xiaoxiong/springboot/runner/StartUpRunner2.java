package com.xiaoxiong.springboot.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author xiongliang
 * @version 1.0
 * @description
 * @since 2022/6/14  12:26
 */
@Slf4j
@Order(0)
@Component
public class StartUpRunner2 implements CommandLineRunner {

    /**
     * {@inheritDoc}
     *
     * @param args incoming main method arguments
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        log.info("StartUpRunner2.run");
        log.info(args.toString());
    }
}
