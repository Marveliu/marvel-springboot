package com.marveliu.web.service.impl;

import com.marveliu.web.dao.entity.User;
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
    public boolean registerAccount(User account) {
        return false;
    }

    @Override
    public String loadAccountRole(String appId) {
        return null;
    }
}
