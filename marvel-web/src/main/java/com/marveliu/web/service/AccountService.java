package com.marveliu.web.service;

import com.marveliu.web.dao.entity.User;
import com.marveliu.web.domain.vo.AccountVo;

/**
 * @Author: Marveliu
 * @Date: 2018/9/29 上午11:45
 * @Description: facade
 **/

public interface AccountService {

    AccountVo loadAccount(String username);

    boolean isAccountExistByUid(Integer uid);

    boolean registerAccount(User user);

    String loadAccountRole(String username);
}
