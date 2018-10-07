package com.marveliu.web.service.impl;

import com.marveliu.web.component.dao.BaseDao;
import com.marveliu.web.component.service.impl.BaseServiceImpl;
import com.marveliu.web.dao.entity.User;
import com.marveliu.web.dao.repository.UserRepository;
import com.marveliu.web.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 * @Author: Marveliu
 * @Date: 2018/9/24 下午5:09
 * @Description:
 **/

@Slf4j
@Service
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public BaseDao<User, Integer> getDAO() {
        return userRepository;
    }

    @Override
    public boolean authorityUserRole(String uid, int roleId) throws DataAccessException {
        // AuthUserRole authUserRole = new AuthUserRole();
        // authUserRole.setRoleId(roleId);
        // authUserRole.setUserId(uid);
        // authUserRole.setCreateTime(new Date());
        // authUserRole.setUpdateTime(new Date());
        // return authUserRoleMapper.insert(authUserRole) == 1 ? Boolean.TRUE : Boolean.FALSE;
        return false;
    }

    @Override
    public User getUserByAppId(String appId) {
        return null;
    }
}
