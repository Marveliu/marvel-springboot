package com.marveliu.web.config;

import com.marveliu.web.domain.vo.SysConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Marveliu
 * @Date: 2018/9/29 下午5:05
 * @Description: 系统配置信息
 **/

@Configuration
public class MarvelConfig {

    @Value("${marvel.app.name}")
    private String appName;

    @Value("${marvel.app.upload.path}")
    private String uploadPath;

    @Value("${marvel.app.upload.base}")
    private String uploadBase;

    @Value("${marvel.app.author.name}")
    private String authorName;

    @Value("${marvel.app.author.email}")
    private String authorEmail;

    @Bean
    public SysConfig sysConfig() {
        SysConfig sysConfig = new SysConfig();
        sysConfig.setAppName(appName);
        sysConfig.setUploadPath(uploadPath);
        sysConfig.setUploadBase(uploadBase);
        sysConfig.setAuthorName(authorName);
        sysConfig.setAuthorEmail(authorEmail);
        return sysConfig;
    }

}
