package com.marveliu.web.service;

import lombok.extern.slf4j.Slf4j;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ResourceServiceTest {

    @Autowired
    private ResourceService resourceService;

    @Rule
    public ContiPerfRule i = new ContiPerfRule();

    /**
     * 测试全部缓存
     */
    @Test
    public void findAll() {
        resourceService.findAll();
    }

    /**
     * 性能测试
     * 10万次查询，100个线程同时操作findAll方法
     */
    @Test
    @PerfTest(invocations = 1000, threads = 100)
    public void contextLoads() {
        resourceService.findAll();
    }

}