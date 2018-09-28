package com.marveliu.web.shiro;

import com.marveliu.web.dao.entity.SysPermission;
import com.marveliu.web.dao.entity.SysRole;
import com.marveliu.web.dao.entity.User;
import com.marveliu.web.dao.repository.UserRepository;
import com.marveliu.web.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

/**
 * @Author: Marveliu
 * @Date: 2018/9/24 下午5:07
 * @Description:
 **/

@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    /**
     * 获取用户角色和权限
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User User = (User) principals.getPrimaryPrincipal();
        for (SysRole role : User.getRoles()) {
            authorizationInfo.addRole(role.getRole());
            for (SysPermission p : role.getPermissions()) {
                authorizationInfo.addStringPermission(p.getPermission());
            }
        }
        return authorizationInfo;
    }

    /**
     * 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {

        // 获取用户的输入的账号
        String userName = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        log.info(String.format("用户:[%s]认证", userName));


        // 通过username从数据库中查找 User对象，如果找到，没找到.
        // todo: 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        User user = ((UserRepository) userService.getDAO()).findUserByName(userName);
        if (user == null) {
            throw new UnknownAccountException("用户名或密码错误！");
        }
        if (!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("用户名或密码错误！");
        }
        // todo: 账户状态信息定义
        if (user.getStatus().equals(2)) {
            throw new LockedAccountException("账号已被锁定,请联系管理员！");
        }


        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                // 用户名
                user,
                // 密码
                user.getPassword(),
                // salt=username+salt
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                // realm name
                getName()
        );
        return authenticationInfo;
    }

}
