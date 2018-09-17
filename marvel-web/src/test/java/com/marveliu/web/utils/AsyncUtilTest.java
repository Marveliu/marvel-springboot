package com.marveliu.web.utils;

import com.marveliu.web.WebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AsyncUtilTest {

    @Autowired
    private AsyncUtil asyncUtil;

    @Test
    public void test1() {
        asyncUtil.test();
        asyncUtil.test1();
    }
}