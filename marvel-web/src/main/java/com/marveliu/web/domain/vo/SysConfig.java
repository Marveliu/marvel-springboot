package com.marveliu.web.domain.vo;

import lombok.Data;

/**
 * @Author: Marveliu
 * @Date: 2018/9/29 下午5:08
 * @Description:
 **/

@Data
public class SysConfig {

    private String appName;

    private String uploadPath;

    private String uploadBase;

    private String authorName;

    private String authorEmail;
}
