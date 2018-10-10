package com.marveliu.web.domain.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: Marveliu
 * @Date: 2018/10/10 下午3:31
 * @Description: 用户注册
 **/

@Data
public class RegUserParam {


    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank
    private String realName;

    /**
     * redis动态公钥
     */
    @NotBlank
    private String userKey;

    /**
     * 被userKey加密
     */
    @NotBlank
    private String password;


}
