package com.gl.springbootlearn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {

    @Test
    public void test1() {
        String name = "gl";
        String pwd = "123456";
        log.debug("debug...");
        log.info("info...");
        log.warn("warn...");
        log.error("error...");
        log.error("name:{}, pwd:{}", name, pwd);
    }
}
