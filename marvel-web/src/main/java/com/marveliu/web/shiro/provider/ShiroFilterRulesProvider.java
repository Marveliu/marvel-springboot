package com.marveliu.web.shiro.provider;

import com.marveliu.web.shiro.rule.RolePermRule;

import java.util.List;

/**
 * @Author: Marveliu
 * @Date: 2018/9/29 下午2:16
 * @Description: 动态过滤规则提供者接口
 **/

public interface ShiroFilterRulesProvider {

    /**
     * 加载基于角色/资源的过滤规则
     * 即：用户-角色-资源（URL），对应关系存储与数据库中
     * 在shiro中生成的过滤器链为：url=jwt[角色1、角色2、角色n]
     *
     * @return
     */
    public List<RolePermRule> loadRolePermRules();
}
