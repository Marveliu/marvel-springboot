package com.marveliu.web.config;

import com.marveliu.web.dao.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisConfigTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void f0_test() throws Exception {
        User authUser = new User();
        authUser.setUid(12345);
        authUser.setSalt("12341341");
        authUser.setRealName("marveliu");
        redisTemplate.opsForValue().set("marveliu", authUser);
        Thread.sleep(100000);
    }

}