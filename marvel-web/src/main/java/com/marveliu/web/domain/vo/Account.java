package com.marveliu.web.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Marveliu
 * @Date: 2018/9/29 下午2:34
 * @Description:
 **/

@Data
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    private String appId;
    private String password;
    private String salt;

    public Account(String appId, String password, String salt) {
        this.appId = appId;
        this.password = password;
        this.salt = salt;
    }
}
