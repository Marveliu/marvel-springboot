package com.marveliu.web.domain.mapper;

import com.marveliu.web.dao.entity.User;
import com.marveliu.web.domain.param.RegUserParam;
import com.marveliu.web.domain.vo.AccountVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.notNullValue;


@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class MapStructTest {

    @Autowired
    @SuppressWarnings("all")
    private AccountMapper accountMapper;

    @Autowired
    @SuppressWarnings("all")
    private UserMapper userMapper;

    @Test
    public void f0_test() {
        User authUser = new User();
        authUser.setUid(12345);
        authUser.setSalt("12341341");
        authUser.setRealName("marveliu");
        AccountVo account = accountMapper.from(authUser);
        log.info(account.toString());
        Assert.assertNotNull(account);

    }

    @Test
    public void f1_test() {
        RegUserParam regUserParam = new RegUserParam();
        regUserParam.setUsername("marveliu");
        regUserParam.setRealName("liu");
        regUserParam.setPassword("1234123");

        User user = userMapper.from(regUserParam, "lala");
        log.info(user.toString());
        Assert.assertThat(user, notNullValue(null));

    }

}