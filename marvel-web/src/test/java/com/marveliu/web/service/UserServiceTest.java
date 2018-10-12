package com.marveliu.web.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        log.info(this.getClass().getName() + ":=================");
    }

    @After
    public void tearDown() throws Exception {
        log.info(this.getClass().getName() + ":=================");
    }

    @Test
    public void f0_test() {
        userService.del(2);
    }
}