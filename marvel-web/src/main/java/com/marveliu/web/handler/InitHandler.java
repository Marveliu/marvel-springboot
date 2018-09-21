package com.marveliu.web.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Author Marveliu
 * @Date 2018/7/18 8:59 PM
 **/

@Slf4j
@Component
public class InitHandler implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("initHandler start.");
        // todo
    }
}
