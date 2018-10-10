package com.marveliu.web.service.impl;

import com.marveliu.web.component.dao.BaseDao;
import com.marveliu.web.component.service.impl.BaseServiceImpl;
import com.marveliu.web.dao.entity.Role;
import com.marveliu.web.dao.repository.RoleRepository;
import com.marveliu.web.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Marveliu
 * @Date: 2018/10/10 上午9:21
 * @Description:
 **/

@Slf4j
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, Integer> implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public BaseDao<Role, Integer> getDAO() {
        return roleRepository;
    }
}
