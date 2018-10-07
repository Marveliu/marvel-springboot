package com.marveliu.web.service.impl;

import com.marveliu.web.domain.bo.AuthUser;
import com.marveliu.web.domain.mapper.UserMapper;
import com.marveliu.web.domain.vo.Account;
import com.marveliu.web.service.AccountService;
import com.marveliu.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Marveliu
 * @Date: 2018/9/29 上午11:45
 * @Description:
 **/

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Override
    public Account loadAccount(String appId) {
        return null;
    }

    @Override
    public boolean isAccountExistByUid(String uid) {
        return false;
    }

    @Override
    public boolean registerAccount(AuthUser account) {
        // 给新用户授权访客角色
        userService.authorityUserRole(account.getUid(), 103);
        // 插入用户信息
        return userService.save(userMapper.from(account)) == null ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public String loadAccountRole(String appId) {
        return null;
    }
}
