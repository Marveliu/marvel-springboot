package com.marveliu.web.shiro.token;

import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Author: Marveliu
 * @Date: 2018/9/29 下午2:04
 * @Description:
 **/

@Data
public class PasswordToken implements AuthenticationToken {

    private static final long serialVersionUID = 1L;
    private String appId;
    private String password;
    private String timestamp;
    private String host;
    private String tokenKey;

    public PasswordToken(String appId, String password, String timestamp, String host, String tokenKey) {
        this.appId = appId;
        this.password = password;
        this.timestamp = timestamp;
        this.host = host;
        this.tokenKey = tokenKey;
    }

    @Override
    public Object getPrincipal() {
        return this.appId;
    }

    @Override
    public Object getCredentials() {
        return this.password;
    }


}
