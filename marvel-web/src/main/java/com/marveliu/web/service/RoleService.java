package com.marveliu.web.service;

import com.marveliu.web.component.service.BaseService;
import com.marveliu.web.dao.entity.Role;
import com.marveliu.web.dao.repository.RoleRepository;

/**
 * @Author: Marveliu
 * @Date: 2018/10/10 上午9:21
 * @Description:
 **/

public interface RoleService extends BaseService<Role, Integer> {

    RoleRepository getDAO();
}
