package com.marveliu.web.domain.bo;

import lombok.Data;

/**
 * @Author: Marveliu
 * @Date: 2018/10/7 下午7:44
 * @Description:
 **/

@Data
public class AuthUser {

    private String uid;

    private String username;

    private String password;

    private String salt;

    private String realName;

    private String avatar;

    private String phone;

    private String email;

    private Byte sex;

    private Byte status;

    private Long createTime;

    private Long updateTime;

    private Byte createWhere;
}
