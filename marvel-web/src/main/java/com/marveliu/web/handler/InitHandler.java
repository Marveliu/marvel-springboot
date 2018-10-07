package com.marveliu.web.handler;

import com.marveliu.web.domain.vo.SysConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @Author Marveliu
 * @Date 2018/7/18 8:59 PM
 **/

@Slf4j
@Component
public class InitHandler implements CommandLineRunner {

    @Autowired
    private SysConfig sysConfig;

    @Override
    public void run(String... args) throws Exception {
        log.info(String.format("%s initHandler start.", sysConfig.getAppName()));
        initUpload();
    }

    /**
     * 初始化上传路径
     *
     * @return
     */
    private boolean initUpload() {
        try {
            File dir = new File(sysConfig.getUploadPath());
            if (!dir.exists()) {
                dir.mkdir();
                log.info("初始化上传文件地址：" + dir.getAbsolutePath());
            }
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }

}
