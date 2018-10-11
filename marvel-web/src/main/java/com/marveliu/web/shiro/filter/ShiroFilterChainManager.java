package com.marveliu.web.shiro.filter;

import com.marveliu.web.service.AccountService;
import com.marveliu.web.shiro.provider.ShiroFilterRulesProvider;
import com.marveliu.web.shiro.rule.RolePermRule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Marveliu
 * @Date: 2018/9/29 下午2:15
 * @Description:
 **/

@Slf4j
@Component
public class ShiroFilterChainManager {

    private final ShiroFilterRulesProvider shiroFilterRulesProvider;
    private final StringRedisTemplate redisTemplate;
    private final AccountService accountService;

    @Autowired
    public ShiroFilterChainManager(ShiroFilterRulesProvider shiroFilterRulesProvider, StringRedisTemplate redisTemplate, AccountService accountService) {
        this.shiroFilterRulesProvider = shiroFilterRulesProvider;
        this.redisTemplate = redisTemplate;
        this.accountService = accountService;
    }

    /**
     * 初始化获取过滤链
     *
     * @return
     */
    public Map<String, Filter> initGetFilters() {
        // url和对应的filter的映射关系
        Map<String, Filter> filters = new LinkedHashMap<>();
        PasswordFilter passwordFilter = new PasswordFilter();
        passwordFilter.setRedisTemplate(redisTemplate);
        filters.put("auth", passwordFilter);
        JwtFilter jwtFilter = new JwtFilter();
        jwtFilter.setRedisTemplate(redisTemplate);
        jwtFilter.setAccountService(accountService);
        filters.put("jwt", jwtFilter);
        return filters;
    }

    /**
     * 初始化获取过滤链规则
     *
     * @return
     */
    public Map<String, String> initGetFilterChain() {
        Map<String, String> filterChain = new LinkedHashMap<>();
        // anon 默认过滤器忽略的URL
        // todo: 过滤Druid Swagger等资源
        List<String> defalutAnon = Arrays.asList("/css/**", "/js/**");
        defalutAnon.forEach(ignored -> filterChain.put(ignored, "anon"));
        // auth 默认需要认证过滤器的URL 走auth--PasswordFilter
        List<String> defalutAuth = Arrays.asList("/account/**");
        defalutAuth.forEach(auth -> filterChain.put(auth, "auth"));

        // todo:一旦发生变动，貌似不能动态的更新
        // -------------dynamic 动态URL
        if (shiroFilterRulesProvider != null) {
            List<RolePermRule> rolePermRules = this.shiroFilterRulesProvider.loadRolePermRules();
            if (null != rolePermRules) {
                rolePermRules.forEach(rule -> {
                    StringBuilder Chain = rule.toFilterChain();
                    if (null != Chain) {
                        filterChain.putIfAbsent(rule.getUrl(), Chain.toString());
                    }
                });
            }
        }
        return filterChain;
    }


}
