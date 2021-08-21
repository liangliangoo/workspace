package com.example.batchdemo.job;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/8/19 21:47
 */
@SpringBootTest
public class Job1Test {

    @Autowired
    private Job job1;

    @Test
    public void jobTest(){
    }

}
