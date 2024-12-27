package com.washy.springtest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: washy
 * @Date: 2024/04/24/14:18
 */

@Slf4j
@SpringBootApplication(scanBasePackages = "com.washy.springtest")
public class SpringTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringTestApplication.class, args);
        log.info("=================application running !!!===================");
    }
}
