package com.marveliu.web.shiro.realm;

import com.marveliu.web.shiro.token.JwtToken;
import com.marveliu.web.util.JwtUtil;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Map;
import java.util.Set;

/**
 * @Author: Marveliu
 * @Date: 2018/9/29 下午1:47
 * @Description:
 **/

@Slf4j
public class JwtRealm extends AuthorizingRealm {

    @Override
    public Class<?> getAuthenticationTokenClass() {
        // 此realm只支持jwtToken
        return JwtToken.class;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String payload = (String) principalCollection.getPrimaryPrincipal();
        // likely to be json, parse it:
        if (payload.startsWith("jwt:") && payload.charAt(4) == '{'
                && payload.charAt(payload.length() - 1) == '}') {
            Map<String, Object> payloadMap = JwtUtil.readValue(payload.substring(4));
            Set<String> roles = JwtUtil.split((String) payloadMap.get("roles"));
            Set<String> permissions = JwtUtil.split((String) payloadMap.get("perms"));
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            if (null != roles && !roles.isEmpty()) {
                info.setRoles(roles);
            }
            if (null != permissions && !permissions.isEmpty()) {
                info.setStringPermissions(permissions);
            }
            return info;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (!(authenticationToken instanceof JwtToken)) {
            return null;
        }
        JwtToken jwtToken = (JwtToken) authenticationToken;
        String jwt = (String) jwtToken.getCredentials();
        String payload = null;
        try {
            // 预先解析Payload
            // 没有做任何的签名校验
            payload = JwtUtil.parseJwtPayload(jwt);
        } catch (MalformedJwtException e) {
            // 令牌格式错误
            throw new AuthenticationException("errJwt");
        } catch (Exception e) {
            // 令牌无效
            throw new AuthenticationException("errsJwt");
        }
        if (null == payload) {
            // 令牌无效
            throw new AuthenticationException("errJwt");
        }
        return new SimpleAuthenticationInfo("jwt:" + payload, jwt, this.getName());
    }
}
