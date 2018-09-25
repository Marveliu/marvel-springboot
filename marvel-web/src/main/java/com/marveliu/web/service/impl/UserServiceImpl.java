package com.marveliu.web.service.impl;

import com.marveliu.common.component.dao.BaseDao;
import com.marveliu.common.component.service.impl.BaseServiceImpl;
import com.marveliu.web.dao.entity.Log;
import com.marveliu.web.dao.entity.User;
import com.marveliu.web.dao.repository.UserRepository;
import com.marveliu.web.service.LogService;
import com.marveliu.web.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: Marveliu
 * @Date: 2018/9/24 下午5:09
 * @Description:
 **/

@Slf4j
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public BaseDao<User, Integer> getDAO() {
        return userRepository;
    }
}
