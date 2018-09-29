package com.marveliu.web.service;

import com.marveliu.web.dao.entity.User;
import com.marveliu.web.domain.vo.Account;

/**
 * @Author: Marveliu
 * @Date: 2018/9/29 上午11:45
 * @Description:
 **/

public interface AccountService {

    Account loadAccount(String appId);

    boolean isAccountExistByUid(String uid);

    boolean registerAccount(User account);

    String loadAccountRole(String appId);
}
