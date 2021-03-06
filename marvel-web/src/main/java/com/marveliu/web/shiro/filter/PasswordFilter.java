package com.marveliu.web.shiro.filter;

import com.alibaba.fastjson.JSON;
import com.marveliu.web.domain.vo.MessageVo;
import com.marveliu.web.shiro.token.PasswordToken;
import com.marveliu.web.util.CommonUtil;
import com.marveliu.web.util.HttpUtil;
import com.marveliu.web.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Marveliu
 * @Date: 2018/9/29 下午2:08
 * @Description: 基于 用户名密码 的认证过滤器
 **/

@Slf4j
public class PasswordFilter extends AccessControlFilter {

    private StringRedisTemplate redisTemplate;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {

        Subject subject = getSubject(request, response);
        // 如果其已经登录，再此发送登录请求
        if (null != subject && subject.isAuthenticated()) {
            return true;
        }
        //  拒绝，统一交给 onAccessDenied 处理
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        // 判断若为获取登录注册加密动态秘钥请求
        if (isPasswordTokenGet(request)) {
            //动态生成秘钥，redis存储秘钥供之后秘钥验证使用，设置有效期5秒用完即丢弃
            String tokenKey = CommonUtil.getRandomString(16);
            String userKey = CommonUtil.getRandomString(6);
            try {
                redisTemplate.opsForValue().set("TOKEN_KEY_" + IpUtil.getIpFromRequest(WebUtils.toHttp(request)).toUpperCase() + userKey.toUpperCase(), tokenKey, 100, TimeUnit.SECONDS);
                // 动态秘钥response返回给前端
                MessageVo message = new MessageVo();
                message.ok(1000, "issued tokenKey success")
                        .addData("tokenKey", tokenKey).addData("userKey", userKey.toUpperCase());
                HttpUtil.responseWrite(JSON.toJSONString(message), response);
            } catch (Exception e) {
                log.warn("签发动态秘钥失败" + e.getMessage(), e);
                MessageVo message = new MessageVo();
                message.ok(1000, "issued tokenKey fail");
                HttpUtil.responseWrite(JSON.toJSONString(message), response);
            }
            return false;
        }

        // 判断是否是登录请求
        if (isPasswordLoginPost(request)) {

            AuthenticationToken authenticationToken = null;
            try {
                authenticationToken = createPasswordToken(request);
            } catch (Exception e) {
                // response 告知无效请求
                MessageVo message = new MessageVo().error(1111, "error request");
                HttpUtil.responseWrite(JSON.toJSONString(message), response);
                return false;
            }

            Subject subject = getSubject(request, response);
            try {
                subject.login(authenticationToken);
                //登录认证成功,进入请求派发json web token url资源内
                return true;
            } catch (AuthenticationException e) {
                log.warn(authenticationToken.getPrincipal() + "::" + e.getMessage());
                // 返回response告诉客户端认证失败
                MessageVo message = new MessageVo().error(1002, "login fail");
                HttpUtil.responseWrite(JSON.toJSONString(message), response);
                return false;
            } catch (Exception e) {
                log.error(authenticationToken.getPrincipal() + "::认证异常::" + e.getMessage(), e);
                // 返回response告诉客户端认证失败
                MessageVo message = new MessageVo().error(1002, "login fail");
                HttpUtil.responseWrite(JSON.toJSONString(message), response);
                return false;
            }
        }
        // 判断是否为注册请求,若是通过过滤链进入controller注册
        if (isAccountRegisterPost(request)) {
            return true;
        }
        // 之后添加对账户的找回等

        // response 告知无效请求
        MessageVo message = new MessageVo().error(1111, "error request");
        HttpUtil.responseWrite(JSON.toJSONString(message), response);
        return false;
    }

    private boolean isPasswordTokenGet(ServletRequest request) {
        String tokenKey = HttpUtil.getParameter(request, "tokenKey");
        return (request instanceof HttpServletRequest)
                && ((HttpServletRequest) request).getMethod().toUpperCase().equals("GET")
                && null != tokenKey && "get".equals(tokenKey);
    }

    private boolean isPasswordLoginPost(ServletRequest request) {
        Map<String, String> map = HttpUtil.getRequestBodyMap(request);
        String password = map.get("password");
        String timestamp = map.get("timestamp");
        String methodName = map.get("methodName");
        String appId = map.get("username");
        return (request instanceof HttpServletRequest)
                && ((HttpServletRequest) request).getMethod().toUpperCase().equals("POST")
                && null != password
                && null != timestamp
                && null != methodName
                && null != appId
                && methodName.equals("login");
    }

    private boolean isAccountRegisterPost(ServletRequest request) {
        Map<String, String> map = HttpUtil.getRequestBodyMap(request);
        String userName = map.get("username");
        String realName = map.get("realName");
        String methodName = map.get("methodName");
        String password = map.get("password");
        return (request instanceof HttpServletRequest)
                && ((HttpServletRequest) request).getMethod().toUpperCase().equals("POST")
                && null != userName
                && null != password
                && null != methodName
                && null != realName
                && methodName.equals("register");
    }

    /**
     * Create Password Token
     *
     * @param request
     * @return
     * @throws Exception
     */
    private AuthenticationToken createPasswordToken(ServletRequest request) throws Exception {
        Map<String, String> map = HttpUtil.getRequestBodyMap(request);
        String appId = map.get("username");
        String timestamp = map.get("timestamp");
        String password = map.get("password");
        String host = IpUtil.getIpFromRequest(WebUtils.toHttp(request));
        String userKey = map.get("userKey");
        String tokenKey = redisTemplate.opsForValue().get("TOKEN_KEY_" + host.toUpperCase() + userKey);
        return new PasswordToken(appId, password, timestamp, host, tokenKey);
    }

    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

}

