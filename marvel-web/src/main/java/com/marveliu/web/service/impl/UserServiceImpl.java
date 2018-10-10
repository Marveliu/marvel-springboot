package com.marveliu.web.service.impl;

import com.marveliu.web.component.service.impl.BaseServiceImpl;
import com.marveliu.web.dao.entity.Role;
import com.marveliu.web.dao.entity.User;
import com.marveliu.web.dao.repository.UserRepository;
import com.marveliu.web.service.RoleService;
import com.marveliu.web.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private RoleService roleService;

    @Override
    public UserRepository getDAO() {
        return userRepository;
    }

    @Override
    public boolean authorityUserRole(User user, Integer roleId) throws DataAccessException {
        Role role = roleService.findById(roleId);
        if (ObjectUtils.isEmpty(role)) {
            return false;
        } else {
            List<Role> roleList = user.getRoles();
            if (ObjectUtils.isEmpty(roleList)) {
                roleList = new ArrayList<>();
            }
            roleList.add(role);
            user.setRoles(roleList);
        }
        return this.save(user) != null ? Boolean.TRUE : Boolean.FALSE;
    }
}
