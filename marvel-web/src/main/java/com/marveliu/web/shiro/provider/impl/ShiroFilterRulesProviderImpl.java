package com.marveliu.web.shiro.provider.impl;

import com.marveliu.web.dao.entity.Resource;
import com.marveliu.web.service.ResourceService;
import com.marveliu.web.shiro.provider.ShiroFilterRulesProvider;
import com.marveliu.web.shiro.rule.RolePermRule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Marveliu
 * @Date: 2018/9/29 下午2:18
 * @Description:
 **/

@Slf4j
@Service
public class ShiroFilterRulesProviderImpl implements ShiroFilterRulesProvider {

    @Autowired
    private ResourceService resourceService;

    @Override
    public List<RolePermRule> loadRolePermRules() {
        List<RolePermRule> rolePermRules = new ArrayList<>();
        List<Resource> resources = resourceService.findAll();
        for (Resource r : resources) {
            rolePermRules.add(new RolePermRule(r.getUrlWithMethod(), r.getRolesName()));
            r.getRoles();
        }
        return rolePermRules;
    }

}
