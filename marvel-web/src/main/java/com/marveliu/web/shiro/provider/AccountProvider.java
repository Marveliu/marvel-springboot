package com.marveliu.web.shiro.provider;


import com.marveliu.web.domain.vo.AccountVo;

/**
 * @Author: Marveliu
 * @Date: 2018/9/29 下午2:31
 * @Description: 数据库用户密码账户提供
 **/

public interface AccountProvider {

    AccountVo loadAccount(String username);
}
