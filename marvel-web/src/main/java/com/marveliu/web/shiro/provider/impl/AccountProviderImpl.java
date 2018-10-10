package com.marveliu.web.shiro.provider.impl;

import com.marveliu.web.domain.vo.Account;
import com.marveliu.web.service.AccountService;
import com.marveliu.web.shiro.provider.AccountProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Marveliu
 * @Date: 2018/9/29 下午2:31
 * @Description:
 **/

@Slf4j
@Service
public class AccountProviderImpl implements AccountProvider {

    @Autowired
    private AccountService accountService;

    @Override
    public Account loadAccount(Integer appId) {
        return accountService.loadAccount(appId);
    }
}
