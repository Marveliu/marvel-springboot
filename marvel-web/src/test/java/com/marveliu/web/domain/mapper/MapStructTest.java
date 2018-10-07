package com.marveliu.web.domain.mapper;

import com.marveliu.web.dao.entity.User;
import com.marveliu.web.domain.bo.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class MapStructTest {

    @Autowired
    @SuppressWarnings("all")
    private UserMapper userMapper;

    @Test
    public void test() {
        AuthUser authUser = new AuthUser();
        authUser.setUid("12345");
        authUser.setSalt("12341341");
        authUser.setRealName("marveliu");
        User user = userMapper.from(authUser);
        log.info(user.toString());
        Assert.assertNotNull(user);

    }

}