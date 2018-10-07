package com.marveliu.web.config;

import com.marveliu.web.domain.vo.SysConfig;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.MultipartConfigElement;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * @Author: Marveliu
 * @Date: 2018/9/17 下午11:35
 * @Description:
 **/

@Slf4j
@Configuration
public class UploadConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private SysConfig sysConfig;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 设置暂存目录
        String location = System.getProperty("user.dir") + "/" + sysConfig.getAppName() + "/tmp";
        File tmpFile = new File(location);
        if (!tmpFile.exists()) {
            tmpFile.mkdirs();
        }
        factory.setLocation(location);
        // 单个文件最大 // KB, MB
        factory.setMaxFileSize("201400KB");
        // 设置上传数据总大小
        factory.setMaxRequestSize("2014000KB");
        return factory.createMultipartConfig();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
            log.debug("路径: " + path);
            String gitPath = path.getParentFile().getParentFile().getParent() + sysConfig.getUploadPath();
            log.debug("上传外部资源路径：" + gitPath);
            registry.addResourceHandler(sysConfig.getUploadBase() + "**").addResourceLocations(ResourceUtils.FILE_URL_PREFIX + gitPath);
            registry.addResourceHandler("/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
            super.addResourceHandlers(registry);
        } catch (FileNotFoundException e) {
            log.error("映射外部资源文件出错", e);
        }
    }
}
