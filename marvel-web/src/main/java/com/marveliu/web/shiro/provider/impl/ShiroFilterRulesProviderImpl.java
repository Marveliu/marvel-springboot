package com.marveliu.web.shiro.provider.impl;

import com.marveliu.web.shiro.provider.ShiroFilterRulesProvider;
import com.marveliu.web.shiro.rule.RolePermRule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Marveliu
 * @Date: 2018/9/29 下午2:18
 * @Description:
 **/

@Slf4j
@Service
public class ShiroFilterRulesProviderImpl implements ShiroFilterRulesProvider {

    // @Autowired
    // private AuthResourceMapper authResourceMapper;

    @Override
    public List<RolePermRule> loadRolePermRules() {
        // return authResourceMapper.selectRoleRules();
        return null;
    }

}
