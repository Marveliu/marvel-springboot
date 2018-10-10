package com.marveliu.web.service.impl;

import com.marveliu.web.dao.entity.Role;
import com.marveliu.web.dao.entity.User;
import com.marveliu.web.domain.mapper.AccountMapper;
import com.marveliu.web.domain.vo.AccountVo;
import com.marveliu.web.service.AccountService;
import com.marveliu.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Marveliu
 * @Date: 2018/9/29 上午11:45
 * @Description:
 **/

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public AccountVo loadAccount(String username) {
        User user = userService.getDAO().findUserByUsername(username);
        return user != null ? accountMapper.from(user) : null;
    }

    @Override
    public boolean isAccountExistByUid(Integer uid) {
        User user = userService.findById(uid);
        return user != null ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean registerAccount(User user) {
        // 给新用户授权访客角色
        userService.authorityUserRole(user, 1);
        // 插入用户信息
        return userService.save(user) != null ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public String loadAccountRole(String username) {
        List<Role> sysRoles = userService.getDAO().findUserByUsername(username).getRoles();
        StringBuilder rs = new StringBuilder();
        for (Role role : sysRoles) {
            rs.append(role.getId() + ",");
        }
        return rs.toString();
    }
}
