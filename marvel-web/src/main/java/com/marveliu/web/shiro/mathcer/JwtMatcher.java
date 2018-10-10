package com.marveliu.web.shiro.mathcer;

import com.marveliu.web.domain.vo.JwtAccountVo;
import com.marveliu.web.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.springframework.stereotype.Component;

/**
 * @Author: Marveliu
 * @Date: 2018/9/29 下午2:26
 * @Description:
 **/

@Component
public class JwtMatcher implements CredentialsMatcher {


    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        String jwt = (String) authenticationInfo.getCredentials();
        JwtAccountVo jwtAccount = null;
        try {
            jwtAccount = JwtUtil.parseJwt(jwt, JwtUtil.SECRET_KEY);
        } catch (SignatureException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            // 令牌错误
            throw new AuthenticationException("errJwt");
        } catch (ExpiredJwtException e) {
            // 令牌过期
            throw new AuthenticationException("expiredJwt");
        } catch (Exception e) {
            throw new AuthenticationException("errJwt");
        }
        if (null == jwtAccount) {
            throw new AuthenticationException("errJwt");
        }
        return true;
    }
}

