package com.marveliu.web.domain.mapper;

import com.marveliu.web.dao.entity.User;
import com.marveliu.web.domain.vo.Account;
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
    private AccountMapper accountMapper;

    @Test
    public void test() {
        User authUser = new User();
        authUser.setUid(12345);
        authUser.setSalt("12341341");
        authUser.setRealName("marveliu");
        Account account = accountMapper.from(authUser);
        log.info(account.toString());
        Assert.assertNotNull(account);

    }

}