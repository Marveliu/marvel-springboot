package com.marveliu.web.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @Author: Marveliu
 * @Date: 2018/9/29 下午2:46
 * @Description:
 **/

public class AModularRealmAuthenticator extends ModularRealmAuthenticator {

    /**
     * single realm and multi realm strategy
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        assertRealmsConfigured();
        List<Realm> realms = this.getRealms()
                .stream()
                .filter(realm -> {
                    return realm.supports(authenticationToken);
                })
                .collect(toList());
        return realms.size() == 1 ? this.doSingleRealmAuthentication(realms.iterator().next(), authenticationToken) : this.doMultiRealmAuthentication(realms, authenticationToken);

    }
}